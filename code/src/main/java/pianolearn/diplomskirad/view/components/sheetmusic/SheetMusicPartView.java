package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.StackPane;
import pianolearn.diplomskirad.view.BaseView;

public class SheetMusicPartView extends BaseView {

    private final StackPane rootPane = new StackPane();

    public SheetMusicPartView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        bindToSelf(rootPane);
    }
}
