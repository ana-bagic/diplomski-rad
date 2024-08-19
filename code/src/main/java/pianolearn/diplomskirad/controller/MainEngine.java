package pianolearn.diplomskirad.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.helper.TempoHelper;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.listener.EventListener;
import pianolearn.diplomskirad.listener.EventWithAmountListener;
import pianolearn.diplomskirad.listener.HandChangeListener;
import pianolearn.diplomskirad.model.PlaybackSpeed;
import pianolearn.diplomskirad.model.score.ScoreAttributes;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;
import pianolearn.diplomskirad.model.viewmodel.MeasurePair;

import java.util.LinkedList;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.*;

public enum MainEngine {

    INSTANCE;

    private ScorePartwise.Part part;
    private ScoreAttributes attributes;
    private double durationOfQuarter;
    private double durationOfMeasure;

    private final List<MeasurePair> measurePairs = new LinkedList<>();
    private int nextDisplayMeasureIndex = 0;
    private int nextPlayMeasureIndex = -1;
    private boolean isBeginningOfMeasure = true;

    private double remainingDistance;
    private double tickDistance;
    private int remainingTickCounts = 0;

    private boolean isPlaying = false;
    private double playbackSpeed = 1;
    private boolean isWait = true;
    private boolean rightHandShows = true;
    private boolean leftHandShows;

    private HandChangeListener leftHandChangedListener;
    private HandChangeListener rightHandChangedListener;
    private EventListener stopClickedListener;

    private EventListener finishedListener;
    private EventWithAmountListener translateMeasuresListener;

    public void init() {
        part = Score.pianoPart();
        attributes = Score.attributes(part);

        if (part == null || attributes == null) return;

        leftHandShows = attributes.staves() == 2;
        durationOfQuarter = TempoHelper.getDurationOfQuarter(attributes.beatUnitTempo(), attributes.bpm());
        durationOfMeasure = TempoHelper.getDurationOfMeasure(durationOfQuarter, attributes);
    }

    private void mainLoop() {
        if (isPlaying) {
            if (remainingTickCounts == 0 && !setupNewRegion()) {
                isPlaying = false;
                finishedListener.onAction();
                return;
            }

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(TICK_DURATION_MS), event -> {
                translateMeasuresListener.onAction(remainingTickCounts == 1 ? remainingDistance : tickDistance);
                remainingDistance -= tickDistance;
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

    public MeasurePair getNextMeasure() {
        if (part == null) return null;

        List<ScorePartwise.Part.Measure> measures = part.getMeasure();
        MeasurePair measurePair = null;

        if (nextDisplayMeasureIndex < measures.size()) {
            ScorePartwise.Part.Measure measure = measures.get(nextDisplayMeasureIndex++);
            measurePair = Score.measures(measure);
            measurePairs.add(measurePair);
        }

        return measurePair;
    }

    public ScoreAttributes getAttributes() {
        return attributes;
    }

    public boolean usesBothHands() {
        return attributes.staves() == 2;
    }

    public ClefTimeKeyModel getClefTimeKey(boolean rightHandPart) {
        return Score.clefTimeKey(attributes, rightHandPart);
    }

    public void playPauseButtonClicked() {
        isPlaying = !isPlaying;
        mainLoop();
    }

    public void stopButtonClicked() {
        isPlaying = false;
        measurePairs.clear();
        nextDisplayMeasureIndex = 0;
        nextPlayMeasureIndex = -1;
        remainingTickCounts = 0;
        stopClickedListener.onAction();
    }

    public void speedChanged(PlaybackSpeed speed) {
        playbackSpeed = speed.getSpeed();
        isWait = speed == PlaybackSpeed.WAIT;
    }

    public void leftHandButtonClicked() {
        leftHandShows = !leftHandShows;
        leftHandChangedListener.onHandChanged(leftHandShows);
    }

    public void rightHandButtonClicked() {
        rightHandShows = !rightHandShows;
        rightHandChangedListener.onHandChanged(rightHandShows);
    }

    public void setLeftHandChangedListener(HandChangeListener listener) {
        leftHandChangedListener = listener;
    }

    public void setRightHandChangedListener(HandChangeListener listener) {
        rightHandChangedListener = listener;
    }

    public void setStopClickedListener(EventListener listener) {
        stopClickedListener = listener;
    }

    public void setTranslateMeasuresListener(EventWithAmountListener listener) {
        translateMeasuresListener = listener;
    }

    public void setFinishedListener(EventListener listener) {
        finishedListener = listener;
    }

    private void tempKeyPress() {
        Scene scene = NavigationController.INSTANCE.getStage().getScene();
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.T) {
                System.out.println("Key 'T' was pressed!");
            } else if (event.getCode() == KeyCode.F) {
                System.out.println("Key 'F' was pressed!");
            }
        });
    }
}
