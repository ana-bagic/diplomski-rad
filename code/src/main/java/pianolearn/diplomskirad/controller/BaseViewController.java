package pianolearn.diplomskirad.controller;

import javafx.scene.layout.Pane;

public abstract class BaseViewController {

    protected final NavigationController navigationController;

    protected BaseViewController(NavigationController navigationController) {
        this.navigationController = navigationController;
    }

    public abstract Pane getView();
}
