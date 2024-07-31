package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.view.BaseView;

import static pianolearn.diplomskirad.constants.Config.*;

public class StaffView extends BaseView {

    private final Pane rootPane = new Pane();
    private final Line[] lines = new Line[5];

    public StaffView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        for (int i = 0; i < lines.length; i++) {
            double y = i * STAFF_LINE_SPACING;
            Line line = new Line(0, y, 0, y);
            lines[i] = line;
            rootPane.getChildren().add(line);
        }

        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        double staffHeight = 4 * STAFF_LINE_SPACING + 1;
        setMinHeight(staffHeight);
        setMaxHeight(staffHeight);

        for (Line line : lines) {
            line.setStroke(Colors.blackKey);
            line.setStrokeWidth(1);
            line.endXProperty().bind(rootPane.widthProperty());
        }
    }
}
