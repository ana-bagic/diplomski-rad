package pianolearn.diplomskirad.controller;

import org.audiveris.proxymusic.Note;
import org.audiveris.proxymusic.Pitch;
import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.helper.xml.PitchHelper;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.helper.xml.ScorePartIterator;
import pianolearn.diplomskirad.model.PlaybackSpeed;

public enum MainEngine {

    INSTANCE;

    private boolean usesOneHand;

    private boolean isPlaying = false;
    private PlaybackSpeed playbackSpeed = PlaybackSpeed.WAIT;
    private boolean leftHandShown;
    private boolean rightHandShown = true;

    public void init() {
        usesOneHand = Score.numberOfParts() == 1;
        leftHandShown = !usesOneHand;
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
        leftHandShown = !leftHandShown;
        System.out.println("left hand " + leftHandShown);
    }

    public void rightHandButtonClicked() {
        rightHandShown = !rightHandShown;
        System.out.println("right hand " + rightHandShown);
    }

    public boolean usesOneHand() {
        return usesOneHand;
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
