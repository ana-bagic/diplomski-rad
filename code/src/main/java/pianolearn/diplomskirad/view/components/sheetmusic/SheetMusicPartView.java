package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.StackPane;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.model.score.ClefTimeKey;
import pianolearn.diplomskirad.view.BaseView;

import static pianolearn.diplomskirad.constants.Config.STAFF_HEIGHT;

public class SheetMusicPartView extends BaseView {

    private final StackPane rootPane = new StackPane();
    private final StaffView staffView = new StaffView();
    private final NotesView notesView = new NotesView();

    public SheetMusicPartView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        rootPane.getChildren().addAll(staffView, notesView);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setBackground(StylesHelper.background(Colors.whiteKey, null));

        staffView.setMinHeight(STAFF_HEIGHT);
        staffView.setMaxHeight(STAFF_HEIGHT);

        notesView.setMinHeight(STAFF_HEIGHT);
        notesView.setMaxHeight(STAFF_HEIGHT);
    }

    public void setClefTimeKey(ClefTimeKey model) {
        notesView.setClefTimeKey(model);
    }
}
