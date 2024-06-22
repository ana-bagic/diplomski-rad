package pianolearn.diplomskirad.controller.components;

import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.keyboard.PianoKeyboardView;

public class PianoKeyboardController implements BaseViewController {

    public final PianoKeyboardView view = new PianoKeyboardView();

    public PianoKeyboardController() {

    }

    @Override
    public BaseView getView() {
        return view;
    }
}
