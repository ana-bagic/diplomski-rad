package pianolearn.diplomskirad.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.listener.HandChangeListener;
import pianolearn.diplomskirad.model.PlaybackSpeed;
import pianolearn.diplomskirad.model.score.ScoreAttributes;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;
import pianolearn.diplomskirad.model.viewmodel.MeasurePair;

import java.util.LinkedList;
import java.util.List;

public enum MainEngine {

    INSTANCE;

    private ScorePartwise.Part part;
    private ScoreAttributes attributes;

    private final List<MeasurePair> measurePairs = new LinkedList<>();
    private int nextMeasureIndex = 0;

    private boolean isPlaying = false;
    private PlaybackSpeed playbackSpeed = PlaybackSpeed.WAIT;
    private boolean rightHandShows = true;
    private boolean leftHandShows;

    private HandChangeListener leftHandChangedListener;
    private HandChangeListener rightHandChangedListener;

    public void init() {
        part = Score.pianoPart();
        attributes = Score.attributes(part);

        if (part == null || attributes == null) return;

        leftHandShows = attributes.staves() == 2;

        tempKeyPress();
    }

    public void playPauseButtonClicked() {
        isPlaying = !isPlaying;
        System.out.println("playing " + isPlaying);
    }

    public void stopButtonClicked() {
        isPlaying = false;
        System.out.println("stopped");
    }

    public void speedChanged(PlaybackSpeed speed) {
        playbackSpeed = speed;
        System.out.println("speed " + playbackSpeed.getLabel());
    }

    public void leftHandButtonClicked() {
        leftHandShows = !leftHandShows;
        leftHandChangedListener.onHandChanged(leftHandShows);
    }

    public void rightHandButtonClicked() {
        rightHandShows = !rightHandShows;
        rightHandChangedListener.onHandChanged(rightHandShows);
    }

    public ScoreAttributes getAttributes() {
        return attributes;
    }

    public MeasurePair getNextMeasure() {
        if (part == null) return null;

        List<ScorePartwise.Part.Measure> measures = part.getMeasure();
        if (nextMeasureIndex < measures.size()) {
            ScorePartwise.Part.Measure measure = measures.get(nextMeasureIndex);
            MeasurePair measurePair = Score.measures(measure);
            System.out.println("created new measure");
            measurePairs.add(measurePair);
            nextMeasureIndex++;
            return measurePair;
        }

        return null;
    }

    public boolean usesBothHands() {
        return attributes.staves() == 2;
    }

    public ClefTimeKeyModel getClefTimeKey(boolean rightHandPart) {
        return Score.clefTimeKey(attributes, rightHandPart);
    }

    public void setLeftHandChangedListener(HandChangeListener listener) {
        leftHandChangedListener = listener;
    }

    public void setRightHandChangedListener(HandChangeListener listener) {
        rightHandChangedListener = listener;
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

//    private void play() {
//        ScorePartwise.Part part = Score.rightHandPart();
//        if (part == null) return;
//
//        ScorePartIterator iterator = new ScorePartIterator(part);
//
//        while (iterator.hasNext()) {
//            Note note = iterator.next();
//            if (note != null) {
//                Pitch pitch = note.getPitch();
//
//                if (pitch != null) {
//                    System.out.println(PitchHelper.pitchToString(note.getPitch()) + " " + note.getType().getValue());
//                } else if (note.getUnpitched() != null) {
//                    System.out.println("Unpitched: " + note.getUnpitched());
//                } else if (note.getRest() != null) {
//                    System.out.println("\t" + note.getType().getValue());
//                } else {
//                    System.out.println("Something else");
//                }
//            }
//        }
//    }
}
