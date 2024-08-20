package pianolearn.diplomskirad.helper.xml;

import pianolearn.diplomskirad.model.score.NoteType;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.*;

public class BravuraConverter {
    
    public static String getBravuraClef(boolean isTreble) {
        return isTreble ? trebleClef : bassClef;
    }

    public static String getBravuraTime(int time) {
        return switch (time) {
            case 1 -> time1;
            case 2 -> time2;
            case 3 -> time3;
            default -> time4;
            case 5 -> time5;
            case 6 -> time6;
            case 7 -> time7;
            case 8 -> time8;
            case 9 -> time9;
        };
    }

    public static String getBravuraAccidentalFromFifths(int fifths) {
        return fifths >= 0 ? sharp : flat;
    }

    public static String getBravuraAccidentalFromAccidental(Integer accidental) {
        if (accidental == null) return "";
        return switch (accidental) {
            case 1 -> sharp;
            case -1 -> flat;
            case 0 -> natural;
            default -> "";
        };
    }

    public static String getBravuraDot(int size) {
        return size == 0 ? "" : dot;
    }

    public static String getBravuraNote(String type, boolean up) {
        NoteType noteType = NoteType.fromType(type);
        return switch (noteType) {
            case WHOLE -> wholeNote;
            case HALF -> up ? halfNoteUp : halfNoteDown;
            case QUARTER, OTHER -> up ? quarterNoteUp : quarterNoteDown;
            case EIGHTH -> up ? note8Up : note8Down;
            case TYPE16 -> up ? note16Up : note16Down;
            case TYPE32 -> up ? note32Up : note32Down;
            case TYPE64 -> up ? note64Up : note64Down;
        };
    }

    public static String getBravuraRest(String type) {
        NoteType noteType = NoteType.fromType(type);
        return switch (noteType) {
            case WHOLE -> wholeRest;
            case HALF -> halfRest;
            case QUARTER, OTHER -> quarterRest;
            case EIGHTH -> rest8;
            case TYPE16 -> rest16;
            case TYPE32 -> rest32;
            case TYPE64 -> rest64;
        };
    }
}
