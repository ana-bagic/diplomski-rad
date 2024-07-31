package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.view.BaseView;

public class SheetMusicView extends BaseView {

    private final VBox rootPane = new VBox();
    private final SheetMusicPartView rightHandPartView = new SheetMusicPartView();
    private final SheetMusicPartView leftHandPartView = new SheetMusicPartView();

    public SheetMusicView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        rootPane.getChildren().addAll(rightHandPartView, leftHandPartView);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setBackground(StylesHelper.background(Colors.whiteKey, null));
    }
}
