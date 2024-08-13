package pianolearn.diplomskirad.model.viewmodel;

import java.util.List;

public record ClefTimeKeyModel(

        String clef,
        String numerator,
        String denominator,
        List<Integer> accidentalPositions,
        String accidental
) {}
