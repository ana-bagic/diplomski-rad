package pianolearn.diplomskirad.view.components.library;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.listener.ButtonClickWithIdListener;
import pianolearn.diplomskirad.model.LibraryItem;
import pianolearn.diplomskirad.view.BaseView;

public class LibraryRowItemView extends BaseView {

    private final VBox rootPane = new VBox();
    private final StackPane coverStackPane = new StackPane();
    private final Button coverButton = new Button();
    private final ImageView coverImageView = new ImageView();
    private final Label songNameLabel = new Label();
    private final Label artistNameLabel = new Label();

    private ButtonClickWithIdListener coverButtonListener;
    private String fileName;

    public LibraryRowItemView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        coverStackPane.getChildren().addAll(coverButton, coverImageView);
        rootPane.getChildren().addAll(coverStackPane, songNameLabel, artistNameLabel);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setSpacing(10);

        Styles.setButtonSize(coverButton, 300);

        coverImageView.getStyleClass().add("cover-image-view");
        coverImageView.setFitWidth(80);
        coverImageView.setFitHeight(80);
        Styles.setImageViewSizeAndRadius(coverImageView, 100, 50);

        songNameLabel.setFont(Fonts.body);
        songNameLabel.setTextFill(Colors.accent);
        songNameLabel.setAlignment(Pos.CENTER);

        artistNameLabel.setFont(Fonts.micro);
        artistNameLabel.setTextFill(Colors.text);
        artistNameLabel.setAlignment(Pos.CENTER);
    }

    @Override
    protected void setupActions() {
        coverButton.setOnAction(e -> coverButtonListener.onButtonClicked(fileName));
    }

    public void setCoverButtonListener(ButtonClickWithIdListener listener) {
        coverButtonListener = listener;
    }

    public void setItem(LibraryItem item) {
        songNameLabel.setText(item.songName());
        artistNameLabel.setText(item.artist());
        coverImageView.setImage(item.coverImage());
        Color buttonBackgroundColor = switch (item.difficulty()) {
            case EASY -> Colors.easy;
            case MEDIUM -> Colors.medium;
            case HARD -> Colors.hard;
        };
        coverButton.setBackground(Styles.background(buttonBackgroundColor, 20));
        fileName = item.fileName();
    }
}
