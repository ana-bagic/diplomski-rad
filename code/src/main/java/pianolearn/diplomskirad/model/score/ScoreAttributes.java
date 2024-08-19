package pianolearn.diplomskirad.model.score;

import java.util.Set;

public record ScoreAttributes(

        boolean isRightHandTreble,
        boolean isLeftHandTreble,

        String beats,
        String beatUnit,
        double bpm,
        NoteType beatUnitTempo,
        int divisions,

        int fifths,
        Set<NoteAlphabet> scale,

        int staves
) {}
