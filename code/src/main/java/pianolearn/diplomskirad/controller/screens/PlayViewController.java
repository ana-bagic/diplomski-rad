package pianolearn.diplomskirad.controller.screens;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.controller.components.PianoKeyboardController;
import pianolearn.diplomskirad.controller.components.SheetMusicController;
import pianolearn.diplomskirad.helper.TempoHelper;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.model.PlaybackSpeed;
import pianolearn.diplomskirad.model.score.ScoreAttributes;
import pianolearn.diplomskirad.model.viewmodel.MeasurePair;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.screens.PlayView;

import java.util.LinkedList;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.*;

public class PlayViewController implements BaseViewController {

    private final PlayView view = new PlayView();

    private final SheetMusicController sheetMusicController;
    private final PianoKeyboardController pianoKeyboardController;

    private final ScoreAttributes attributes;
    private double durationOfQuarter;
    private double durationOfMeasure;

    private final List<MeasurePair> measurePairs = new LinkedList<>();
    private int nextPlayMeasureIndex;
    private boolean isBeginningOfMeasure;

    private double remainingDistance;
    private double tickDistance;
    private int remainingTickCounts;

    private boolean isPlaying;
    private double playbackSpeed = 1;
    private boolean isWait = true;
    private boolean rightHandShows = true;
    private boolean leftHandShows;

    public PlayViewController() {
        ScorePartwise.Part part = Score.pianoPart();
        attributes = Score.scoreAttributes(part);
        assert attributes != null;

        sheetMusicController = new SheetMusicController(part, attributes);
        pianoKeyboardController = new PianoKeyboardController();

        setupListeners();
        setupView();
        setupValues();

        sheetMusicController.addNextMeasure();
    }

    @Override
    public BaseView getView() {
        return view;
    }

    private void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);

        view.setPlayPauseButtonListener(this::playPauseButtonClicked);
        view.setStopButtonListener(this::stopButtonClicked);
        view.setSpeedSliderListener(this::speedChanged);
        view.setRightHandButtonListener(this::rightHandButtonClicked);
        view.setLeftHandButtonListener(this::leftHandButtonClicked);

        sheetMusicController.setMeasurePairCreatedListener(measurePairs::add);
    }

    private void setupView() {
        view.setUsesBothHands(attributes.usesBothHands());
        view.setSheetMusicView(sheetMusicController.getView());
        view.setPianoKeyboardView(pianoKeyboardController.getView());
    }

    private void setupValues() {
        leftHandShows = attributes.usesBothHands();
        durationOfQuarter = TempoHelper.getDurationOfQuarter(attributes);
        durationOfMeasure = TempoHelper.getDurationOfMeasure(durationOfQuarter, attributes);

        resetValues();
    }

    private void resetValues() {
        measurePairs.clear();
        nextPlayMeasureIndex = -1;
        isBeginningOfMeasure = true;

        remainingTickCounts = 0;
        isPlaying = false;
    }

    private void playPauseButtonClicked() {
        isPlaying = !isPlaying;
        mainLoop();
    }

    private void stopButtonClicked() {
        resetValues();
        sheetMusicController.reset();
    }

    private void speedChanged(PlaybackSpeed speed) {
        playbackSpeed = speed.getSpeed();
        isWait = speed == PlaybackSpeed.WAIT;
    }

    private void rightHandButtonClicked() {
        rightHandShows = !rightHandShows;
        sheetMusicController.rightHandChanged(rightHandShows);
    }

    private void leftHandButtonClicked() {
        leftHandShows = !leftHandShows;
        sheetMusicController.leftHandChanged(leftHandShows);
    }

    private void mainLoop() {
        if (isPlaying) {
            if (remainingTickCounts == 0 && !setupNewRegion()) {
                isPlaying = false;
                view.setPaused();
                return;
            }

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(TICK_DURATION_MS), event -> {
                double amount = remainingTickCounts == 1 ? remainingDistance : tickDistance;
                sheetMusicController.translateMeasures(amount);
                remainingDistance -= amount;
                remainingTickCounts = Math.max(0, remainingTickCounts - 1);

                mainLoop();
            }));
            timeline.play();
        }
    }

    private boolean setupNewRegion() {
        double durationOfRegion;
        if (nextPlayMeasureIndex < 0) {
            remainingDistance = CTRL_LINE_MEASURE_DISTANCE + BARLINE_NOTE_SPACE;
            durationOfRegion = durationOfMeasure;
            nextPlayMeasureIndex++;
        } else if (nextPlayMeasureIndex < measurePairs.size()) {
            MeasurePair measurePair = measurePairs.get(nextPlayMeasureIndex);
            int lastDuration = measurePair.getMainHand().getLast().getDuration();
            double notesWidthWithoutLast = measurePair.getNotesWidthWithoutLast();
            double durationOfLast = TempoHelper.getDurationOfNote(durationOfQuarter, attributes.divisions(), lastDuration);

            if (isBeginningOfMeasure) {
                remainingDistance = notesWidthWithoutLast;
                durationOfRegion = durationOfMeasure - durationOfLast;
                isBeginningOfMeasure = false;
            } else  {
                remainingDistance = measurePair.getWidth() - notesWidthWithoutLast;
                durationOfRegion = durationOfLast;
                isBeginningOfMeasure = true;
                nextPlayMeasureIndex++;
            }
        } else {
            return false;
        }

        double actualDuration = durationOfRegion / playbackSpeed;
        remainingTickCounts = (int) Math.round(actualDuration / TICK_DURATION_MS);
        tickDistance = remainingDistance / remainingTickCounts;
        return true;
    }
}
