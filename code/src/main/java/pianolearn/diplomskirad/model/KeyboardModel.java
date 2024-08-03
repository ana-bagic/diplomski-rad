package pianolearn.diplomskirad.model;

import pianolearn.diplomskirad.model.score.NoteAlphabet;
import pianolearn.diplomskirad.model.score.Pitch;

import static pianolearn.diplomskirad.model.score.NoteAlphabet.*;

public class KeyboardModel {

    private final Pitch firstPitch;
    private final Pitch lastPitch;
    private final int numberOfWhiteKeys;

    private static final NoteAlphabet[] KEYS = new NoteAlphabet[]{C, D, E, F, G, A, B};

    public KeyboardModel(Pitch firstPitch, Pitch lastPitch) {
        if (lastPitch.lessThanOrEquals(firstPitch)) {
            throw new IllegalArgumentException("First pitch must be less than or equal to the last pitch");
        }

        this.firstPitch = firstPitch;
        this.lastPitch = lastPitch;

        numberOfWhiteKeys = calculateWhiteKeys();
    }

    public Pitch getFirstPitch() {
        return firstPitch;
    }

    public Pitch getLastPitch() {
        return lastPitch;
    }

    public int getNumberOfWhiteKeys() {
        return numberOfWhiteKeys;
    }

    private int calculateWhiteKeys() {
        int startNoteIndex = getNoteIndex(firstPitch.key());
        int endNoteIndex = getNoteIndex(lastPitch.key());

        int whiteKeys = 0;

        if (firstPitch.octave() == lastPitch.octave()) {
            whiteKeys = endNoteIndex - startNoteIndex + 1;
        } else {
            whiteKeys += (KEYS.length - startNoteIndex);
            whiteKeys += KEYS.length * (lastPitch.octave() - firstPitch.octave() - 1);
            whiteKeys += (endNoteIndex + 1);
        }

        return whiteKeys;
    }

    private int getNoteIndex(NoteAlphabet note) {
        for (int i = 0; i < KEYS.length; i++) {
            if (KEYS[i].equals(note)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid note: " + note);
    }
}

