package pianolearn.diplomskirad.view.screens;

import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.view.BaseNavigationView;

public class PlayView extends BaseNavigationView {

    public PlayView() {
        setupGUI();
    }

    @Override
    protected void styleViews() {
        super.styleViews();
        getStylesheets().add(Styles.PLAY_VIEW_STYLE);
    }
}
