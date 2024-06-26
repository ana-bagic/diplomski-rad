package pianolearn.diplomskirad.view.components.keyboard;

import javafx.scene.shape.Rectangle;
import pianolearn.diplomskirad.constants.Colors;

public class PianoKeyView extends Rectangle {

    private boolean isWhite;

    public boolean isWhite() {
        return isWhite;
    }

    public static PianoKeyView whiteKey() {
        PianoKeyView key = new PianoKeyView();
        key.isWhite = true;
        key.setFill(Colors.whiteKey);
        key.setStroke(Colors.blackKey);
        key.setStrokeWidth(PianoKeyboardView.STROKE_WIDTH);
        return key;
    }

    public static PianoKeyView blackKey() {
        PianoKeyView key = new PianoKeyView();
        key.isWhite = false;
        key.setFill(Colors.blackKey);
        return key;
    }

    public void addLabel(String text) {

    }
}
