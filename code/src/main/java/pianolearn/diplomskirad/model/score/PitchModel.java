package pianolearn.diplomskirad.model.score;

import org.audiveris.proxymusic.Pitch;

public record PitchModel(NoteAlphabet key, int octave) {

    public static PitchModel fromMidi(int midiKey) {
        NoteAlphabet noteAlphabet = NoteAlphabet.KEYS[midiKey % 12];
        int octave = midiKey / 12 - 1;
        return new PitchModel(noteAlphabet, octave);
    }

    public static PitchModel fromPitch(Pitch pitch) {
        NoteAlphabet noteAlphabet = NoteAlphabet.fromStep(pitch.getStep());
        int octave = pitch.getOctave();
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

    public PitchModel getIncreased() {
        int nextChromaNumber = (key.getChromaNumber() + 1) % NoteAlphabet.KEYS.length;
        NoteAlphabet newKey = NoteAlphabet.KEYS[nextChromaNumber];
        int newOctave = octave + (newKey == NoteAlphabet.C ? 1 : 0);
        return new PitchModel(newKey, newOctave);
    }
}
