package pianolearn.diplomskirad.view.components.library;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.listener.FileChoseListener;
import pianolearn.diplomskirad.model.viewmodel.LibraryItemModel;

import static pianolearn.diplomskirad.helper.StyleHelper.*;

public class LibraryRowItemView extends VBox {

    private final StackPane coverStackPane = new StackPane();
    private final Button coverButton = new Button();
    private final ImageView coverImageView = new ImageView();
    private final Label songNameLabel = new Label();
    private final Label artistNameLabel = new Label();

    private final String fileName;

    private FileChoseListener fileChoseListener;

    public LibraryRowItemView(LibraryItemModel item) {
        songNameLabel.setText(item.songName());
        artistNameLabel.setText(item.artist());
        coverImageView.setImage(item.coverImage());
        fileName = item.fileName();

        setupView();
    }

    private void setupView() {
        getChildren().addAll(coverStackPane, songNameLabel, artistNameLabel);
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPrefHeight(320);

        coverStackPane.getChildren().addAll(coverButton, coverImageView);

        setButtonSize(coverButton, 160);
        setButtonBackground(coverButton, Colors.text, Colors.highlight, 20);
        coverButton.setOnAction(e -> fileChoseListener.onAction(fileName));

        setImageViewSizeAndRadius(coverImageView, 130, 130);
        coverImageView.setMouseTransparent(true);

        setupLabel(songNameLabel, Fonts.body, Colors.accent);

        setupLabel(artistNameLabel, Fonts.micro, Colors.text);
    }

    private void setupLabel(Label label, Font font, Color color) {
        label.setFont(font);
        label.setTextFill(color);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
    }

    public void setFileChoseListener(FileChoseListener listener) {
        fileChoseListener = listener;
    }
}
