package pianolearn.diplomskirad.view.screens;

import pianolearn.diplomskirad.view.BaseNavigationView;
import pianolearn.diplomskirad.view.components.keyboard.PianoKeyboard;

public class PlayView extends BaseNavigationView {

    private final PianoKeyboard keyboard = new PianoKeyboard(50);

    public PlayView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
        rootPane.setBottom(keyboard);
    }

    @Override
    protected void styleViews() {
        super.styleViews();
    }
}
