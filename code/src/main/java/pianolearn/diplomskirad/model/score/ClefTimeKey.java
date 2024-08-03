package pianolearn.diplomskirad.model.score;

import java.util.List;

public record ClefTimeKey(

        String clef,
        String numerator,
        String denominator,
        List<Integer> accidentals,
        String accidental
) {}
