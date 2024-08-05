package pianolearn.diplomskirad.helper.xml;

import pianolearn.diplomskirad.helper.custom.BidirectionalMap;
import pianolearn.diplomskirad.model.score.NoteAlphabet;
import pianolearn.diplomskirad.model.score.PitchModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static pianolearn.diplomskirad.model.score.NoteAlphabet.*;

public class ScaleHelper {

    private static final BidirectionalMap<Integer, NoteAlphabet> NATURALS = BidirectionalMap.ofEntries(
            Map.entry(0, C), Map.entry(1, D), Map.entry(2, E), Map.entry(3, F),
            Map.entry(4, G), Map.entry(5, A), Map.entry(6, B)
    );
    private static final BidirectionalMap<Integer, NoteAlphabet> SHARPS = BidirectionalMap.ofEntries(
            Map.entry(0, F), Map.entry(1, C), Map.entry(2, G), Map.entry(3, D),
            Map.entry(4, A), Map.entry(5, E), Map.entry(6, B)
    );
    private static final BidirectionalMap<Integer, NoteAlphabet> FLATS = BidirectionalMap.ofEntries(
            Map.entry(0, B), Map.entry(1, E), Map.entry(2, A), Map.entry(3, D),
            Map.entry(4, G), Map.entry(5, C), Map.entry(6, F)
    );

    private static final List<Integer> sharpAccidentalTreblePositions = List.of(4, 1, 5, 2, -1, 3, 0);
    private static final List<Integer> flatAccidentalTreblePositions = List.of(0, 3, -1, 2, -2, 1, -3);
    private static final List<Integer> sharpAccidentalBassPositions = List.of(2, -1, 3, 0, -3, 1, -2);
    private static final List<Integer> flatAccidentalBassPositions = List.of(-2, 1, -3, 0, -4, -1, -5);

    public static int getInterval(PitchModel first, PitchModel last) {
        int startNoteIndex = NATURALS.getFromValue(first.key());
        int endNoteIndex = NATURALS.getFromValue(last.key());

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

    // treble: B4 is position 0, C6 is position 8, A3 is position -8
    // bass: D3 is position 0, E4 is position 8, C2 is position -8
    public static Integer getPositionFromPitch(PitchModel pitch, boolean trebleClef) {
        if (trebleClef) {
            if (pitch.lessThanOrEquals(new PitchModel(G, 3)) || !pitch.lessThanOrEquals(new PitchModel(C, 6))) {
                return null;
            }
            return ScaleHelper.getInterval(new PitchModel(A, 3), pitch) + 8;
        } else {
            if (pitch.lessThanOrEquals(new PitchModel(B, 1)) || !pitch.lessThanOrEquals(new PitchModel(E, 4))) {
                return null;
            }
            return ScaleHelper.getInterval(new PitchModel(C, 2), pitch) + 8;
        }
    }
}