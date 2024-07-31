package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.helper.StylesHelper;
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

    @Override
    protected void styleViews() {
        rootPane.setBackground(StylesHelper.background(Colors.whiteKey, null));
        rootPane.setPadding(new Insets(70, 0, 50, 0));
    }
}
