package pianolearn.diplomskirad.model.viewmodel;

import pianolearn.diplomskirad.constants.Config;

import java.util.List;

public record ClefTimeKeyModel(

        String clef,
        String beats,
        String beatsUnit,
        List<Integer> accidentalPositions,
        String accidental
) {

    private static final double spacing = Config.ATTRIBUTES_SPACE;
    private static final double timeAccidentalSpacing = spacing * 3;

    public static double spacing() {
        return spacing;
    }

    public static double timeAccidentalSpacing() {
        return timeAccidentalSpacing;
    }
}
