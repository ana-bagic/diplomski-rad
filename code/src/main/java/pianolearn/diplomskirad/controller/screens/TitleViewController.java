package pianolearn.diplomskirad.controller.screens;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.view.screens.TitleView;

public class TitleViewController extends BaseViewController {

    private final TitleView view = new TitleView();

    public TitleViewController() {
        super();
        setupListeners();
    }

    @Override
    public Pane getView() {
        return view.getRootPane();
    }

    private void setupListeners() {
        view.setUploadButtonListener(() -> NavigationController.INSTANCE.push(new UploadViewController()));
        view.setLibraryButtonListener(() -> System.out.println("Library button clicked"));
        view.setSettingsButtonListener(() -> NavigationController.INSTANCE.push(new SettingsViewController()));
    }
}