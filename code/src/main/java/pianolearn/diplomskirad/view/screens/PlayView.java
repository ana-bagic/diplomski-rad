package pianolearn.diplomskirad.view.screens;

import pianolearn.diplomskirad.view.BaseNavigationView;
import pianolearn.diplomskirad.view.components.keyboard.PianoKeyboardView;

public class PlayView extends BaseNavigationView {

    private final PianoKeyboardView keyboard = new PianoKeyboardView();

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
