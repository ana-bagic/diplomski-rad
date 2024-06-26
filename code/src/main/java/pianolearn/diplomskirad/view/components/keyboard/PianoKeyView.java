package pianolearn.diplomskirad.view.components.keyboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;

public class PianoKeyView extends StackPane {

    private final Rectangle rectangle = new Rectangle();

    private boolean isWhite;

    private PianoKeyView() {
        getChildren().add(rectangle);

        minWidthProperty().bind(rectangle.widthProperty());
        minHeightProperty().bind(rectangle.heightProperty());
        maxWidthProperty().bind(rectangle.widthProperty());
        maxHeightProperty().bind(rectangle.heightProperty());

        setAlignment(Pos.BOTTOM_CENTER);
    }

    public static PianoKeyView whiteKey() {
        PianoKeyView key = new PianoKeyView();
        key.isWhite = true;
        key.rectangle.setFill(Colors.whiteKey);
        key.rectangle.setStroke(Colors.blackKey);
        key.rectangle.setStrokeWidth(1);
        return key;
    }

    public static PianoKeyView blackKey() {
        PianoKeyView key = new PianoKeyView();
        key.isWhite = false;
        key.rectangle.setFill(Colors.blackKey);
        return key;
    }

    public void addLabel(String text) {
        Label label = new Label(text);
        label.setFont(Fonts.key);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setMinWidth(30);
        label.setPadding(new Insets(0, 5, 10, 5));
        getChildren().add(label);
    }

    public void setWidth(double width) {
        rectangle.setWidth(width);
    }

    public void setHeight(double height) {
        rectangle.setHeight(height);
    }

    public boolean isWhite() {
        return isWhite;
    }
}
