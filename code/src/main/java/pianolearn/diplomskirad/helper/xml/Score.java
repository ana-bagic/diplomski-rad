package pianolearn.diplomskirad.helper.xml;

import org.audiveris.proxymusic.ScorePart;
import org.audiveris.proxymusic.ScorePartwise;
import org.audiveris.proxymusic.TypedText;

import java.util.List;

public class Score {

    public static String title() {
        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        return score == null ? null : score.getMovementTitle();
    }

    public static String artist() {
        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        if (score == null) return null;

        List<TypedText> creators = score.getIdentification().getCreator();
        for (TypedText creator : creators) {
            if (creator.getType().equals("composer")) {
                return creator.getValue();
            }
        }

        return null;
    }

    public static int numberOfParts() {
        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        if (score == null) return 0;

        int parts = 0;
        for (Object part : score.getPartList().getPartGroupOrScorePart()) {
            if (part instanceof ScorePart) parts++;
        }
        return parts;
    }

    public static ScorePartwise.Part getRightHandPart() {
        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        if (score == null) return null;

        return score.getPart().getFirst();
    }

    public static ScorePartwise.Part getLeftHandPart() {
        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        if (score == null || score.getPart().size() < 2) return null;

        return score.getPart().get(1);
    }
}
