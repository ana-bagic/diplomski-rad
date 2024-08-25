package pianolearn.diplomskirad.view.screens;

import javafx.scene.layout.*;
import pianolearn.diplomskirad.listener.EventListener;
import pianolearn.diplomskirad.listener.HandChangedListener;
import pianolearn.diplomskirad.listener.PlayChangedListener;
import pianolearn.diplomskirad.listener.SpeedChangeListener;
import pianolearn.diplomskirad.view.BaseNavigationView;
import pianolearn.diplomskirad.view.components.PlayToolbarView;

public class PlayView extends BaseNavigationView {

    private final PlayToolbarView playToolbarView = new PlayToolbarView();

    public PlayView() {
        setupView();
    }

    private void setupView() {
        topStackPane.getChildren().add(playToolbarView);

        playToolbarView.setMinWidth(HBox.USE_PREF_SIZE);
        playToolbarView.setMaxWidth(HBox.USE_PREF_SIZE);
    }

    public void setUsesBothHands(boolean usesBothHands) {
        playToolbarView.setUsesBothHands(usesBothHands);
    }

    public void setPaused() {
        playToolbarView.setPaused(true);
    }

    public void setSheetMusicView(Pane view) {
        setCenter(view);
    }

    public void setPianoKeyboardView(Pane view) {
        setBottom(view);
    }

    public void setPlayButtonListener(PlayChangedListener listener) {
        playToolbarView.setPlayButtonListener(listener);
    }

    public void setStopButtonListener(EventListener listener) {
        playToolbarView.setStopButtonListener(listener);
    }

    public void setSpeedSliderListener(SpeedChangeListener listener) {
        playToolbarView.setSpeedSliderListener(listener);
    }

    public void setHandChangedListener(HandChangedListener listener) {
        playToolbarView.setHandChangedListener(listener);
    }
}
