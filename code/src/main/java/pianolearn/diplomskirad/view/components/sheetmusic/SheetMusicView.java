package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;
import pianolearn.diplomskirad.model.viewmodel.MeasurePair;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.SongMetadataView;

public class SheetMusicView extends BaseView {

    private final VBox rootPane = new VBox();
    private final SongMetadataView songMetadataView = new SongMetadataView();
    private final VBox sheetMusicVBox = new VBox();
    private final SheetMusicPartView rightHandPartView = new SheetMusicPartView();
    private final SheetMusicPartView leftHandPartView = new SheetMusicPartView();

    public SheetMusicView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        sheetMusicVBox.getChildren().addAll(rightHandPartView, leftHandPartView);
        rootPane.getChildren().addAll(songMetadataView, sheetMusicVBox);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setSpacing(30);

        sheetMusicVBox.setBackground(StylesHelper.background(Colors.whiteKey, null));
        sheetMusicVBox.setSpacing(40);
        sheetMusicVBox.setPadding(new Insets(20, 0, 20, 0));
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
