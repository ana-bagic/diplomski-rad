package pianolearn.diplomskirad.view.components.keyboard;

import javafx.scene.shape.Rectangle;

public class PianoKey extends Rectangle {

    private final int keyIndex;

    public PianoKey(int keyIndex) {
        this.keyIndex = keyIndex;
    }

    public int getKeyIndex() {
        return keyIndex;
    }
}
