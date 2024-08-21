package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.listener.PlayNotesListener;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;
import pianolearn.diplomskirad.model.viewmodel.MusicNodeModel;

import java.util.LinkedList;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.*;

public class SheetMusicPartView extends StackPane {

    private final Pane staffView = new Pane();
    private final ClefTimeKeyView clefTimeKeyView = new ClefTimeKeyView();
    private final Pane notesView = new Pane();
    private final LinkedList<MeasureView> measureViews = new LinkedList<>();
    private final Pane controlLineContainer = new Pane();
    private final Rectangle controlLine = new Rectangle();

    private PlayNotesListener  playNotesListener;

    public SheetMusicPartView() {
        setupView();
    }

    private void setupView() {
        getChildren().addAll(staffView, notesView, controlLineContainer);

        for (int i = 0; i < STAFF_LINES; i++) {
            double y = (i + STAFF_LEDGERS) * STAFF_LINE_SPACING;
            Line line = new Line(0, y, 0, y);
            line.setStroke(Colors.notes);
            line.setStrokeWidth(1);
            line.endXProperty().bind(widthProperty());
            staffView.getChildren().add(line);
        }
        staffView.getChildren().add(clefTimeKeyView);
        staffView.setMinHeight(STAFF_HEIGHT);
        staffView.setMaxHeight(STAFF_HEIGHT);

        notesView.setMinHeight(STAFF_HEIGHT);
        notesView.setMaxHeight(STAFF_HEIGHT);

        controlLineContainer.getChildren().add(controlLine);

        controlLine.setLayoutX(CONTROL_LINE_X);
        controlLine.setLayoutY(0);
        controlLine.setWidth(CONTROL_LINE_WIDTH);
        controlLine.setHeight(STAFF_HEIGHT);
        controlLine.setFill(Colors.controlLine);
    }

    public void setClefTimeKey(ClefTimeKeyModel model) {
        clefTimeKeyView.setModel(model);
    }

    public void addMeasure(List<MusicNodeModel> measure, double measureStartX) {
        MeasureView measureView = new MeasureView(measure);
        measureView.setPlayNotesListener(playNotesListener);
        measureViews.add(measureView);
        notesView.getChildren().add(measureView);
        measureView.setLayoutX(measureStartX);
    }

    public void translateMeasures(double amount) {
        measureViews.forEach(m -> m.translate(amount));
    }

    public void removeMeasuresIfNeeded() {
        while (measureViews.size() >= 2) {
            MeasureView nextMeasure = measureViews.get(1);
            if (nextMeasure.getLayoutX() > 0) break;
            measureViews.pop();
            notesView.getChildren().remove(1);
        }
    }

    public void reset() {
        measureViews.clear();
        notesView.getChildren().clear();
    }

    public void setPlayNotesListener(PlayNotesListener listener) {
        playNotesListener = listener;
    }
}
