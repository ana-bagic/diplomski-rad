package pianolearn.diplomskirad.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import org.audiveris.proxymusic.ScorePartwise;
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

    private final List<MeasurePair> measurePairs = new LinkedList<>();
    private int nextMeasureIndex = 0;

    private double introDistanceLeft = CTRL_LINE_MEASURE_DISTANCE;
    private int remainingTickCounts = 0;

    private boolean isPlaying = false;
    private double playbackSpeed = 1;
    private boolean isWait = true;
    private boolean rightHandShows = true;
    private boolean leftHandShows;

    private HandChangeListener leftHandChangedListener;
    private HandChangeListener rightHandChangedListener;
    private EventListener stopClickedListener;

    private EventWithAmountListener translateMeasuresListener;

    public void init() {
        part = Score.pianoPart();
        attributes = Score.attributes(part);

        if (part == null || attributes == null) return;

        leftHandShows = attributes.staves() == 2;
    }

    private void mainLoop() {
        if (isPlaying) {
            if (introDistanceLeft > 0) {
                Timeline introTimeline = new Timeline(new KeyFrame(Duration.millis(TICK_DURATION_MS), event -> {
                    translateMeasuresListener.onAction(5);
                    mainLoop();
                }));
                introTimeline.play();
            } else {
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(TICK_DURATION_MS), event -> {
                    translateMeasuresListener.onAction(5);
                    mainLoop();
                }));
                timeline.play();
            }
        }
    }

    public MeasurePair getNextMeasure() {
        if (part == null) return null;

        List<ScorePartwise.Part.Measure> measures = part.getMeasure();
        MeasurePair measurePair = null;

        if (nextMeasureIndex < measures.size()) {
            ScorePartwise.Part.Measure measure = measures.get(nextMeasureIndex++);
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
        nextMeasureIndex = 0;
        introDistanceLeft = CTRL_LINE_MEASURE_DISTANCE;
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
