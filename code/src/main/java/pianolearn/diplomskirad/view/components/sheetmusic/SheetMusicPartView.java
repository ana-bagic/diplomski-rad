package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.listener.PlayNotesListener;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;
import pianolearn.diplomskirad.model.viewmodel.MusicNodeModel;
import pianolearn.diplomskirad.view.BaseView;

import java.util.LinkedList;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.*;

public class SheetMusicPartView extends BaseView {

    private final StackPane rootPane = new StackPane();
    private final Pane notesView = new Pane();
    private final StaffView staffView = new StaffView();
    private final ClefTimeKeyView clefTimeKeyView = new ClefTimeKeyView();
    private final LinkedList<MeasureView> measureViews = new LinkedList<>();
    private final Pane controlLineContainer = new Pane();
    private final Rectangle controlLine = new Rectangle();

    private PlayNotesListener  playNotesListener;

    public SheetMusicPartView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        notesView.getChildren().add(clefTimeKeyView);
        controlLineContainer.getChildren().add(controlLine);
        rootPane.getChildren().addAll(staffView, notesView, controlLineContainer);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        staffView.setMinHeight(STAFF_HEIGHT);
        staffView.setMaxHeight(STAFF_HEIGHT);

        notesView.setMinHeight(STAFF_HEIGHT);
        notesView.setMaxHeight(STAFF_HEIGHT);

        controlLine.setLayoutX(CONTROL_LINE_X);
        controlLine.setLayoutY(0);
        controlLine.setWidth(CONTROL_LINE_WIDTH);
        controlLine.setHeight(STAFF_HEIGHT);
        controlLine.setFill(Colors.controlLine);
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

    public void setClefTimeKey(ClefTimeKeyModel model) {
        clefTimeKeyView.setModel(model);
    }

    public void reset() {
        measureViews.clear();
        notesView.getChildren().clear();
        notesView.getChildren().add(clefTimeKeyView);
    }

    public void setPlayNotesListener(PlayNotesListener listener) {
        playNotesListener = listener;
    }
}
