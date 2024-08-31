package pianolearn.diplomskirad.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;
import pianolearn.diplomskirad.constants.Colors;

public enum Hand {

    RIGHT(Colors.rightHandColor, Colors.rightHandColorFaded),
    LEFT(Colors.leftHandColor, Colors.leftHandColorFaded);

    private final BooleanProperty shows;
    private final Color keyColor;
    private final Color keyColorFaded;

    Hand(Color keyColor, Color keyColorFaded) {
        shows = new SimpleBooleanProperty(true);
        this.keyColor = keyColor;
        this.keyColorFaded = keyColorFaded;
    }

    public boolean shows() {
        return shows.get();
    }

    public Color getKeyColor() {
        return keyColor;
    }

    public Color getKeyColorFaded() {
        return keyColorFaded;
    }

    public void setShows(boolean shows) {
        this.shows.set(shows);
    }

    public void addShowsListener(ChangeListener<Boolean> listener) {
        shows.addListener(listener);
    }
}
