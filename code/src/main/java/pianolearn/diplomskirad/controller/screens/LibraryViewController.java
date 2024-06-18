package pianolearn.diplomskirad.controller.screens;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.view.screens.LibraryView;

public class LibraryViewController implements BaseViewController {

    private final LibraryView view = new LibraryView();

    public LibraryViewController() {
        super();
        setupListeners();
    }

    @Override
    public Pane getView() {
        return view;
    }

    public void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);
    }
}
