package pianolearn.diplomskirad.controller.screens;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.view.screens.PlayView;

public class PlayViewController extends BaseViewController {

    private final PlayView view = new PlayView();

    public PlayViewController() {
        super();
        setupListeners();
    }

    @Override
    public Pane getView() {
        return view.getRootPane();
    }

    public void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);
    }
}
