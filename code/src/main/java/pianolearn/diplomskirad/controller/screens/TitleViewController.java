package pianolearn.diplomskirad.controller.screens;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.view.screens.TitleView;

public class TitleViewController extends BaseViewController {

    private final TitleView view = new TitleView();

    public TitleViewController(NavigationController navigationController) {
        super(navigationController);
        setupListeners();
    }

    @Override
    public Pane getView() {
        return view.getRootPane();
    }

    private void setupListeners() {
        view.setUploadButtonClicked(() -> System.out.println("Upload button clicked"));
        view.setLibraryButtonClicked(() -> System.out.println("Library button clicked"));
        view.setSettingsButtonClicked(() -> navigationController.push(new SettingsViewController(navigationController)));
    }
}