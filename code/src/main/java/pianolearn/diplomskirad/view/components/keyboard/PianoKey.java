package pianolearn.diplomskirad.view.components.keyboard;

import javafx.scene.shape.Rectangle;
import pianolearn.diplomskirad.constants.Colors;

public class PianoKey extends Rectangle {

    private boolean isWhite;

    public boolean isWhite() {
        return isWhite;
    }

    public static PianoKey whiteKey() {
        PianoKey key = new PianoKey();
        key.isWhite = true;
        key.setFill(Colors.whiteKey);
        key.setStroke(Colors.blackKey);
        key.setStrokeWidth(PianoKeyboardView.STROKE_WIDTH);
        return key;
    }

    public static PianoKey blackKey() {
        PianoKey key = new PianoKey();
        key.isWhite = false;
        key.setFill(Colors.blackKey);
        return key;
    }
}
