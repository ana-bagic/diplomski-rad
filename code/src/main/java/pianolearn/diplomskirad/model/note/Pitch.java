package pianolearn.diplomskirad.model.note;

import java.util.Objects;

public class Pitch {

    private NoteAlphabet key;
    private int octave;

    public Pitch(NoteAlphabet key, int octave) {
        this.key = key;
        this.octave = octave;
    }

    public NoteAlphabet getKey() {
        return key;
    }

    public int getOctave() {
        return octave;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, octave);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pitch pitch = (Pitch) obj;
        return key == pitch.key && octave == pitch.octave;
    }

    @Override
    public String toString() {
        return key.getName() + octave;
    }

    public boolean lessThanOrEquals(Pitch pitch) {
        if (equals(pitch)) return true;
        if (pitch == null) return false;
        if (octave == pitch.octave) {
            return key.getChromaNumber() < pitch.key.getChromaNumber();
        }
        return octave < pitch.octave;
    }

    public void increase() {
        int nextChromaNumber = (key.getChromaNumber() + 1) % NoteAlphabet.KEYS.length;
        key = NoteAlphabet.KEYS[nextChromaNumber];
        if (key == NoteAlphabet.C) {
            octave++;
        }
    }
}
