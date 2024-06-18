package pianolearn.diplomskirad.view.screens;

import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.view.BaseNavigationView;

public class PlayView extends BaseNavigationView {

    public PlayView() {
        super();
        setupGUI();
    }

    @Override
    protected void styleViews() {
        super.styleViews();
        rootPane.getStylesheets().add(Styles.PLAY_VIEW_STYLE);
    }
}
