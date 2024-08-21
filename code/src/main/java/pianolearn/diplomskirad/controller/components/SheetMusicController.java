package pianolearn.diplomskirad.controller.components;

import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.listener.MeasurePairCreatedListener;
import pianolearn.diplomskirad.listener.PlayNotesListener;
import pianolearn.diplomskirad.model.score.ScoreAttributes;
import pianolearn.diplomskirad.model.viewmodel.MeasurePair;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.sheetmusic.SheetMusicView;

import java.util.List;

public class SheetMusicController implements BaseViewController {

    private final SheetMusicView view = new SheetMusicView();

    private final ScorePartwise.Part part;
    private final ScoreAttributes attributes;

    private int nextDisplayMeasureIndex = 0;

    private MeasurePairCreatedListener measurePairCreatedListener;

    public SheetMusicController(ScorePartwise.Part part, ScoreAttributes attributes) {
        this.part = part;
        this.attributes = attributes;

        setupListeners();
        setupView();
    }

    @Override
    public BaseView getView() {
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
        MeasurePair measurePair = null;

        if (nextDisplayMeasureIndex < measures.size()) {
            ScorePartwise.Part.Measure measure = measures.get(nextDisplayMeasureIndex++);
            measurePair = Score.measures(measure, attributes);
            measurePairCreatedListener.onAction(measurePair);
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

    public void setMeasurePairCreatedListener(MeasurePairCreatedListener listener) {
        measurePairCreatedListener = listener;
    }

    public void setPlayNotesListeners(PlayNotesListener rightHandListener, PlayNotesListener leftHandListener) {
        view.setPlayNotesListeners(rightHandListener, leftHandListener);
    }
}
