package pianolearn.diplomskirad.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class BaseView {

    protected void setupGUI() {
        addViews();
        styleViews();
        setupActions();
    }

    protected abstract void addViews();

    protected void styleViews() {
    }

    protected void setupActions() {
    }

    public abstract Pane getRootPane();

    protected void bindImageToButton(Image image, Button button) {
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(button.widthProperty());
        imageView.fitHeightProperty().bind(button.heightProperty());
        button.setGraphic(imageView);
    }
}
