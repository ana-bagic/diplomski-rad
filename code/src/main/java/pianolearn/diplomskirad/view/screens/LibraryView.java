package pianolearn.diplomskirad.view.screens;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.listener.ButtonClickWithIdListener;
import pianolearn.diplomskirad.model.LibraryItem;
import pianolearn.diplomskirad.view.BaseNavigationView;
import pianolearn.diplomskirad.view.components.library.LibraryRowView;

public class LibraryView extends BaseNavigationView {

    private final Label header = new Label();
    private final VBox centerVBox = new VBox();
    private final LibraryRowView classicalRowView = new LibraryRowView();
    private final LibraryRowView modernRowView = new LibraryRowView();

    public LibraryView() {
        super();
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
        centerVBox.getChildren().addAll(classicalRowView, modernRowView);
        topHBox.getChildren().add(header);
        rootPane.setCenter(centerVBox);
    }

    @Override
    protected void styleViews() {
        super.styleViews();
        getStylesheets().add(Styles.LIBRARY_VIEW_STYLE);

        header.getStyleClass().addAll("header", "font-header");
        header.setText(Strings.libraryLabel);

        centerVBox.getStyleClass().add("center-v-box");

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
