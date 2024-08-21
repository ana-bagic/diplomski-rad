package pianolearn.diplomskirad.helper;

import org.audiveris.proxymusic.Pitch;
import pianolearn.diplomskirad.helper.xml.BravuraConverter;
import pianolearn.diplomskirad.model.score.NoteAlphabet;
import pianolearn.diplomskirad.model.score.PitchModel;
import pianolearn.diplomskirad.model.score.AttributesModel;
import pianolearn.diplomskirad.model.viewmodel.NoteModel;

import java.math.BigDecimal;
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

    public static List<Integer> getAccidentalPositions(int fifths, boolean trebleClef) {
        List<Integer> accidentals = new LinkedList<>();
        boolean isSharp = fifths >= 0;

        if (trebleClef) {
            if (isSharp) {
                accidentals.addAll(sharpAccidentalTreblePositions.subList(0, fifths));
            } else {
                accidentals.addAll(flatAccidentalTreblePositions.subList(0, -fifths));
            }
        } else {
            if (isSharp) {
                accidentals.addAll(sharpAccidentalBassPositions.subList(0, fifths));
            } else {
                accidentals.addAll(flatAccidentalBassPositions.subList(0, -fifths));
            }
        }

        return accidentals;
    }

    public static Set<NoteAlphabet> getScale(int fifths) {
        int adjustBy = fifths*7;
        int firstChromaNumber = adjustPitch(0, 0, adjustBy).key().getChromaNumber();

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

    public static int getInterval(PitchModel first, PitchModel last) {
        int startNoteIndex = NATURALS.get(first.key());
        int endNoteIndex = NATURALS.get(last.key());

        int interval = 0;

        if (first.octave() == last.octave()) {
            interval = endNoteIndex - startNoteIndex;
        } else {
            interval += (NATURALS.size() - startNoteIndex - 1);
            interval += NATURALS.size() * (last.octave() - first.octave() - 1);
            interval += (endNoteIndex + 1);
        }

        return interval;
    }

    // treble: B4 is position 0, C6 is position 8, A3 is position -8
    // bass: D3 is position 0, E4 is position 8, C2 is position -8
    public static int getPositionFromPitch(PitchModel pitch, boolean trebleClef) {
        if (trebleClef) {
            if (pitch.lessThanOrEquals(new PitchModel(G, 3)) || !pitch.lessThanOrEquals(new PitchModel(C, 6))) {
                return 0;
            }
            return ScaleHelper.getInterval(new PitchModel(A, 3), pitch) - 8;
        } else {
            if (pitch.lessThanOrEquals(new PitchModel(B, 1)) || !pitch.lessThanOrEquals(new PitchModel(E, 4))) {
                return 0;
            }
            return ScaleHelper.getInterval(new PitchModel(C, 2), pitch) - 8;
        }
    }

    public static void setNoteModelPitch(NoteModel noteModel, Pitch pitch, boolean trebleClef, AttributesModel attributes) {
        PitchModel pitchModel = ScaleHelper.getPitchWithAlter(pitch);
        NoteAlphabet note = pitchModel.key();

        int fifths = attributes.fifths();
        Integer accidentalInt;
        PitchModel pitchToPosition;
        if (attributes.scale().contains(note)) {
            accidentalInt = null;
        } else {
            if (fifths >= 0) {
                accidentalInt = note.isBlack() ? 1 : 0;
            } else {
                accidentalInt = note.isBlack() ? -1 : 0;
            }
        }
        if (fifths >= 0) {
            pitchToPosition = isSharp(note, fifths) ? getFlat(pitchModel) : pitchModel;
        } else {
            pitchToPosition = isFlat(note, fifths) ? getSharp(pitchModel) : pitchModel;
        }

        String accidental = BravuraConverter.getBravuraAccidentalFromAccidental(accidentalInt);
        int position = ScaleHelper.getPositionFromPitch(pitchToPosition, trebleClef);

        noteModel.setPosition(position);
        noteModel.setAccidental(accidental);
        noteModel.setPitch(pitchModel);
    }

    private static PitchModel getPitchWithAlter(Pitch pitch) {
        NoteAlphabet step = NoteAlphabet.fromStep(pitch.getStep());
        BigDecimal alter = pitch.getAlter();
        int accidental = alter == null ? 0 : alter.intValue();

        return adjustPitch(step.getChromaNumber(), pitch.getOctave(), accidental);
    }

    public static PitchModel adjustPitch(int chromaNumber, int octave, int amount) {
        int len = ALPHABET.length;
        int newChromaNumber = chromaNumber + amount;
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

    private static PitchModel getSharp(PitchModel pitch) {
        return adjustPitch(pitch.key().getChromaNumber(), pitch.octave(), 1);
    }

    private static PitchModel getFlat(PitchModel pitch) {
        return adjustPitch(pitch.key().getChromaNumber(), pitch.octave(), -1);
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
}