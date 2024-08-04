package pianolearn.diplomskirad.helper.xml;

import org.audiveris.proxymusic.*;
import pianolearn.diplomskirad.model.score.ClefTimeKey;

import javax.xml.bind.JAXBElement;
import java.lang.String;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.*;

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

    public static ScorePartwise.Part rightHandPart() {
        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        if (score == null) return null;

        return score.getPart().getFirst();
    }

    public static ScorePartwise.Part leftHandPart() {
        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        if (score == null || score.getPart().size() < 2) return null;

        return score.getPart().get(1);
    }

    public static ClefTimeKey clefTimeKey(ScorePartwise.Part.Measure measure) {
        String clef = trebleClef;
        String numerator = time4;
        String denominator = time4;
        List<Integer> accidentals = new LinkedList<>();
        String accidental = sharp;

        Optional<Object> object = measure.getNoteOrBackupOrForward()
                .stream().filter(n -> n instanceof Attributes).findAny();

        if (object.isPresent()) {
            Attributes attributes = (Attributes) object.get();

            List<Clef> clefs = attributes.getClef();
            if (clefs != null && !clefs.isEmpty()) {
                clef = BravuraConverter.getBravuraClef(clefs.getFirst().getSign());
            }

            List<Time> times = attributes.getTime();
            if (times != null && !times.isEmpty()) {
                List<JAXBElement<String>> time = times.getFirst().getTimeSignature();
                if (time != null) {
                    for (JAXBElement<String> element : time) {
                        String localName = element.getName().getLocalPart();
                        if (localName.equals("beats")) {
                            numerator = BravuraConverter.getBravuraTime(element.getValue());
                        } else if (localName.equals("beat-type")) {
                            denominator = BravuraConverter.getBravuraTime(element.getValue());
                        }
                    }
                }
            }

            List<Key> keys = attributes.getKey();
            if (keys != null && !keys.isEmpty()) {
                int fifths = keys.getFirst().getFifths().intValue();
                accidental = BravuraConverter.getBravuraAccidental(fifths, clef.equals(trebleClef), accidentals);
            }
        }

        return new ClefTimeKey(clef, numerator, denominator, accidentals, accidental);
    }
}
