package pianolearn.diplomskirad.controller.screens;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.controller.components.PianoKeyboardController;
import pianolearn.diplomskirad.view.screens.PlayView;

public class PlayViewController implements BaseViewController {

    private final PlayView view = new PlayView();

    private final PianoKeyboardController pianoKeyboardController = new PianoKeyboardController();

    public PlayViewController() {
        setupViews();
        setupListeners();
    }

    @Override
    public Pane getView() {
        return view;
    }

    private void setupViews() {
        view.setBottom(pianoKeyboardController.getView());
    }

    private void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);
    }
}
