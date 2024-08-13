package pianolearn.diplomskirad.view.screens;

import javafx.scene.layout.*;
import pianolearn.diplomskirad.listener.ButtonClickListener;
import pianolearn.diplomskirad.listener.SpeedChangeListener;
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
        topStackPane.getChildren().add(playToolbarView);
    }

    @Override
    protected void styleViews() {
        super.styleViews();

        playToolbarView.setMinWidth(HBox.USE_PREF_SIZE);
        playToolbarView.setMaxWidth(HBox.USE_PREF_SIZE);
    }

    public void setSheetMusicView(BaseView view) {
        rootPane.setCenter(view);
    }

    public void setPianoKeyboardView(BaseView view) {
        rootPane.setBottom(view);
    }

    public void setUsesBothHands(boolean usesBothHands) {
        playToolbarView.setUsesBothHands(usesBothHands);
    }

    public void setPlayPauseButtonListener(ButtonClickListener listener) {
        playToolbarView.setPlayPauseButtonListener(listener);
    }

    public void setStopButtonListener(ButtonClickListener listener) {
        playToolbarView.setStopButtonListener(listener);
    }

    public void setSpeedSliderListener(SpeedChangeListener listener) {
        playToolbarView.setSpeedSliderListener(listener);
    }

    public void setLeftHandButtonListener(ButtonClickListener listener) {
        playToolbarView.setLeftHandButtonListener(listener);
    }

    public void setRightHandButtonListener(ButtonClickListener listener) {
        playToolbarView.setRightHandButtonListener(listener);
    }
}
