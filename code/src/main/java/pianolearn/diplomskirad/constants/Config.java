package pianolearn.diplomskirad.constants;

import pianolearn.diplomskirad.model.KeyboardModel;
import pianolearn.diplomskirad.model.score.NoteAlphabet;
import pianolearn.diplomskirad.model.score.PitchModel;

public class Config {

    public static final boolean DEBUG = true;

    public static final boolean KEYBOARD_CONNECTED = false;

    public static final KeyboardModel KEYBOARD_MODEL =
            new KeyboardModel(new PitchModel(NoteAlphabet.A, 0), new PitchModel(NoteAlphabet.C, 8));

    // maybe put font sizes here

    // sheet music
    public static final double STAFF_LINE_SPACING = 16;
    public static final double STAFF_HEIGHT = STAFF_LINE_SPACING * 8;
    public static final double FONT_CENTER_FIX = 8;
    public static final double NOTE_PITCH_SPACING = STAFF_LINE_SPACING / 2;

    public static final double CLEF_TIME_KEY_WIDTH = 300;

    public static final double ATTRIBUTES_SPACE = 15;
    public static final double BARLINE_NOTE_SPACE = 30;
    public static final double NOTE_NOTE_SPACE = 60;

    public static final double CONTROL_LINE_X = 400;
    public static final double CONTROL_LINE_WIDTH = 30;
    public static final double CTRL_LINE_MEASURE_DISTANCE = 400;
    public static final double MEASURE_START_X = CONTROL_LINE_X + CTRL_LINE_MEASURE_DISTANCE;

    public static final double TICK_DURATION_MS = 40;

    // keyboard
    public static final double WHITE_KEY_HEIGHT_MULTIPLIER = 5;
    public static final double BLACK_KEY_HEIGHT_MULTIPLIER = 4.5;
    public static final double BLACK_KEY_WIDTH_MULTIPLIER = 0.7;
}
