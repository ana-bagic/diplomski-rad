package pianolearn.diplomskirad.model.score;

import pianolearn.diplomskirad.model.Hand;

import java.util.Set;

public record AttributesModel(

        boolean isRightHandTreble,
        boolean isLeftHandTreble,

        int beats,
        int beatUnit,
        int bpm,
        int divisions,

        int fifths,
        Set<NoteAlphabet> scale,

        int staves
) {

    public boolean usesBothHands() {
        return staves == 2;
    }

    public boolean isHandTreble(Hand hand) {
        return hand == Hand.RIGHT ? isRightHandTreble : isLeftHandTreble;
    }
}
