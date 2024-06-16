package pianolearn.diplomskirad.controller;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.view.screens.TitleView;

public class TitleViewController extends BaseViewController{

    private final TitleView view = new TitleView();

    public TitleViewController(NavigationController navigationController) {
        super(navigationController);
        setupListeners();
    }

    @Override
    public Pane getView() {
        return view.getRoot();
    }

    private void setupListeners() {
        view.setUploadButtonClicked(() -> System.out.println("Upload button clicked"));
        view.setLibraryButtonClicked(() -> System.out.println("Library button clicked"));
        view.setSettingsButtonClicked(() -> System.out.println("Settings button clicked"));
    }
}