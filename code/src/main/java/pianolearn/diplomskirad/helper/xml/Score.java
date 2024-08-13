package pianolearn.diplomskirad.helper.xml;

import org.audiveris.proxymusic.*;
import pianolearn.diplomskirad.controller.MainEngine;
import pianolearn.diplomskirad.helper.ScaleHelper;
import pianolearn.diplomskirad.model.score.PitchModel;
import pianolearn.diplomskirad.model.viewmodel.*;

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
            if (creator.getType().equalsIgnoreCase("composer")) {
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
                    if (localName.equalsIgnoreCase("beats")) {
                        numerator = BravuraConverter.getBravuraTime(element.getValue());
                    } else if (localName.equalsIgnoreCase("beat-type")) {
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

    public static MeasurePair measures(ScorePartwise.Part.Measure measure) {
        if (measure == null) return null;

        LinkedList<Object> nbfList = new LinkedList<>(measure.getNoteOrBackupOrForward());
        MeasureModel rightHandMeasure = measureModel(nbfList, true);

        boolean hasBothHands = !nbfList.isEmpty();
        MeasureModel leftHandMeasure = measureModel(nbfList, false);

        return new MeasurePair(hasBothHands, rightHandMeasure, leftHandMeasure);
    }

    private static MeasureModel measureModel(LinkedList<Object> nbfList, boolean rightHand) {
        boolean isTreble = MainEngine.INSTANCE.isPartTreble(rightHand);
        List<MusicNodeModel> nodes = new LinkedList<>();
        MusicNodeModel musicNodeModel = new MusicNodeModel();
        boolean isNextNoteInChord = false;

        while (!nbfList.isEmpty()) {
            Object nbf = nbfList.peek();
            if (rightHand && nbf instanceof Note note) {
                BigInteger staff = note.getStaff();
                if (staff != null && staff.intValue() == 2) {
                    break;
                }
            }

            nbf = nbfList.pop();
            if (nbf instanceof Note note) {
                if (note.getGrace() != null || note.getCue() != null) {
                    continue;
                }

                if (note.getChord() != null) {
                    isNextNoteInChord = true;
                }

                NoteModel noteModel = noteModel(note, isTreble);
                if (noteModel == null) continue;

                if (!isNextNoteInChord) {
                    if (!musicNodeModel.isEmpty()) {
                        nodes.add(musicNodeModel);
                        musicNodeModel = new MusicNodeModel();
                    }
                }

                musicNodeModel.addNote(noteModel);

                isNextNoteInChord = false;
            } else if (nbf instanceof Backup backup) {
                int duration = backup.getDuration().intValue();
                isNextNoteInChord = true;
            }
        }

        nodes.add(musicNodeModel);
        return new MeasureModel(nodes);
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

    private static NoteModel noteModel(Note note, boolean trebleClef) {
        if (note.getType() == null) return null;
        String noteType = note.getType().getValue();

        Pitch pitch = note.getPitch();
        if (pitch != null) {
            String type = BravuraConverter.getBravuraNote(noteType, isStemUp(note));
            PitchModel pitchModel = PitchModel.fromPitch(pitch);
            Integer position = ScaleHelper.getPositionFromPitch(pitchModel, trebleClef);
            if (position == null) {
                return null;
            } else {
                return new NoteModel(type, position, pitchModel);
            }
        }

        if (note.getRest() != null) {
            String type = BravuraConverter.getBravuraRest(noteType);
            return new NoteModel(type, 0, null);
        }

        return null;
    }

    private static boolean isStemUp(Note note) {
        Stem stem = note.getStem();
        if (stem == null) return true;
        return stem.getValue().value().equalsIgnoreCase("up");
    }
}
