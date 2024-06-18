package pianolearn.diplomskirad.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class BaseView extends Pane {

    protected void setupGUI() {
        addViews();
        styleViews();
        setupActions();
    }

    protected abstract void addViews();

    protected void styleViews() {}

    protected void setupActions() {}

    protected void bindToSelf(Pane pane) {
        pane.prefWidthProperty().bind(widthProperty());
        pane.prefHeightProperty().bind(heightProperty());
        getChildren().add(pane);
    }

    protected static void bindImageToButton(Image image, Button button) {
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(button.widthProperty());
        imageView.fitHeightProperty().bind(button.heightProperty());
        button.setGraphic(imageView);
    }
}
