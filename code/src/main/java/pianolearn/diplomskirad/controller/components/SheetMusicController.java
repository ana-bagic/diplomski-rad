package pianolearn.diplomskirad.controller.components;

import javafx.scene.layout.Pane;
import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.listener.MeasurePairCreateListener;
import pianolearn.diplomskirad.listener.NotesPlayListener;
import pianolearn.diplomskirad.model.score.AttributesModel;
import pianolearn.diplomskirad.model.viewmodel.MeasurePairModel;
import pianolearn.diplomskirad.view.components.sheetmusic.SheetMusicView;

import java.util.List;

public class SheetMusicController implements BaseViewController {

    private final SheetMusicView view = new SheetMusicView();

    private final ScorePartwise.Part part;
    private final AttributesModel attributes;

    private int nextDisplayMeasureIndex = 0;

    private MeasurePairCreateListener measurePairCreateListener;

    public SheetMusicController(ScorePartwise.Part part, AttributesModel attributes) {
        this.part = part;
        this.attributes = attributes;

        setupListeners();
        setupView();
    }

    @Override
    public Pane getView() {
        return view;
    }

    private void setupListeners() {
        view.setNewMeasureNeededListener(this::addNextMeasure);
    }

    private void setupView() {
        view.showPart(false, attributes.usesBothHands());

        view.setClefTimeKey(true, Score.clefTimeKey(attributes, true));
        if (attributes.usesBothHands()) {
            view.setClefTimeKey(false, Score.clefTimeKey(attributes, false));
        }
    }

    public void addNextMeasure() {
        if (part == null) return;

        List<ScorePartwise.Part.Measure> measures = part.getMeasure();
        MeasurePairModel measurePair = null;

        if (nextDisplayMeasureIndex < measures.size()) {
            ScorePartwise.Part.Measure measure = measures.get(nextDisplayMeasureIndex++);
            measurePair = Score.measures(measure, attributes);
            measurePairCreateListener.onAction(measurePair);
        }

        if (measurePair == null) return;
        view.addMeasure(measurePair);
    }

    public void reset() {
        nextDisplayMeasureIndex = 0;
        view.reset();
        addNextMeasure();
    }

    public void rightHandChanged(boolean show) {
        view.showPart(true, show);
    }

    public void leftHandChanged(boolean show) {
        view.showPart(false, show);
    }

    public void translateMeasures(double amount) {
        view.translateMeasures(amount);
    }

    public void setMeasurePairCreateListener(MeasurePairCreateListener listener) {
        measurePairCreateListener = listener;
    }

    public void setNotesPlayListeners(NotesPlayListener rightHandListener, NotesPlayListener leftHandListener) {
        view.setNotesPlayListeners(rightHandListener, leftHandListener);
    }
}
