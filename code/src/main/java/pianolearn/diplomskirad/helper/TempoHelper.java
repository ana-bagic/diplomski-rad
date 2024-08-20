package pianolearn.diplomskirad.helper;

import pianolearn.diplomskirad.model.score.NoteType;

public class TempoHelper {

    // duration is in millisecond
    public static long getDurationOfBeatUnit(int bpm) {
        return 60000 / bpm;
    }

    public static double getDurationOfQuarter(double durationOfBeatUnit, NoteType beatUnit) {
        return switch (beatUnit) {
            case WHOLE -> durationOfBeatUnit / 4;
            case HALF -> durationOfBeatUnit / 2;
            case QUARTER, OTHER -> durationOfBeatUnit;
            case EIGHTH -> durationOfBeatUnit * 2;
            case TYPE16 -> durationOfBeatUnit * 4;
            case TYPE32 -> durationOfBeatUnit * 8;
            case TYPE64 -> durationOfBeatUnit * 16;
        };
    }

    public static double getDurationOfNote(double durationOfQuarter, int divisions, int duration) {
        return durationOfQuarter * ((double) duration / divisions);
    }
}
