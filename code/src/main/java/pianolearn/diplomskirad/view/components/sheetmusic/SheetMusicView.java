package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.listener.EventListener;
import pianolearn.diplomskirad.listener.PlayNotesListener;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;
import pianolearn.diplomskirad.model.viewmodel.MeasurePair;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.SongMetadataView;

import static pianolearn.diplomskirad.constants.Config.MEASURE_START_X;

public class SheetMusicView extends BaseView {

    private final VBox rootPane = new VBox();
    private final SongMetadataView songMetadataView = new SongMetadataView();
    private final VBox sheetMusicVBox = new VBox();
    private final SheetMusicPartView rightHandPartView = new SheetMusicPartView();
    private final SheetMusicPartView leftHandPartView = new SheetMusicPartView();

    private double lastMeasureEnd = MEASURE_START_X;
    private final Scene scene = NavigationController.INSTANCE.getStage().getScene();

    private EventListener newMeasureNeededListener;

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
        scene.widthProperty().addListener(e -> fetchRemoveMeasures());

        rootPane.setSpacing(30);

        sheetMusicVBox.setBackground(StylesHelper.background(Colors.text, null));
        sheetMusicVBox.setSpacing(50);
        sheetMusicVBox.setPadding(new Insets(40, 0, 40, 0));
    }

    public void addMeasure(MeasurePair measurePair) {
        rightHandPartView.addMeasure(measurePair.getRightHandMeasure(), lastMeasureEnd);
        if (measurePair.hasBothHands()) {
            leftHandPartView.addMeasure(measurePair.getLeftHandMeasure(), lastMeasureEnd);
        }

        lastMeasureEnd += measurePair.getWidth();
        fetchRemoveMeasures();
    }

    public void translateMeasures(double amount) {
        rightHandPartView.translateMeasures(amount);
        leftHandPartView.translateMeasures(amount);

        lastMeasureEnd -= amount;
        fetchRemoveMeasures();
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

    public void reset() {
        rightHandPartView.reset();
        leftHandPartView.reset();
        lastMeasureEnd = MEASURE_START_X;
    }

    private void fetchRemoveMeasures() {
        double sceneWidth = scene.getWidth();

        if (newMeasureNeededListener != null && lastMeasureEnd < sceneWidth) {
            newMeasureNeededListener.onAction();
        }

        rightHandPartView.removeMeasuresIfNeeded();
        leftHandPartView.removeMeasuresIfNeeded();
    }

    public void setNewMeasureNeededListener(EventListener listener) {
        newMeasureNeededListener = listener;
    }

    public void setPlayNotesListeners(PlayNotesListener rightHandListener, PlayNotesListener leftHandListener) {
        rightHandPartView.setPlayNotesListener(rightHandListener);
        leftHandPartView.setPlayNotesListener(leftHandListener);
    }
}
