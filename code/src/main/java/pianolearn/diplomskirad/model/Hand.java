package pianolearn.diplomskirad.model;

import javafx.scene.paint.Color;
import pianolearn.diplomskirad.constants.Colors;

public enum Hand {

    RIGHT(Colors.accent),
    LEFT(Colors.highlight);

    private final Color keyColor;

    Hand(Color keyColor) {
        this.keyColor = keyColor;
    }

    public Color getKeyColor() {
        return keyColor;
    }
}
