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
        view.setUploadButtonClicked(() -> NavigationController.INSTANCE.push(new UploadViewController()));
        view.setLibraryButtonClicked(() -> System.out.println("Library button clicked"));
        view.setSettingsButtonClicked(() -> NavigationController.INSTANCE.push(new SettingsViewController()));
    }
}