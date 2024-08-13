package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;
import pianolearn.diplomskirad.model.viewmodel.MeasurePair;
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

    public void showPart(boolean rightHandPart, boolean show) {
        showNode(rightHandPart ? rightHandPartView : leftHandPartView, show);
    }

    public void setClefTimeKey(boolean rightHandPart, ClefTimeKeyModel model) {
        if (rightHandPart) {
            rightHandPartView.setClefTimeKey(model);
        } else {
            leftHandPartView.setClefTimeKey(model);
        }
    }

    public void reset(boolean rightHandPart) {
        if (rightHandPart) {
            rightHandPartView.reset();
        } else {
            leftHandPartView.reset();
        }
    }

    public void addMeasure(MeasurePair measurePair) {
        rightHandPartView.addMeasure(measurePair.rightHandModel());
        if (measurePair.hasBothHands()) {
            leftHandPartView.addMeasure(measurePair.leftHandModel());
        }
    }
}
