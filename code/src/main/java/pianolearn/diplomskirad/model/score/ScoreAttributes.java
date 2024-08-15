package pianolearn.diplomskirad.model.score;

import java.util.Set;

public record ScoreAttributes(

        boolean isRightHandTreble,
        boolean isLeftHandTreble,
        String timeNumerator,
        String timeDenominator,
        int fifths,
        Set<NoteAlphabet> scale,
        int staves
) {}
