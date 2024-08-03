package pianolearn.diplomskirad.controller.components;

import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Config;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.model.score.Pitch;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.keyboard.PianoKeyboardView;

public class PianoKeyboardController implements BaseViewController {

    private final PianoKeyboardView view;

    public PianoKeyboardController() {
        view = new PianoKeyboardView(Config.KEYBOARD_MODEL);
    }

    @Override
    public BaseView getView() {
        return view;
    }

    public void keyPressed(int midiKey) {
        Pitch key = Pitch.fromMidi(midiKey);
        view.setHighlight(key.toString(), Colors.accent);
    }

    public void keyReleased(int midiKey) {
        Pitch key = Pitch.fromMidi(midiKey);
        view.removeHighlight(key.toString());
    }
}
