package pianolearn.diplomskirad.model.note;

public record Pitch(NoteAlphabet key, int octave) {

    public static Pitch fromMidi(int midiKey) {
        int octave = midiKey / 12 - 1;
        int chromaNumber = midiKey % 12;
        return new Pitch(NoteAlphabet.KEYS[chromaNumber], octave);
    }

    public NoteAlphabet getKey() {
        return key;
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

    public Pitch getIncreased() {
        int nextChromaNumber = (key.getChromaNumber() + 1) % NoteAlphabet.KEYS.length;
        NoteAlphabet newKey = NoteAlphabet.KEYS[nextChromaNumber];
        int newOctave = octave + (newKey == NoteAlphabet.C ? 1 : 0);
        return new Pitch(newKey, newOctave);
    }
}
