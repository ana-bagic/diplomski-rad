package pianolearn.diplomskirad.controller.components;

import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.model.KeyboardModel;
import pianolearn.diplomskirad.model.note.NoteAlphabet;
import pianolearn.diplomskirad.model.note.Pitch;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.keyboard.PianoKeyboardView;

public class PianoKeyboardController implements BaseViewController {

    public PianoKeyboardView view;

    public PianoKeyboardController() {
        Pitch firstPitch = new Pitch(NoteAlphabet.A, 0);
        Pitch lastPitch = new Pitch(NoteAlphabet.C, 8);
        view = new PianoKeyboardView(new KeyboardModel(firstPitch, lastPitch));
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
