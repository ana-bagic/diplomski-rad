package pianolearn.diplomskirad.helper.xml;

import pianolearn.diplomskirad.helper.custom.BidirectionalMap;
import pianolearn.diplomskirad.model.score.NoteAlphabet;
import pianolearn.diplomskirad.model.score.Pitch;

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

    public static int getInterval(Pitch first, Pitch last) {
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
}