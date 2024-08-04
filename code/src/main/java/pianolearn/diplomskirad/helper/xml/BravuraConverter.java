package pianolearn.diplomskirad.helper.xml;

import org.audiveris.proxymusic.ClefSign;
import pianolearn.diplomskirad.model.score.Pitch;

import java.util.List;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.*;
import static pianolearn.diplomskirad.model.score.NoteAlphabet.*;

public class BravuraConverter {

    private static final List<Integer> sharpAccidentalTreblePositions = List.of(4, 1, 5, 2, -1, 3, 0);
    private static final List<Integer> flatAccidentalTreblePositions = List.of(0, 3, -1, 2, -2, 1, -3);
    private static final List<Integer> sharpAccidentalBassPositions = List.of(2, -1, 3, 0, -3, 1, -2);
    private static final List<Integer> flatAccidentalBassPositions = List.of(-2, 1, -3, 0, -4, -1, -5);
    
    public static String getBravuraClef(ClefSign clef) {
        return clef == ClefSign.F ? bassClef : trebleClef;
    }

    public static String getBravuraTime(String time) {
        return switch (time) {
            case "1" -> time1;
            case "2" -> time2;
            case "3" -> time3;
            default -> time4;
            case "5" -> time5;
            case "6" -> time6;
            case "7" -> time7;
            case "8" -> time8;
            case "9" -> time9;
        };
    }

    public static String getBravuraAccidental(int fifths, boolean trebleClef, List<Integer> accidentals) {
        boolean isSharp = fifths >= 0;

        if (trebleClef) {
            if (isSharp) {
                accidentals.addAll(sharpAccidentalTreblePositions.subList(0, fifths));
            } else {
                accidentals.addAll(flatAccidentalTreblePositions.subList(0, -fifths));
            }
        } else {
            if (isSharp) {
                accidentals.addAll(sharpAccidentalBassPositions.subList(0, fifths));
            } else {
                accidentals.addAll(flatAccidentalBassPositions.subList(0, -fifths));
            }
        }

        return isSharp ? sharp : flat;
    }

    // treble: B4 is position 0, C6 is position 8, A3 is position -8
    // bass: D3 is position 0, E4 is position 8, C2 is position -8
    private static Integer fromPitchToPosition(Pitch pitch, boolean trebleClef) {
        if (trebleClef) {
            if (pitch.lessThanOrEquals(new Pitch(G, 3)) || !pitch.lessThanOrEquals(new Pitch(C, 6))) {
                return null;
            }
            return ScaleHelper.getInterval(new Pitch(A, 3), pitch) + 8;
        } else {
            if (pitch.lessThanOrEquals(new Pitch(B, 1)) || !pitch.lessThanOrEquals(new Pitch(E, 4))) {
                return null;
            }
            return ScaleHelper.getInterval(new Pitch(C, 2), pitch) + 8;
        }
    }
}
