package pianolearn.diplomskirad.controller;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.view.screens.TitleView;

public class TitleViewController extends BaseViewController {

    private final TitleView view = new TitleView();

    public TitleViewController(NavigationController navigationController) {
        super(navigationController);
    }

    public Pane getView() {
        return view.getRoot();
    }
}