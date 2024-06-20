package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox centerVBox = new VBox();
    private final Label pickASongLabel = new Label();
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
        centerVBox.getChildren().addAll(pickASongLabel, classicalRowView, modernRowView);
        scrollPane.setContent(centerVBox);
        rootPane.setCenter(scrollPane);
    }

    @Override
    protected void styleViews() {
        super.styleViews();

        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setBackground(Styles.background(Colors.background, null));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        centerVBox.setAlignment(Pos.TOP_CENTER);
        centerVBox.setBackground(Styles.background(Colors.background, null));

        pickASongLabel.setFont(Fonts.header);
        pickASongLabel.setTextFill(Colors.text);
        pickASongLabel.setPadding(new Insets(0, 0, 20, 0));
        pickASongLabel.setText(Strings.libraryLabel);

        classicalRowView.setLabel(Strings.classicRow);

        modernRowView.setLabel(Strings.modernRow);
    }

    public void setCoverButtonListeners(ButtonClickWithIdListener listener) {
        classicalRowView.setCoverButtonListeners(listener);
        modernRowView.setCoverButtonListeners(listener);
    }
}
