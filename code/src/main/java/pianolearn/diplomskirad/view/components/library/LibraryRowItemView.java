package pianolearn.diplomskirad.view.components.library;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
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
        setPrefHeight(320);

        rootPane.setAlignment(Pos.CENTER);
        rootPane.setSpacing(10);

        Styles.setButtonSize(coverButton, 160);
        Styles.setButtonBackground(coverButton, Colors.text, Colors.highlight, 20);

        Styles.setImageViewSizeAndRadius(coverImageView, 130, 130);
        coverImageView.setMouseTransparent(true);

        songNameLabel.setFont(Fonts.body);
        songNameLabel.setTextFill(Colors.accent);
        songNameLabel.setWrapText(true);
        songNameLabel.setTextAlignment(TextAlignment.CENTER);
        songNameLabel.setAlignment(Pos.CENTER);

        artistNameLabel.setFont(Fonts.micro);
        artistNameLabel.setTextFill(Colors.text);
        artistNameLabel.setWrapText(true);
        artistNameLabel.setTextAlignment(TextAlignment.CENTER);
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
        fileName = item.fileName();
    }
}
