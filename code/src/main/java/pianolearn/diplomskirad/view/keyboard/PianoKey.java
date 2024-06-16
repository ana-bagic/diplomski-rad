package pianolearn.diplomskirad.view.keyboard;

import javafx.scene.shape.Rectangle;

public class PianoKey extends Rectangle {

    private final int keyIndex;

    public PianoKey(double width, double height, int keyIndex) {
        super(width, height);
        this.keyIndex = keyIndex;
    }

    public int getKeyIndex() {
        return keyIndex;
    }
}
