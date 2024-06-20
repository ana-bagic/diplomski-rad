package pianolearn.diplomskirad.view.screens;

import javafx.scene.control.Label;
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
    private final LibraryRowView classicalRowView = new LibraryRowView();
    private final LibraryRowView modernRowView = new LibraryRowView();

    public LibraryView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
        centerVBox.getChildren().addAll(pickASongLabel, classicalRowView, modernRowView);
        rootPane.setCenter(centerVBox);
    }

    @Override
    protected void styleViews() {
        super.styleViews();
        getStylesheets().add(Styles.LIBRARY_VIEW_STYLE);

        centerVBox.getStyleClass().add("center-v-box");

        pickASongLabel.setFont(Fonts.header);
        pickASongLabel.setTextFill(Colors.text);
        pickASongLabel.setText(Strings.libraryLabel);

        classicalRowView.setLabel(Strings.classicRow);

        modernRowView.setLabel(Strings.modernRow);
    }

    public void setCoverButtonListeners(ButtonClickWithIdListener listener) {
        classicalRowView.setCoverButtonListeners(listener);
        modernRowView.setCoverButtonListeners(listener);
    }

    public void setClassicalSongs(LibraryItem[] songs) {
        classicalRowView.setItems(songs);
    }

    public void setModernSongs(LibraryItem[] songs) {
        modernRowView.setItems(songs);
    }
}
