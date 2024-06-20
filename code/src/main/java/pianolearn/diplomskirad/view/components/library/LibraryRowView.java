package pianolearn.diplomskirad.view.components.library;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.constants.LibraryData;
import pianolearn.diplomskirad.listener.ButtonClickWithIdListener;
import pianolearn.diplomskirad.model.LibraryItem;
import pianolearn.diplomskirad.view.BaseView;

public class LibraryRowView extends BaseView {

    private final HBox rootPane = new HBox();
    private final Label rowLabel = new Label();
    private final LibraryRowItemView[] rowItems;

    public LibraryRowView() {
        this.rowItems = new LibraryRowItemView[LibraryData.NUMBER_OF_SONGS_PER_ROW];
        for (int i = 0; i < rowItems.length; i++) {
            rowItems[i] = new LibraryRowItemView();
        }
        setupGUI();
    }

    @Override
    protected void addViews() {
        rootPane.getChildren().add(rowLabel);
        rootPane.getChildren().addAll(rowItems);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setSpacing(20);

        rowLabel.setFont(Fonts.body);
        rowLabel.setTextFill(Colors.text);
    }

    public void setCoverButtonListeners(ButtonClickWithIdListener listener) {
        for (LibraryRowItemView view : rowItems) {
            view.setCoverButtonListener(listener);
        }
    }

    public void setItems(LibraryItem[] items) {
        for (int i = 0; i < items.length; i++) {
            rowItems[i].setItem(items[i]);
        }
    }

    public void setLabel(String label) {
        rowLabel.setText(label);
    }
}
