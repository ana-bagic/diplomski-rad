package pianolearn.diplomskirad.helper.xml;

import org.audiveris.proxymusic.*;
import pianolearn.diplomskirad.helper.ScaleHelper;
import pianolearn.diplomskirad.model.score.*;
import pianolearn.diplomskirad.model.score.PitchModel;

import javax.xml.bind.JAXBElement;
import java.lang.String;
import java.math.BigInteger;
import java.util.*;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.*;

public class Score {

    public static String title() {
        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        if (score == null) return null;

        if (score.getMovementTitle() != null) return score.getMovementTitle();
        Work work = score.getWork();
        if (work != null && work.getWorkTitle() != null) return work.getWorkTitle();

        return null;
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

    public static boolean noPianoPart() {
        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        if (score == null) return true;

        if (score.getPart().size() == 1) return false;

        for (Object part : score.getPartList().getPartGroupOrScorePart()) {
            if (isPiano(part)) return false;
        }
        return true;
    }

    public static ScorePartwise.Part pianoPart() {
        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        if (noPianoPart()) return null;

        if (score.getPart().size() == 1) return score.getPart().getFirst();

        for (Object part : score.getPartList().getPartGroupOrScorePart()) {
            if (!isPiano(part)) continue;

            String partId = ((ScorePart) part).getId();
            return score.getPart()
                    .stream().filter(p -> ((ScorePart) p.getId()).getId().equals(partId)).findFirst().orElse(null);
        }
        return null;
    }

    public static int numberOfStaves(ScorePartwise.Part part) {
        Attributes attributes = attributes(part);
        if (attributes == null) return 1;

        BigInteger staves = attributes.getStaves();
        return staves == null ? 1 : staves.intValue();
    }

    public static ClefTimeKeyModel clefTimeKey(ScorePartwise.Part part, boolean rightHand) {
        String clef = trebleClef;
        String numerator = time4;
        String denominator = time4;
        List<Integer> accidentalPositions = Collections.emptyList();
        String accidental = sharp;

        Attributes attributes = attributes(part);
        if (attributes == null) return new ClefTimeKeyModel(clef, numerator, denominator, accidentalPositions, accidental);

        List<Clef> clefs = attributes.getClef();
        if (clefs != null && !clefs.isEmpty()) {
            ClefSign clefSign = clefs.get(rightHand ? 0 : 1).getSign();
            clef = BravuraConverter.getBravuraClef(clefSign);
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
            accidentalPositions = ScaleHelper.getAccidentalPositions(fifths, clef.equals(trebleClef));
            accidental = BravuraConverter.getBravuraAccidental(fifths);
        }

        return new ClefTimeKeyModel(clef, numerator, denominator, accidentalPositions, accidental);
    }

    public static MeasureModel measure(ScorePartwise.Part.Measure measure, boolean trebleClef) {
        if (measure == null) return null;

        List<MusicNodeModel> elements = new LinkedList<>();
        List<Note> notes = measure.getNoteOrBackupOrForward()
                .stream().filter(o -> o instanceof Note).map(o -> (Note) o).toList();
        for (Note note : notes) {
            if (note.getGrace() != null || note.getCue() != null) {
                continue;
            }

            MusicNodeModel node = musicNode(note, trebleClef);
            if (node != null) {
                elements.add(node);
            }
        }

        return new MeasureModel(elements);
    }

    private static boolean isPiano(Object part) {
        if (part instanceof ScorePart scorePart) {
            String partName = scorePart.getPartName().getValue();
            return partName.toUpperCase().contains("PIANO");
        }
        return false;
    }

    private static Attributes attributes(ScorePartwise.Part part) {
        if (part == null || part.getMeasure().isEmpty()) return null;

        ScorePartwise.Part.Measure measure = part.getMeasure().getFirst();
        if (measure == null) return null;

        Optional<Object> object = measure.getNoteOrBackupOrForward()
                .stream().filter(n -> n instanceof Attributes).findAny();
        return (Attributes) object.orElse(null);

    }

    private static MusicNodeModel musicNode(Note note, boolean trebleClef) {
        if (note.getType() == null) return null;
        String noteType = note.getType().getValue();

        Pitch pitch = note.getPitch();
        if (pitch != null) {
            String type = BravuraConverter.getBravuraNote(noteType, true);
            PitchModel pitchModel = PitchModel.fromPitch(pitch);
            Integer position = ScaleHelper.getPositionFromPitch(pitchModel, trebleClef);
            if (position == null) {
                return null;
            } else {
                return new MusicNodeModel(type, position);
            }
        }

        if (note.getRest() != null) {
            String type = BravuraConverter.getBravuraRest(noteType);
            return new MusicNodeModel(type, 0);
        }

        return null;
    }
}
