package pianolearn.diplomskirad.controller.screens;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.view.screens.SettingsView;

public class SettingsViewController extends BaseViewController {

    private final SettingsView view = new SettingsView();

    public SettingsViewController(NavigationController navigationController) {
        super(navigationController);
        setupListeners();
    }

    @Override
    public Pane getView() {
        return view.getRootPane();
    }

    private void setupListeners() {
        view.setBackButtonClicked(navigationController::pop);
    }
}
