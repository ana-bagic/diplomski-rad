package pianolearn.diplomskirad.controller.screens;

import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.screens.SettingsView;

public class SettingsViewController implements BaseViewController {

    private final SettingsView view = new SettingsView();

    public SettingsViewController() {
        setupListeners();
    }

    @Override
    public BaseView getView() {
        return view;
    }

    private void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);
    }
}
