package pianolearn.diplomskirad.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.helper.xml.Score;

public class SongMetadataView extends VBox {

    private final Label titleLabel = new Label();
    private final Label artistLabel = new Label();

    public SongMetadataView() {
        setupView();
    }

    private void setupView() {
        getChildren().addAll(titleLabel, artistLabel);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(0, 20, 0, 20));

        titleLabel.setFont(Fonts.header);
        titleLabel.setTextFill(Colors.text);
        String title = Score.title();
        titleLabel.setText(title == null ? Strings.titlePlaceholder : title);

        artistLabel.setFont(Fonts.body);
        artistLabel.setTextFill(Colors.accent);
        String artist = Score.artist();
        artistLabel.setText(artist == null ? Strings.artistPlaceholder : artist);
    }
}
