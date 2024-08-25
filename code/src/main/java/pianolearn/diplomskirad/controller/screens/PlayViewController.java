package pianolearn.diplomskirad.controller.screens;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.controller.components.PianoKeyboardController;
import pianolearn.diplomskirad.controller.components.SheetMusicController;
import pianolearn.diplomskirad.helper.TempoHelper;
import pianolearn.diplomskirad.helper.midi.Metronome;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.model.Hand;
import pianolearn.diplomskirad.model.PlaybackSpeed;
import pianolearn.diplomskirad.model.score.AttributesModel;
import pianolearn.diplomskirad.model.viewmodel.MeasurePairModel;
import pianolearn.diplomskirad.view.screens.PlayView;

import javax.sound.midi.MidiUnavailableException;
import java.util.LinkedList;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.*;

public class PlayViewController implements BaseViewController {

    private final PlayView view = new PlayView();

    private final SheetMusicController sheetMusicController;
    private final PianoKeyboardController pianoKeyboardController;

    private final AttributesModel attributes;
    private final long durationOfBeatUnit;
    private double actualDurationOfBeatUnit;
    private double actualDurationOfQuarter;

    private Metronome metronome = new Metronome();
    private Thread metronomeThread;

    private final List<MeasurePairModel> measurePairs = new LinkedList<>();
    private int nextPlayMeasureIndex;
    private boolean isBeginningOfMeasure;
    private double durationOfLast;

    private double remainingDistance;
    private double tickDistance;
    private int remainingTickCounts;

    private boolean isPlaying;
    private boolean isWait = true;

    public PlayViewController() {
        ScorePartwise.Part part = Score.pianoPart();
        attributes = Score.scoreAttributes(part);
        assert attributes != null;

        sheetMusicController = new SheetMusicController(part, attributes);
        pianoKeyboardController = new PianoKeyboardController();

        setupListeners();
        setupView();

        durationOfBeatUnit = TempoHelper.getDurationOfBeatUnit(attributes.bpm());
        actualDurationOfBeatUnit = durationOfBeatUnit;
        actualDurationOfQuarter = TempoHelper.getDurationOfQuarter(actualDurationOfBeatUnit, attributes.beatUnitTempo());

        resetValues();

        sheetMusicController.addNextMeasure();

        try {
            metronome.open();
            metronome.setBeats(attributes.beats());
        } catch (MidiUnavailableException e) {
            metronome = null;
        }
    }

    @Override
    public Pane getView() {
        return view;
    }

    private void setupListeners() {
        view.setBackButtonListener(() -> {
            NavigationController.INSTANCE.pop();
            metronome.close();
        });

        view.setPlayButtonListener(this::playChanged);
        view.setStopButtonListener(this::stopClicked);
        view.setSpeedSliderListener(this::speedChanged);
        view.setHandChangedListener(this::handChanged);

        sheetMusicController.setMeasurePairCreateListener(measurePairs::add);
        sheetMusicController.setNotesPlayListeners(
                pianoKeyboardController::playNotesRightHand, pianoKeyboardController::playNotesLeftHand);

        pianoKeyboardController.setPlayPauseListener(this::playChanged);

        NavigationController.INSTANCE.getStage().setOnCloseRequest(e -> metronome.close());
    }

    private void setupView() {
        view.setUsesBothHands(attributes.usesBothHands());
        view.setSheetMusicView(sheetMusicController.getView());
        view.setPianoKeyboardView(pianoKeyboardController.getView());
    }

    private void resetValues() {
        measurePairs.clear();
        nextPlayMeasureIndex = -1;
        isBeginningOfMeasure = true;

        remainingTickCounts = 0;
        isPlaying = false;
    }

    private void playChanged(boolean play) {
        isPlaying = play;
        mainLoop();
    }

    private void stopClicked() {
        resetValues();
        sheetMusicController.reset();
        pianoKeyboardController.reset();
    }

    private void speedChanged(PlaybackSpeed speed) {
        actualDurationOfBeatUnit = durationOfBeatUnit / speed.getSpeed();
        actualDurationOfQuarter = TempoHelper.getDurationOfQuarter(actualDurationOfBeatUnit, attributes.beatUnitTempo());
        isWait = speed == PlaybackSpeed.WAIT;
        pianoKeyboardController.setWait(isWait);
    }

    private void handChanged(Hand hand, boolean shows) {
        sheetMusicController.handChanged(hand, shows);
        pianoKeyboardController.handChanged(hand, shows);
    }

    private void mainLoop() {
        if (isPlaying) {
            try {
                if (remainingTickCounts == 0 && !setupNewRegion()) {
                    isPlaying = false;
                    view.setPaused();
                    return;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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

    private boolean setupNewRegion() throws InterruptedException {
        if (nextPlayMeasureIndex >= measurePairs.size()) {
            if (metronomeThread != null) metronomeThread.join();
            return false;
        }

        double durationOfRegion;

        if (nextPlayMeasureIndex < 0) {
            remainingDistance = CTRL_LINE_MEASURE_DISTANCE + BARLINE_NOTE_SPACE;
            durationOfRegion = actualDurationOfBeatUnit * attributes.beats();
            nextPlayMeasureIndex++;
        } else {
            MeasurePairModel measurePair = measurePairs.get(nextPlayMeasureIndex);
            double notesWidthWithoutLast = measurePair.getNotesWidthWithoutLast();

            if (isBeginningOfMeasure) {
                int lastDuration = measurePair.getMainHand().getLast().getDuration();
                durationOfLast = TempoHelper.getDurationOfNote(actualDurationOfQuarter, attributes.divisions(), lastDuration);

                remainingDistance = notesWidthWithoutLast;
                durationOfRegion = actualDurationOfBeatUnit * attributes.beats() - durationOfLast;
                isBeginningOfMeasure = false;

                if (metronomeThread != null) metronomeThread.join();
                setMetronome();
            } else  {
                remainingDistance = measurePair.getWidth() - notesWidthWithoutLast;
                durationOfRegion = durationOfLast;
                isBeginningOfMeasure = true;
                nextPlayMeasureIndex++;
            }
        }

        remainingTickCounts = (int) Math.round(durationOfRegion / TICK_DURATION_MS);
        tickDistance = remainingDistance / remainingTickCounts;
        return true;
    }

    private void setMetronome() {
        if (isWait) return;

        metronome.setInterval((long) (actualDurationOfBeatUnit) + 100);
        metronomeThread = new Thread(metronome);
        metronomeThread.start();
    }
}
