package pianolearn.diplomskirad.constants;

import pianolearn.diplomskirad.model.KeyboardModel;
import pianolearn.diplomskirad.model.score.NoteAlphabet;
import pianolearn.diplomskirad.model.score.PitchModel;

public class Config {

    // metronome
    public static final int METRONOME_ACCENT_SOUND = 48;
    public static final int METRONOME_SOUND = 37;
    public static final int METRONOME_VOLUME = 600;

    // sheet music
    public static final double STAFF_LINE_SPACING = 16;
    public static final int STAFF_LINES = 5;
    public static  final int STAFF_LEDGERS = 4;
    public static final double STAFF_HEIGHT = STAFF_LINE_SPACING * (2 * STAFF_LEDGERS + STAFF_LINES - 1);
    public static final double FONT_CENTER_FIX = 8;
    public static final double NOTE_PITCH_SPACING = STAFF_LINE_SPACING / 2;

    public static final double NOTE_DISAPPEAR_X = 240;

    public static final double ATTRIBUTES_SPACE = 15;
    public static final double BARLINE_NOTE_SPACE = 30;
    public static final double NOTE_NOTE_SPACE = 70;

    public static final double CONTROL_LINE_X = 500;
    public static final double CONTROL_LINE_WIDTH = 30;
    public static final double CTRL_LINE_MEASURE_DISTANCE = 400;
    public static final double MEASURE_START_X = CONTROL_LINE_X + CTRL_LINE_MEASURE_DISTANCE;

    public static final double TICK_DURATION_MS = 30;

    // keyboard
    public static final KeyboardModel KEYBOARD_DISPLAY_MODEL =
            new KeyboardModel(new PitchModel(NoteAlphabet.A, 0), new PitchModel(NoteAlphabet.C, 8));

    public static final double WHITE_KEY_HEIGHT_MULTIPLIER = 5;
    public static final double BLACK_KEY_HEIGHT_MULTIPLIER = 4.5;
    public static final double BLACK_KEY_WIDTH_MULTIPLIER = 0.7;
}
