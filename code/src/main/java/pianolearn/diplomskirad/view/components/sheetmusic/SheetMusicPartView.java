package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.StackPane;
import pianolearn.diplomskirad.view.BaseView;

public class SheetMusicPartView extends BaseView {

    private final StackPane rootPane = new StackPane();
    private final StaffView staffView = new StaffView();

    public SheetMusicPartView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        rootPane.getChildren().add(staffView);
        bindToSelf(rootPane);
    }
}
