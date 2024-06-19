package pianolearn.diplomskirad.view.components.library;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.listener.ButtonClickWithIdListener;
import pianolearn.diplomskirad.model.LibraryItem;
import pianolearn.diplomskirad.model.SongDifficulty;
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

        songNameLabel.getStyleClass().addAll("song-label", "font-body");

        artistNameLabel.getStyleClass().addAll("artist-label", "font-micro");
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
        coverButton.getStyleClass().add(switch (item.difficulty()) {
            case EASY -> "easy";
            case MEDIUM -> "medium";
            case HARD -> "hard";
        });
        fileName = item.fileName();
    }
}
