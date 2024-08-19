package pianolearn.diplomskirad.helper;

import pianolearn.diplomskirad.model.score.NoteType;
import pianolearn.diplomskirad.model.score.ScoreAttributes;

public class TempoHelper {

    // duration is in millisecond
    public static double getDurationOfQuarter(NoteType beatUnit, double bpm) {
        double beatUnitDuration = 60000 / bpm;
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

    public static double getDurationOfMeasure(double durationOfQuarter, ScoreAttributes attributes) {
        int beats = Integer.parseInt(attributes.beats());
        int beatUnit = Integer.parseInt(attributes.beatUnit());
        double durationOfBeatUnit = durationOfQuarter * (4.0 / beatUnit);
        return durationOfBeatUnit * beats;
    }

    public static double getDurationOfNote(double durationOfQuarter, int divisions, int duration) {
        return durationOfQuarter * ((double) duration / divisions);
    }
}
