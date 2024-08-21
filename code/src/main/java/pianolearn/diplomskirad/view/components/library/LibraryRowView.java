package pianolearn.diplomskirad.view.components.library;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.listener.EventWithIdListener;
import pianolearn.diplomskirad.model.viewmodel.LibraryItem;

import java.util.LinkedList;
import java.util.List;

public class LibraryRowView extends HBox {

    private final Label rowLabel = new Label();
    private final List<LibraryRowItemView> rowItems = new LinkedList<>();

    private double rowPrefHeight;

    public LibraryRowView(LibraryItem[] items) {
        for (LibraryItem item : items) {
            LibraryRowItemView view = new LibraryRowItemView(item);
            rowItems.add(view);
            rowPrefHeight = view.getPrefHeight();
        }

        setupView();
    }

    private void setupView() {
        getChildren().add(rowLabel);
        getChildren().addAll(rowItems);
        setAlignment(Pos.CENTER);
        setSpacing(40);
        setMinHeight(rowPrefHeight);
        setMaxHeight(rowPrefHeight);

        rowLabel.setFont(Fonts.body);
        rowLabel.setTextFill(Colors.text);
    }

    public void setLabel(String label) {
        rowLabel.setText(label);
    }

    public void setCoverButtonListeners(EventWithIdListener listener) {
        for (LibraryRowItemView view : rowItems) {
            view.setCoverButtonListener(listener);
        }
    }
}
