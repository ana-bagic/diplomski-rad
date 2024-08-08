package pianolearn.diplomskirad.helper;

import pianolearn.diplomskirad.model.score.NoteType;

public class TempoHelper {

    // duration is in seconds
    public static double getDurationOfQuarter(NoteType beatUnit, int bpm) {
        double beatUnitDuration = 60.0 / bpm;
        return switch (beatUnit) {
            case WHOLE -> beatUnitDuration / 4;
            case HALF -> beatUnitDuration / 2;
            case QUARTER, OTHER -> beatUnitDuration;
            case EIGHTH -> beatUnitDuration * 2;
            case TYPE16 -> beatUnitDuration * 4;
            case TYPE32 -> beatUnitDuration * 8;
            case TYPE64 -> beatUnitDuration * 16;
        };
    }

    public static double getDurationOfNote(int divisions, int duration, double durationOfQuarter) {
        return durationOfQuarter * ((double) duration / divisions);
    }
}
