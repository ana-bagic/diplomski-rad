package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.helper.StyleHelper;
import pianolearn.diplomskirad.listener.FileChoseListener;
import pianolearn.diplomskirad.model.viewmodel.LibraryItemModel;
import pianolearn.diplomskirad.view.BaseNavigationView;
import pianolearn.diplomskirad.view.components.library.LibraryRowView;

public class LibraryView extends BaseNavigationView {

    private final VBox centerVBox = new VBox();
    private final Label pickASongLabel = new Label();
    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox songsVBox = new VBox();
    private final LibraryRowView classicalRowView;
    private final LibraryRowView modernRowView;

    public LibraryView(LibraryItemModel[] classicalSongs, LibraryItemModel[] modernSongs) {
        classicalRowView = new LibraryRowView(classicalSongs);
        modernRowView = new LibraryRowView(modernSongs);
        setupView();
    }

    private void setupView() {
        setCenter(centerVBox);

        centerVBox.getChildren().addAll(pickASongLabel, scrollPane);
        centerVBox.setAlignment(Pos.TOP_CENTER);

        pickASongLabel.setFont(Fonts.header);
        pickASongLabel.setTextFill(Colors.text);
        pickASongLabel.setPadding(new Insets(0, 0, 20, 0));
        pickASongLabel.setText(Strings.libraryLabel);

        scrollPane.setContent(songsVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setBackground(StyleHelper.background(Colors.background, null));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        songsVBox.getChildren().addAll(classicalRowView, modernRowView);
        songsVBox.setBackground(StyleHelper.background(Colors.background, null));
        songsVBox.setAlignment(Pos.CENTER);

        classicalRowView.setLabel(Strings.classicRow);

        modernRowView.setLabel(Strings.modernRow);
    }

    public void setFileChoseListeners(FileChoseListener listener) {
        classicalRowView.setFileChoseListeners(listener);
        modernRowView.setFileChoseListeners(listener);
    }
}
