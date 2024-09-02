package pianolearn.diplomskirad.helper;

public class TempoHelper {

    // duration is in millisecond
    public static double getDurationOfQuarter(int bpm) {
        return 60000.0 / bpm;
    }

    public static double getDurationOfBeatUnit(double durationOfQuarter, int beatUnit) {
        return durationOfQuarter * (4.0 / beatUnit);
    }

    public static double getDurationOfNote(double durationOfQuarter, int divisions, int duration) {
        return durationOfQuarter * ((double) duration / divisions);
    }
}
