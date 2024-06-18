package pianolearn.diplomskirad.view.screens;

import javafx.scene.control.Label;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.view.BaseNavigationView;

public class LibraryView extends BaseNavigationView {

    private final Label header = new Label();

    public LibraryView() {
        super();
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
        topHBox.getChildren().add(header);
    }

    @Override
    protected void styleViews() {
        super.styleViews();
        rootPane.getStylesheets().add(Styles.LIBRARY_VIEW_STYLE);

        header.getStyleClass().addAll("header", "font-header");
        header.setText(Strings.libraryLabel);
    }
}
