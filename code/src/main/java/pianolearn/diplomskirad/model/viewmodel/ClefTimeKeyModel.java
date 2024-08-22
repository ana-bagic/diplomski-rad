package pianolearn.diplomskirad.model.viewmodel;

import pianolearn.diplomskirad.constants.Config;
import pianolearn.diplomskirad.helper.BravuraHelper;
import pianolearn.diplomskirad.helper.ScaleHelper;
import pianolearn.diplomskirad.model.score.AttributesModel;

import java.util.Collections;
import java.util.List;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.*;

public record ClefTimeKeyModel(

        String clef,
        String beats,
        String beatsUnit,
        List<Integer> accidentalPositions,
        String accidental
) {

    private static final double spacing = Config.ATTRIBUTES_SPACE;
    private static final double timeAccidentalSpacing = spacing * 3;

    public static ClefTimeKeyModel fromAttributes(AttributesModel attributes, boolean rightHand) {
        String clef = trebleClef;
        String beats = time4;
        String beatsUnit = time4;
        List<Integer> accidentalPositions = Collections.emptyList();
        String accidental = sharp;

        if (attributes != null) {
            boolean isHandTreble = rightHand ? attributes.isRightHandTreble() : attributes.isLeftHandTreble();
            clef = BravuraHelper.getBravuraClef(isHandTreble);
            beats = BravuraHelper.getBravuraTime(attributes.beats());
            beatsUnit = BravuraHelper.getBravuraTime(attributes.beatUnit());

            int fifths = attributes.fifths();
            accidentalPositions = ScaleHelper.getAccidentalPositions(fifths, isHandTreble);
            accidental = BravuraHelper.getBravuraAccidentalFromFifths(fifths);
        }

        return new ClefTimeKeyModel(clef, beats, beatsUnit, accidentalPositions, accidental);
    }

    public static double spacing() {
        return spacing;
    }

    public static double timeAccidentalSpacing() {
        return timeAccidentalSpacing;
    }
}
