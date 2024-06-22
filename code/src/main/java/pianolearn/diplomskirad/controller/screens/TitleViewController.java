package pianolearn.diplomskirad.controller.screens;

import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.screens.TitleView;

public class TitleViewController implements BaseViewController {

    private final TitleView view = new TitleView();

    public TitleViewController() {
        setupListeners();
    }

    @Override
    public BaseView getView() {
        return view;
    }

    private void setupListeners() {
        view.setUploadButtonListener(() -> NavigationController.INSTANCE.push(new UploadViewController()));
        view.setLibraryButtonListener(() -> NavigationController.INSTANCE.push(new LibraryViewController()));
        view.setSettingsButtonListener(() -> NavigationController.INSTANCE.push(new SettingsViewController()));
    }
}