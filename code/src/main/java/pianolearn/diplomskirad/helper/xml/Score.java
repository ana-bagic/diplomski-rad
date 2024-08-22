package pianolearn.diplomskirad.helper.xml;

import org.audiveris.proxymusic.*;
import pianolearn.diplomskirad.helper.BravuraHelper;
import pianolearn.diplomskirad.helper.ScaleHelper;
import pianolearn.diplomskirad.model.score.NoteAlphabet;
import pianolearn.diplomskirad.model.score.NoteType;
import pianolearn.diplomskirad.model.score.AttributesModel;
import pianolearn.diplomskirad.model.score.PitchModel;
import pianolearn.diplomskirad.model.viewmodel.*;

import javax.xml.bind.JAXBElement;
import java.lang.Double;
import java.lang.String;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static pianolearn.diplomskirad.constants.Config.*;

public class Score {

    public static boolean noPianoPart() {
        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        if (score == null) return true;

        if (score.getPart().size() == 1) return false;

        for (Object part : score.getPartList().getPartGroupOrScorePart()) {
            if (isPiano(part)) return false;
        }
        return true;
    }

    private static boolean isPiano(Object part) {
        if (part instanceof ScorePart scorePart) {
            String partName = scorePart.getPartName().getValue();
            return partName.toUpperCase().contains("PIANO");
        }
        return false;
    }

    public static int staves() {
        Attributes attributes = attributes(pianoPart());
        if (attributes == null) return 0;

        BigInteger stavesInteger = attributes.getStaves();
        if (stavesInteger != null) {
            return stavesInteger.intValue();
        }

        return 1;
    }

    private static Attributes attributes(ScorePartwise.Part part) {
        if (part == null || part.getMeasure().isEmpty()) return null;

        ScorePartwise.Part.Measure measure = part.getMeasure().getFirst();
        if (measure == null) return null;

        Optional<Attributes> optionalAttributes = measure.getNoteOrBackupOrForward()
                .stream().filter(n -> n instanceof Attributes).map(a -> (Attributes) a).findAny();
        return optionalAttributes.orElse(null);
    }

    public static ScorePartwise.Part pianoPart() {
        if (noPianoPart()) return null;

        ScorePartwise score = XMLConverter.INSTANCE.getScore();
        if (score.getPart().size() == 1) return score.getPart().getFirst();

        for (Object part : score.getPartList().getPartGroupOrScorePart()) {
            if (!isPiano(part)) continue;

            String partId = ((ScorePart) part).getId();
            return score.getPart()
                    .stream().filter(p -> ((ScorePart) p.getId()).getId().equals(partId)).findFirst().orElse(null);
        }
        return null;
    }

    public static AttributesModel scoreAttributes(ScorePartwise.Part part) {
        Attributes attributes = attributes(part);
        if (attributes == null) return null;

        Optional<Metronome> optionalMetronome = part.getMeasure().getFirst().getNoteOrBackupOrForward()
                .stream().filter(n -> n instanceof Direction).map(d -> ((Direction) d).getDirectionType())
                .flatMap(List::stream).map(DirectionType::getMetronome)
                .filter(Objects::nonNull).filter(m -> m.getBeatUnit() != null).findAny();
        Metronome metronome = optionalMetronome.orElse(null);

        boolean isRightHandTreble = true;
        boolean isLeftHandTreble = false;
        int beats = 4;
        int beatUnit = 4;
        int bpm = 60;
        NoteType beatUnitTempo = NoteType.QUARTER;
        int divisions = 8;
        int fifths = 0;
        Set<NoteAlphabet> scale = Collections.emptySet();
        int staves = 1;

        List<Clef> clefs = attributes.getClef();
        if (clefs != null && !clefs.isEmpty()) {
            isRightHandTreble = clefs.getFirst().getSign() == ClefSign.G;
            if (clefs.size() > 1) {
                isLeftHandTreble = clefs.get(1).getSign() == ClefSign.G;
            }
        }

        List<Time> times = attributes.getTime();
        if (times != null && !times.isEmpty()) {
            List<JAXBElement<String>> time = times.getFirst().getTimeSignature();
            if (time != null) {
                for (JAXBElement<String> element : time) {
                    String localName = element.getName().getLocalPart();
                    if (localName.equalsIgnoreCase("beats")) {
                        beats = Integer.parseInt(element.getValue());
                    } else if (localName.equalsIgnoreCase("beat-type")) {
                        beatUnit = Integer.parseInt(element.getValue());
                    }
                }
            }
        }

        BigDecimal divisionsDecimal = attributes.getDivisions();
        if (divisionsDecimal != null) {
            divisions = divisionsDecimal.intValue();
        }

        List<Key> keys = attributes.getKey();
        if (keys != null && !keys.isEmpty()) {
            fifths = keys.getFirst().getFifths().intValue();
            scale = ScaleHelper.getScale(fifths);
        }

        BigInteger stavesInteger = attributes.getStaves();
        if (stavesInteger != null) {
            staves = stavesInteger.intValue();
        }

        if (metronome != null) {
            String beatUnitString = (String) metronome.getBeatUnit().getFirst();
            beatUnitTempo = NoteType.fromType(beatUnitString);
            PerMinute perMinute = metronome.getPerMinute();
            if (perMinute != null) {
                bpm = (int) Double.parseDouble(perMinute.getValue());
            }
        }

        return new AttributesModel(isRightHandTreble, isLeftHandTreble,
                beats, beatUnit, bpm, beatUnitTempo, divisions, fifths, scale, staves);
    }

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

    public static MeasurePairModel measurePair(ScorePartwise.Part.Measure measure, AttributesModel attributes) {
        if (measure == null) return null;

        LinkedList<Object> nbfList = new LinkedList<>(measure.getNoteOrBackupOrForward());
        List<MusicNodeModel> rightHandMeasure = measureModel(nbfList, true, attributes);

        boolean hasBothHands = !nbfList.isEmpty();
        List<MusicNodeModel> leftHandMeasure = hasBothHands ? measureModel(nbfList, false, attributes) : Collections.emptyList();

        MeasurePairModel measurePair = new MeasurePairModel(hasBothHands, rightHandMeasure, leftHandMeasure);
        calculateDistances(measurePair);

        return measurePair;
    }

    private static List<MusicNodeModel> measureModel(LinkedList<Object> nbfList, boolean rightHand, AttributesModel attributes) {
        boolean isTreble = rightHand ? attributes.isRightHandTreble() : attributes.isLeftHandTreble();

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

                NoteModel noteModel = noteModel(note, isTreble, attributes);
                if (noteModel == null) continue;

                if (!isNextNoteInChord) {
                    if (!musicNodeModel.isEmpty()) {
                        nodes.add(musicNodeModel);
                        musicNodeModel = new MusicNodeModel();
                    }
                }

                int duration = note.getDuration().intValue();
                musicNodeModel.addNote(noteModel, duration);

                isNextNoteInChord = false;
            } else if (nbf instanceof Backup) {
                isNextNoteInChord = true;
            }
        }

        nodes.add(musicNodeModel);
        return nodes;
    }

    private static NoteModel noteModel(Note note, boolean isTreble, AttributesModel attributes) {
        if (note.getType() == null) return null;
        String noteType = note.getType().getValue();

        Pitch pitch = note.getPitch();
        if (pitch != null) {
            String type = BravuraHelper.getBravuraNote(noteType, isStemUp(note));
            String dot = BravuraHelper.getBravuraDot(note.getDot().size());

            NoteModel noteModel = new NoteModel(type);
            noteModel.setDot(dot);

            PitchModel pitchModel = pitchModel(pitch);
            ScaleHelper.setNoteModelPitch(noteModel, pitchModel, isTreble, attributes);
            return noteModel;
        }

        if (note.getRest() != null) {
            String type = BravuraHelper.getBravuraRest(noteType);
            return new NoteModel(type);
        }

        return null;
    }

    private static PitchModel pitchModel(Pitch pitch) {
        NoteAlphabet step = NoteAlphabet.fromStep(pitch.getStep());
        BigDecimal alter = pitch.getAlter();
        int accidental = alter == null ? 0 : alter.intValue();

        return ScaleHelper.adjustPitch(step, pitch.getOctave(), accidental);
    }

    private static boolean isStemUp(Note note) {
        Stem stem = note.getStem();
        if (stem == null) return true;
        return stem.getValue().value().equalsIgnoreCase("up");
    }

    private static void calculateDistances(MeasurePairModel measurePair) {
        List<MusicNodeModel> rightHand = measurePair.getRightHandMeasure();
        List<MusicNodeModel> leftHand = measurePair.getLeftHandMeasure();
        if (rightHand.isEmpty()) return;

        int minDurationRight = rightHand.stream().mapToInt(MusicNodeModel::getDuration).min().orElse(Integer.MAX_VALUE);
        int minDurationLeft = leftHand.stream().mapToInt(MusicNodeModel::getDuration).min().orElse(Integer.MAX_VALUE);
        double minDuration = Math.min(minDurationRight, minDurationLeft);

        setDistances(rightHand, minDuration);
        if (measurePair.hasBothHands()) {
            setDistances(leftHand, minDuration);
        }

        setMainHand(measurePair);
        List<MusicNodeModel> mainHand = measurePair.getMainHand();
        double notesWidth = mainHand.stream().mapToDouble(MusicNodeModel::getDistanceToNext).sum();
        double measureWidth = notesWidth + BARLINE_NOTE_SPACE;
        double notesWidthWithoutLast = notesWidth - mainHand.getLast().getDistanceToNext();

        measurePair.setWidth(measureWidth);
        measurePair.setNotesWidthWithoutLast(notesWidthWithoutLast);
    }

    private static void setDistances(List<MusicNodeModel> measure, double minDuration) {
        double distanceFromPrev = BARLINE_NOTE_SPACE;
        for (MusicNodeModel model : measure) {
            double distanceToNext = (model.getDuration() / minDuration) * NOTE_NOTE_SPACE;
            model.setDistanceFromPrev(distanceFromPrev);
            model.setDistanceToNext(distanceToNext);
            distanceFromPrev = distanceToNext;
        }
    }

    private static void setMainHand(MeasurePairModel measurePair) {
        if (measurePair.hasBothHands()) {
            int rightLastDuration = measurePair.getRightHandMeasure().getLast().getDuration();
            int leftLastDuration = measurePair.getLeftHandMeasure().getLast().getDuration();

            measurePair.setMainHand(rightLastDuration <= leftLastDuration);
        } else {
            measurePair.setMainHand(true);
        }
    }
}
