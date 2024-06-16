package pianolearn.diplomskirad.controller.screens;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.view.screens.UploadView;

public class UploadViewController extends BaseViewController {

    private final UploadView view = new UploadView();

    public UploadViewController() {
        super();
        setupListeners();
    }

    @Override
    public Pane getView() {
        return view.getRootPane();
    }

    private void setupListeners() {
        view.setBackButtonClicked(NavigationController.INSTANCE::pop);
    }
}
