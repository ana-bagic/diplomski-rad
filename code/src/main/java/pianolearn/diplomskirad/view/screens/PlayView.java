package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Insets;
import pianolearn.diplomskirad.listener.ButtonClickListener;
import pianolearn.diplomskirad.view.BaseNavigationView;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.PlayToolbarView;

public class PlayView extends BaseNavigationView {

    private final PlayToolbarView playToolbarView = new PlayToolbarView();

    public PlayView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
        topHBox.getChildren().add(playToolbarView);
    }

    @Override
    protected void styleViews() {
        super.styleViews();
        Insets insets = topHBox.getPadding();
        topHBox.setPadding(new Insets(20, insets.getRight(), 20, insets.getLeft()));
    }

    public void setSheetMusicView(BaseView view) {
        rootPane.setCenter(view);
    }

    public void setPianoKeyboardView(BaseView view) {
        rootPane.setBottom(view);
    }

    public void setLeftHandButtonListener(ButtonClickListener listener) {
        playToolbarView.setLeftHandButtonListener(listener);
    }

    public void setRightHandButtonListener(ButtonClickListener listener) {
        playToolbarView.setRightHandButtonListener(listener);
    }
}
