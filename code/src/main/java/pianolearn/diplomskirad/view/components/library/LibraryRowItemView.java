package pianolearn.diplomskirad.view.components.library;

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
        rootPane.getStyleClass().add("library-row-item");

        coverButton.getStyleClass().add("cover-button");

        coverImageView.getStyleClass().add("cover-image-view");

        songNameLabel.getStyleClass().addAll("song-label");
        songNameLabel.setFont(Fonts.body);
        songNameLabel.setTextFill(Colors.accent);

        artistNameLabel.getStyleClass().addAll("artist-label");
        artistNameLabel.setFont(Fonts.micro);
        artistNameLabel.setTextFill(Colors.text);
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
        //coverImageView.setImage(item.coverImage());
        Color buttonBackgroundColor = switch (item.difficulty()) {
            case EASY -> Colors.easy;
            case MEDIUM -> Colors.medium;
            case HARD -> Colors.hard;
        };
        coverButton.setBackground(Styles.background(buttonBackgroundColor, 20));
        fileName = item.fileName();
    }
}
