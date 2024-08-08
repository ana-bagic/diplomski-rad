package pianolearn.diplomskirad.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import org.audiveris.proxymusic.Note;
import org.audiveris.proxymusic.Pitch;
import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.helper.xml.PitchHelper;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.helper.xml.ScorePartIterator;
import pianolearn.diplomskirad.listener.HandChangeListener;
import pianolearn.diplomskirad.model.PlaybackSpeed;
import pianolearn.diplomskirad.model.score.ClefTimeKeyModel;

public enum MainEngine {

    INSTANCE;

    private boolean usesOneHand;
    private ClefTimeKeyModel leftHandClefTimeKeyModel;
    private ClefTimeKeyModel rightHandClefTimeKeyModel;

    private boolean isPlaying = false;
    private PlaybackSpeed playbackSpeed = PlaybackSpeed.WAIT;
    private boolean leftHandShows;
    private boolean rightHandShows = true;

    private HandChangeListener leftHandChangedListener;
    private HandChangeListener rightHandChangedListener;

    public void init() {
        usesOneHand = Score.numberOfParts() == 1;
        leftHandShows = !usesOneHand;

        leftHandClefTimeKeyModel = Score.clefTimeKey(Score.leftHandPart());
        rightHandClefTimeKeyModel = Score.clefTimeKey(Score.rightHandPart());

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

    public boolean usesOneHand() {
        return usesOneHand;
    }

    public ClefTimeKeyModel getLeftHandClefTimeKeyModel() {
        return leftHandClefTimeKeyModel;
    }

    public ClefTimeKeyModel getRightHandClefTimeKeyModel() {
        return rightHandClefTimeKeyModel;
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

    private void play() {
        ScorePartwise.Part part = Score.rightHandPart();
        if (part == null) return;

        ScorePartIterator iterator = new ScorePartIterator(part);

        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note != null) {
                Pitch pitch = note.getPitch();

                if (pitch != null) {
                    System.out.println(PitchHelper.pitchToString(note.getPitch()) + " " + note.getType().getValue());
                } else if (note.getUnpitched() != null) {
                    System.out.println("Unpitched: " + note.getUnpitched());
                } else if (note.getRest() != null) {
                    System.out.println("\t" + note.getType().getValue());
                } else {
                    System.out.println("Something else");
                }
            }
        }
    }
}
