package pianolearn.diplomskirad.constants;

import pianolearn.diplomskirad.model.KeyboardModel;
import pianolearn.diplomskirad.model.note.NoteAlphabet;
import pianolearn.diplomskirad.model.note.Pitch;

public class Config {

    public static final boolean DEBUG = true;

    public static final boolean KEYBOARD_CONNECTED = false;

    public static final KeyboardModel KEYBOARD_MODEL =
            new KeyboardModel(new Pitch(NoteAlphabet.A, 0), new Pitch(NoteAlphabet.C, 8));

    // maybe put font sizes here

    // sheet music
    public static final double STAFF_LINE_SPACING = 18;
    public static final double NOTE_PITCH_SPACING = STAFF_LINE_SPACING / 2;

    // keyboard
    public static final double WHITE_KEY_HEIGHT_MULTIPLIER = 5;
    public static final double BLACK_KEY_HEIGHT_MULTIPLIER = 4.5;
    public static final double BLACK_KEY_WIDTH_MULTIPLIER = 0.7;
}
