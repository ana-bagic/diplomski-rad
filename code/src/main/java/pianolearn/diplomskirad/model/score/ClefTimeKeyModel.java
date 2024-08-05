package pianolearn.diplomskirad.model.score;

import java.util.List;

public record ClefTimeKeyModel(

        String clef,
        String numerator,
        String denominator,
        List<Integer> accidentalPositions,
        String accidental
) {}
