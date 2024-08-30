package pianolearn.diplomskirad.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;
import pianolearn.diplomskirad.constants.Colors;

public enum Hand {

    RIGHT(Colors.accent),
    LEFT(Colors.highlight);

    private final BooleanProperty shows;
    private final Color keyColor;

    Hand(Color keyColor) {
        shows = new SimpleBooleanProperty(true);
        this.keyColor = keyColor;
    }

    public boolean shows() {
        return shows.get();
    }

    public Color getKeyColor() {
        return keyColor;
    }

    public void setShows(boolean shows) {
        this.shows.set(shows);
    }

    public void addShowsListener(ChangeListener<Boolean> listener) {
        shows.addListener(listener);
    }
}
