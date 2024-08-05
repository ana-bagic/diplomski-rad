package pianolearn.diplomskirad.helper.xml;

import org.audiveris.proxymusic.ClefSign;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.*;

public class BravuraConverter {
    
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

    public static String getBravuraAccidental(int fifths) {
        return fifths >= 0 ? sharp : flat;
    }

    public static String getBravuraRest(String type) {
        return switch (type) {
            default -> wholeRest;
            case "half" -> halfRest;
            case "quarter" -> quarterRest;
            case "eighth" -> rest8;
            case "16th" -> rest16;
            case "32nd" -> rest32;
            case "64th" -> rest64;
        };
    }

    public static String getBravuraNote(String type, boolean up) {
        return switch (type) {
            default -> wholeNote;
            case "half" -> up ? halfNoteUp : halfNoteDown;
            case "quarter" -> up ? quarterNoteUp : quarterNoteDown;
            case "eighth" -> up ? note8Up : note8Down;
            case "16th" -> up ? note16Up : note16Down;
            case "32nd" -> up ? note32Up : note32Down;
            case "64th" -> up ? note64Up : note64Down;
        };
    }
}
