package pianolearn.diplomskirad.helper;

import pianolearn.diplomskirad.model.score.NoteAlphabet;
import pianolearn.diplomskirad.model.score.PitchModel;
import pianolearn.diplomskirad.model.score.AttributesModel;
import pianolearn.diplomskirad.model.viewmodel.NoteModel;

import java.util.*;

import static pianolearn.diplomskirad.model.score.NoteAlphabet.*;

public class ScaleHelper {

    public static final NoteAlphabet[] ALPHABET = new NoteAlphabet[] {C, CSH, D, DSH, E, F, FSH, G, GSH, A, ASH, B};

    private static final Map<NoteAlphabet, Integer> NATURALS = Map.ofEntries(
            Map.entry(C, 0), Map.entry(D, 1), Map.entry(E, 2), Map.entry(F, 3),
            Map.entry(G, 4), Map.entry(A, 5), Map.entry(B, 6)
    );

    private static final List<Integer> sharpAccidentalTreblePositions = List.of(4, 1, 5, 2, -1, 3, 0);
    private static final List<Integer> flatAccidentalTreblePositions = List.of(0, 3, -1, 2, -2, 1, -3);
    private static final List<Integer> sharpAccidentalBassPositions = List.of(2, -1, 3, 0, -3, 1, -2);
    private static final List<Integer> flatAccidentalBassPositions = List.of(-2, 1, -3, 0, -4, -1, -5);

    public static Set<NoteAlphabet> getScale(int fifths) {
        int adjustBy = fifths * 7;
        int firstChromaNumber = adjustPitch(C, 0, adjustBy).key().getChromaNumber();

        int len = ALPHABET.length;
        Set<NoteAlphabet> scale = new HashSet<>();

        scale.add(ALPHABET[firstChromaNumber]);
        scale.add(ALPHABET[(firstChromaNumber + 2) % len]);
        scale.add(ALPHABET[(firstChromaNumber + 4) % len]);
        scale.add(ALPHABET[(firstChromaNumber + 5) % len]);
        scale.add(ALPHABET[(firstChromaNumber + 7) % len]);
        scale.add(ALPHABET[(firstChromaNumber + 9) % len]);
        scale.add(ALPHABET[(firstChromaNumber + 11) % len]);

        return scale;
    }

    public static PitchModel adjustPitch(NoteAlphabet key, int octave, int amount) {
        int len = ALPHABET.length;
        int newChromaNumber = key.getChromaNumber() + amount;
        int newOctave = octave;

        if (newChromaNumber >= len) {
            newOctave += newChromaNumber / len;
            newChromaNumber = newChromaNumber % len;
        } else if (newChromaNumber < 0) {
            newOctave += (newChromaNumber + 1) / len - 1;
            newChromaNumber = (newChromaNumber % len + len) % len;
        }

        return new PitchModel(ALPHABET[newChromaNumber], newOctave);
    }

    public static List<Integer> getAccidentalPositions(int fifths, boolean trebleClef) {
        boolean isSharp = fifths >= 0;

        if (trebleClef) {
            if (isSharp) {
                return sharpAccidentalTreblePositions.subList(0, fifths);
            }
            return flatAccidentalTreblePositions.subList(0, -fifths);
        }

        if (isSharp) {
            return sharpAccidentalBassPositions.subList(0, fifths);
        }
        return flatAccidentalBassPositions.subList(0, -fifths);
    }

    public static int getInterval(PitchModel first, PitchModel last) {
        int comparison = first.compareTo(last);

        int lowerNoteIndex = comparison <= 0 ? NATURALS.get(first.key()) : NATURALS.get(last.key());
        int higherNoteIndex = comparison <= 0 ? NATURALS.get(last.key()) : NATURALS.get(first.key());
        int lowerOctave = comparison <= 0 ? first.octave() : last.octave();
        int higherOctave = comparison <= 0 ? last.octave() : first.octave();

        int interval;

        if (lowerOctave == higherOctave) {
            interval = higherNoteIndex - lowerNoteIndex;
        } else {
            interval = NATURALS.size() - lowerNoteIndex - 1;
            interval += NATURALS.size() * (higherOctave - lowerOctave - 1);
            interval += higherNoteIndex + 1;
        }

        return interval * comparison * -1;
    }

    public static void setNoteModelPitch(NoteModel noteModel, PitchModel pitch, boolean trebleClef, AttributesModel attributes) {
        NoteAlphabet note = pitch.key();
        int fifths = attributes.fifths();

        PitchModel pitchToPosition;
        if (fifths >= 0) {
            pitchToPosition = isSharp(note, fifths) ? getFlat(pitch) : pitch;
        } else {
            pitchToPosition = isFlat(note, fifths) ? getSharp(pitch) : pitch;
        }

        Integer accidentalInt;
        if (attributes.scale().contains(note)) {
            accidentalInt = null;
        } else {
            if (fifths >= 0) {
                accidentalInt = note.isBlack() ? 1 : 0;
            } else {
                accidentalInt = note.isBlack() ? -1 : 0;
            }
        }

        int position = ScaleHelper.getPositionFromPitch(pitchToPosition, trebleClef);
        String accidental = BravuraHelper.getBravuraAccidentalFromAccidental(accidentalInt);

        noteModel.setPosition(position);
        noteModel.setAccidental(accidental);
        noteModel.setPitch(pitch);
    }

    private static boolean isSharp(NoteAlphabet note, int fifths) {
        if (note.isBlack()) return true;

        if (fifths > 5 && note == F) return true;
        return fifths > 6 && note == C;
    }

    private static boolean isFlat(NoteAlphabet note, int fifths) {
        if (note.isBlack()) return true;

        if (fifths > 5 && note == B) return true;
        return fifths > 6 && note == E;
    }

    private static PitchModel getSharp(PitchModel pitch) {
        return adjustPitch(pitch.key(), pitch.octave(), 1);
    }

    private static PitchModel getFlat(PitchModel pitch) {
        return adjustPitch(pitch.key(), pitch.octave(), -1);
    }

    // treble: B4 is position 0, C6 is position 8, A3 is position -8
    // bass: D3 is position 0, E4 is position 8, C2 is position -8
    private static int getPositionFromPitch(PitchModel pitch, boolean trebleClef) {
        if (trebleClef) {
            if (pitch.compareTo(new PitchModel(E, 3)) < 0 || pitch.compareTo(new PitchModel(F, 6)) > 0) {
                return 0;
            }
            return getInterval(new PitchModel(B, 4), pitch);
        } else {
            if (pitch.compareTo(new PitchModel(G, 1)) < 0 || pitch.compareTo(new PitchModel(A, 4)) > 0) {
                return 0;
            }
            return getInterval(new PitchModel(D, 3), pitch);
        }
    }
}