package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.listener.ButtonClickWithIdListener;
import pianolearn.diplomskirad.model.LibraryItem;
import pianolearn.diplomskirad.view.BaseNavigationView;
import pianolearn.diplomskirad.view.components.library.LibraryRowView;

public class LibraryView extends BaseNavigationView {

    private final VBox centerVBox = new VBox();
    private final Label pickASongLabel = new Label();
    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox songsVBox = new VBox();
    private final LibraryRowView classicalRowView;
    private final LibraryRowView modernRowView;

    public LibraryView(LibraryItem[] classicalSongs, LibraryItem[] modernSongs) {
        classicalRowView = new LibraryRowView(classicalSongs);
        modernRowView = new LibraryRowView(modernSongs);
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
        songsVBox.getChildren().addAll(classicalRowView, modernRowView);
        scrollPane.setContent(songsVBox);
        centerVBox.getChildren().addAll(pickASongLabel, scrollPane);
        rootPane.setCenter(centerVBox);
    }

    @Override
    protected void styleViews() {
        super.styleViews();

        centerVBox.setAlignment(Pos.TOP_CENTER);

        pickASongLabel.setFont(Fonts.header);
        pickASongLabel.setTextFill(Colors.text);
        pickASongLabel.setPadding(new Insets(0, 0, 20, 0));
        pickASongLabel.setText(Strings.libraryLabel);

        scrollPane.setFitToWidth(true);
        //scrollPane.setFitToHeight(false);
        scrollPane.setPannable(true);
        scrollPane.setBackground(Styles.background(Colors.background, null));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        songsVBox.setAlignment(Pos.CENTER);
        songsVBox.setBackground(Styles.background(Colors.background, null));

        classicalRowView.setLabel(Strings.classicRow);

        modernRowView.setLabel(Strings.modernRow);
    }

    public void setCoverButtonListeners(ButtonClickWithIdListener listener) {
        classicalRowView.setCoverButtonListeners(listener);
        modernRowView.setCoverButtonListeners(listener);
    }
}
