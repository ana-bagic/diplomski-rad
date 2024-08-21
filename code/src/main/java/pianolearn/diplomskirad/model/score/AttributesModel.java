package pianolearn.diplomskirad.model.score;

import java.util.Set;

public record AttributesModel(

        boolean isRightHandTreble,
        boolean isLeftHandTreble,

        int beats,
        int beatUnit,
        int bpm,
        NoteType beatUnitTempo,
        int divisions,

        int fifths,
        Set<NoteAlphabet> scale,

        int staves
) {

    public boolean usesBothHands() {
        return staves == 2;
    }
}
