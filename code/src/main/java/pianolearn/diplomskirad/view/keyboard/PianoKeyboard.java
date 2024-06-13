package pianolearn.diplomskirad.view.keyboard;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import pianolearn.diplomskirad.view.base.BaseView;

public class PianoKeyboard extends BaseView {

    private static class Key {
        Rectangle rect;
        int keyIndex;

        public Key(Rectangle rect, int keyIndex) {
            this.rect = rect;
            this.keyIndex = keyIndex;
        }
    }

    public PianoKeyboard() {
        super(new StackPane());
    }

    @Override
    protected void addViews() {

    }

    @Override
    protected void setupConstraints() {

    }
}
