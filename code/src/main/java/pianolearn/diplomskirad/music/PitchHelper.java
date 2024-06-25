package pianolearn.diplomskirad.music;

import org.audiveris.proxymusic.Pitch;

public class PitchHelper {

    public static String pitchToString(Pitch pitch) {
        String alter = "";
        if (pitch.getAlter() != null) {
            if (pitch.getAlter().intValue() == 1) {
                alter = "#";
            } else if (pitch.getAlter().intValue() == -1) {
                alter = "b";
            }
        }

        return pitch.getStep().toString() + alter + pitch.getOctave();
    }
}
