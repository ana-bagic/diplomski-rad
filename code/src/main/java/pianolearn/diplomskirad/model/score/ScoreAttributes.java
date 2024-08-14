package pianolearn.diplomskirad.model.score;

public record ScoreAttributes(

        boolean isRightHandTreble,
        boolean isLeftHandTreble,
        String timeNumerator,
        String timeDenominator,
        int fifths,
        int staves
) {}
