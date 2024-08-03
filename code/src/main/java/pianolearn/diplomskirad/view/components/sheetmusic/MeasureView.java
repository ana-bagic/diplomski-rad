package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.view.BaseView;

public class MeasureView extends BaseView {

    private final Pane rootView = new Pane();

    public MeasureView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        bindToSelf(rootView);
    }
}
