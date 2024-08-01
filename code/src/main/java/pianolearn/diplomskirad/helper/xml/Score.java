package pianolearn.diplomskirad.helper.xml;

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
}
