package pianolearn.diplomskirad.model.score;

import pianolearn.diplomskirad.helper.ScaleHelper;

public record PitchModel(NoteAlphabet key, int octave) {

    public static PitchModel fromMidi(int midiKey) {
        NoteAlphabet noteAlphabet = ScaleHelper.ALPHABET[midiKey % 12];
        int octave = midiKey / 12 - 1;
        return new PitchModel(noteAlphabet, octave);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PitchModel pitch = (PitchModel) obj;
        return key == pitch.key && octave == pitch.octave;
    }

    @Override
    public String toString() {
        return key.getName() + octave;
    }

    public boolean lessThanOrEquals(PitchModel pitch) {
        if (equals(pitch)) return true;
        if (pitch == null) return false;

        if (octave == pitch.octave) {
            return key.getChromaNumber() < pitch.key.getChromaNumber();
        }
        return octave < pitch.octave;
    }
}
