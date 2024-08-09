package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.model.score.ClefTimeKeyModel;
import pianolearn.diplomskirad.view.BaseView;

import java.util.LinkedList;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.*;

public class SheetMusicPartView extends BaseView {

    private final StackPane rootPane = new StackPane();
    private final StaffView staffView = new StaffView();
    private final Pane notesView = new Pane();
    private final ClefTimeKeyView clefTimeKeyView = new ClefTimeKeyView();
    private final List<MeasureView> measureViews = new LinkedList<>();
    private final Pane controlLineContainer = new Pane();
    private final Rectangle controlLine = new Rectangle();

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
        rootPane.setBackground(StylesHelper.background(Colors.whiteKey, null));

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

    public void setClefTimeKey(ClefTimeKeyModel model) {
        clefTimeKeyView.setModel(model);
    }
}
