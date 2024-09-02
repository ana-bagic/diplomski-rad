package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.helper.StyleHelper;
import pianolearn.diplomskirad.listener.EventListener;
import pianolearn.diplomskirad.listener.NotesEndListener;
import pianolearn.diplomskirad.listener.NotesPlayListener;
import pianolearn.diplomskirad.model.Hand;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;
import pianolearn.diplomskirad.model.viewmodel.MeasurePairModel;
import pianolearn.diplomskirad.view.components.SongMetadataView;

import static pianolearn.diplomskirad.constants.Config.MEASURE_START_X;

public class SheetMusicView extends VBox {

    private final SongMetadataView songMetadataView = new SongMetadataView();
    private final VBox sheetMusicVBox = new VBox();
    private final SheetMusicPartView rightHandPartView = new SheetMusicPartView(Hand.RIGHT);
    private final SheetMusicPartView leftHandPartView = new SheetMusicPartView(Hand.LEFT);

    private double lastMeasureEnd = MEASURE_START_X;
    private final Scene scene = NavigationController.INSTANCE.getStage().getScene();

    private EventListener newMeasureNeededListener;

    public SheetMusicView() {
        setupView();
    }

    private void setupView() {
        scene.widthProperty().addListener(e -> fetchRemoveMeasures());

        getChildren().addAll(songMetadataView, sheetMusicVBox);
        setSpacing(30);
        setPadding(new Insets(40, 0, 0, 0));

        sheetMusicVBox.getChildren().addAll(rightHandPartView, leftHandPartView);
        sheetMusicVBox.setBackground(StyleHelper.background(Colors.text, null));

        Hand.RIGHT.addShowsListener((o, ov, nv) -> StyleHelper.showNode(rightHandPartView, nv));
        Hand.LEFT.addShowsListener((o, ov, nv) -> StyleHelper.showNode(leftHandPartView, nv));
    }

    public void setClefTimeKey(Hand hand, ClefTimeKeyModel model) {
        if (hand == Hand.RIGHT) {
            rightHandPartView.setClefTimeKey(model);
        } else {
            leftHandPartView.setClefTimeKey(model);
        }
    }

    public void addMeasure(MeasurePairModel measurePair) {
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

    private void fetchRemoveMeasures() {
        double sceneWidth = scene.getWidth();

        if (newMeasureNeededListener != null && lastMeasureEnd < sceneWidth) {
            newMeasureNeededListener.onAction();
        }

        rightHandPartView.removeMeasuresIfNeeded();
        leftHandPartView.removeMeasuresIfNeeded();
    }

    public void reset() {
        rightHandPartView.reset();
        leftHandPartView.reset();
        lastMeasureEnd = MEASURE_START_X;
    }

    public void setNewMeasureNeededListener(EventListener listener) {
        newMeasureNeededListener = listener;
    }

    public void setNotesPlayListener(NotesPlayListener listener) {
        rightHandPartView.setNotesPlayListener(listener);
        leftHandPartView.setNotesPlayListener(listener);
    }

    public void setNotesEndListener(NotesEndListener listener) {
        rightHandPartView.setNotesEndListener(listener);
        leftHandPartView.setNotesEndListener(listener);
    }
}
