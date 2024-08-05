package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.model.score.ClefTimeKeyModel;
import pianolearn.diplomskirad.view.BaseView;

public class NotesView extends BaseView {

    private final Pane rootPane = new Pane();
    private final ClefTimeKeyView clefTimeKeyView = new ClefTimeKeyView();
    private final MeasureView[] measureViews = new MeasureView[4];

    public NotesView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        rootPane.getChildren().add(clefTimeKeyView);
        bindToSelf(rootPane);
    }

    public void setClefTimeKey(ClefTimeKeyModel model) {
        clefTimeKeyView.setModel(model);
    }
}
