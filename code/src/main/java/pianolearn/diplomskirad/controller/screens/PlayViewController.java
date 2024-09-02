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
import pianolearn.diplomskirad.helper.midi.MidiDeviceManager;
import pianolearn.diplomskirad.helper.midi.MidiPlayback;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.model.PlaybackSpeed;
import pianolearn.diplomskirad.model.score.AttributesModel;
import pianolearn.diplomskirad.model.viewmodel.MeasurePairModel;
import pianolearn.diplomskirad.view.screens.PlayView;

import java.util.LinkedList;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.*;

public class PlayViewController implements BaseViewController {

    private final PlayView view = new PlayView();

    private final SheetMusicController sheetMusicController;
    private final PianoKeyboardController pianoKeyboardController;

    private final AttributesModel attributes;
    private final double durationOfQuarter;
    private double actualDurationOfQuarter;
    private double actualDurationOfBeatUnit;

//    private Metronome metronome = new Metronome();
//    private Thread metronomeThread;

    private final List<MeasurePairModel> measurePairs = new LinkedList<>();
    private int nextPlayMeasureIndex;
    private boolean isBeginningOfMeasure;

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

        durationOfQuarter = TempoHelper.getDurationOfQuarter(attributes.bpm());
        actualDurationOfQuarter = durationOfQuarter;
        actualDurationOfBeatUnit = TempoHelper.getDurationOfBeatUnit(durationOfQuarter, attributes.beatUnit());

        resetValues();

        sheetMusicController.addNextMeasure();

//        try {
//            metronome.open();
//            metronome.setBeats(attributes.beats());
//        } catch (MidiUnavailableException e) {
//            metronome = null;
//        }
    }

    @Override
    public Pane getView() {
        return view;
    }

    private void setupListeners() {
        view.setBackButtonListener(() -> {
            close();
            NavigationController.INSTANCE.pop();
        });

        view.setPlayButtonListener(this::playChanged);
        view.setStopButtonListener(this::stopClicked);
        view.setSpeedSliderListener(this::speedChanged);

        sheetMusicController.setMeasurePairCreateListener(measurePairs::add);
        sheetMusicController.setNotesPlayListener(pianoKeyboardController::playNotes);
        sheetMusicController.setNotesEndListener(pianoKeyboardController::endNotes);

        pianoKeyboardController.setPlayPauseListener(this::playChanged);

        NavigationController.INSTANCE.getStage().setOnCloseRequest(e -> close());
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

    private void close() {
        MidiDeviceManager.INSTANCE.close();
        MidiPlayback.INSTANCE.close();
//        metronome.close();
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
        actualDurationOfQuarter = durationOfQuarter / speed.getSpeed();
        actualDurationOfBeatUnit = TempoHelper.getDurationOfBeatUnit(actualDurationOfQuarter, attributes.beatUnit());
        isWait = speed == PlaybackSpeed.WAIT;
        pianoKeyboardController.setWait(isWait);
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
//            if (metronomeThread != null) metronomeThread.join();
            return false;
        }

        double actualDurationOfRegion;

        if (nextPlayMeasureIndex < 0) {
            remainingDistance = CTRL_LINE_MEASURE_DISTANCE + BARLINE_NOTE_SPACE;
            actualDurationOfRegion = actualDurationOfBeatUnit * attributes.beats();
            nextPlayMeasureIndex++;
        } else {
            MeasurePairModel measurePair = measurePairs.get(nextPlayMeasureIndex);
            int durationOfRegion;

            if (isBeginningOfMeasure) {
                remainingDistance = measurePair.getWidth() - measurePair.getLastWidth();
                durationOfRegion = measurePair.getDuration() - measurePair.getLastDuration();
                isBeginningOfMeasure = false;

//                if (metronomeThread != null) metronomeThread.join();
//                setMetronome();
            } else  {
                remainingDistance = measurePair.getLastWidth();
                durationOfRegion = measurePair.getLastDuration();
                isBeginningOfMeasure = true;
                nextPlayMeasureIndex++;
            }

            actualDurationOfRegion = TempoHelper.getDurationOfNote(actualDurationOfQuarter, attributes.divisions(), durationOfRegion);
        }

        remainingTickCounts = (int) Math.round(actualDurationOfRegion / TICK_DURATION_MS);
        tickDistance = remainingDistance / remainingTickCounts;
        return true;
    }

//    private void setMetronome() {
//        if (isWait) return;
//
//        metronome.setInterval((long) actualDurationOfBeatUnit);
//        metronomeThread = new Thread(metronome);
//        metronomeThread.start();
//    }
}
