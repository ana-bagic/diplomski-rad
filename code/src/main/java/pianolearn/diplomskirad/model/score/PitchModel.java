package pianolearn.diplomskirad.model.score;

import static pianolearn.diplomskirad.helper.ScaleHelper.ALPHABET;

public record PitchModel(NoteAlphabet key, int octave) implements Comparable<PitchModel> {

    public static PitchModel fromMidi(int midiKey) {
        NoteAlphabet noteAlphabet = ALPHABET[midiKey % ALPHABET.length];
        int octave = midiKey / ALPHABET.length - 1;
        return new PitchModel(noteAlphabet, octave);
    }

    public int toMidi() {
        return (octave + 1) * ALPHABET.length + key.getChromaNumber();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        PitchModel pitch = (PitchModel) other;
        return key == pitch.key && octave == pitch.octave;
    }

    @Override
    public int compareTo(PitchModel other) {
        if (equals(other)) return 0;
        if (other == null) return -1;

        if (octave == other.octave) {
            return Integer.compare(key.getChromaNumber(), other.key.getChromaNumber());
        }
        return Integer.compare(octave, other.octave);
    }

    @Override
    public String toString() {
        return key.getName() + octave;
    }
}
