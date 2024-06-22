package pianolearn.diplomskirad.view.screens;

import pianolearn.diplomskirad.view.BaseNavigationView;
import pianolearn.diplomskirad.view.BaseView;

public class PlayView extends BaseNavigationView {

    public PlayView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
    }

    @Override
    protected void styleViews() {
        super.styleViews();
    }

    public void setBottom(BaseView view) {
        rootPane.setBottom(view);
    }
}
