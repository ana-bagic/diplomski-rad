package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.model.score.ClefTimeKeyModel;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.SongMetadataView;

public class SheetMusicView extends BaseView {

    private final VBox rootPane = new VBox();
    private final SongMetadataView songMetadataView = new SongMetadataView();
    private final SheetMusicPartView rightHandPartView = new SheetMusicPartView();
    private final SheetMusicPartView leftHandPartView = new SheetMusicPartView();

    public SheetMusicView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        rootPane.getChildren().addAll(songMetadataView, rightHandPartView, leftHandPartView);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        if (Score.numberOfParts() == 1) {
            leftHandPartView.setVisible(false);
        }
    }

    public void setRightHandClefTimeKey(ClefTimeKeyModel model) {
        rightHandPartView.setClefTimeKey(model);
    }

    public void setLeftHandClefTimeKey(ClefTimeKeyModel model) {
        leftHandPartView.setClefTimeKey(model);
    }
}
