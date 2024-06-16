package pianolearn.diplomskirad.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class BaseView {

    protected void setupGUI() {
        addViews();
        styleViews();
        setupConstraints();
        setupActions();
    }

    protected abstract void addViews();

    protected void styleViews() {
    }

    protected abstract void setupConstraints();

    protected void setupActions() {
    }

    public abstract Pane getRoot();

    protected void bindImageToButton(Image image, Button button, double width, double height) {
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(button.widthProperty());
        imageView.fitHeightProperty().bind(button.heightProperty());
        button.setGraphic(imageView);

        button.setMinSize(width, height);
        button.setMaxSize(width, height);
    }
}
