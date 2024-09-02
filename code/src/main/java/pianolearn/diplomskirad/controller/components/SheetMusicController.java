package pianolearn.diplomskirad.controller.components;

import javafx.scene.layout.Pane;
import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.listener.MeasurePairCreateListener;
import pianolearn.diplomskirad.listener.NotesEndListener;
import pianolearn.diplomskirad.listener.NotesPlayListener;
import pianolearn.diplomskirad.model.Hand;
import pianolearn.diplomskirad.model.score.AttributesModel;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;
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
        Hand.LEFT.setShows(attributes.usesBothHands());

        ClefTimeKeyModel rightCTKModel = ClefTimeKeyModel.fromAttributes(attributes, true);
        view.setClefTimeKey(Hand.RIGHT, rightCTKModel);
        if (attributes.usesBothHands()) {
            ClefTimeKeyModel leftCTKModel = ClefTimeKeyModel.fromAttributes(attributes, false);
            view.setClefTimeKey(Hand.LEFT, leftCTKModel);
        }
    }

    public void addNextMeasure() {
        if (part == null) return;

        List<ScorePartwise.Part.Measure> measures = part.getMeasure();
        MeasurePairModel measurePair = null;

        if (nextDisplayMeasureIndex < measures.size()) {
            ScorePartwise.Part.Measure measure = measures.get(nextDisplayMeasureIndex++);
            measurePair = Score.measurePair(measure, attributes);
        }

        if (measurePair == null) return;

        measurePairCreateListener.onAction(measurePair);
        view.addMeasure(measurePair);
    }

    public void reset() {
        nextDisplayMeasureIndex = 0;
        view.reset();
        addNextMeasure();
    }

    public void translateMeasures(double amount) {
        view.translateMeasures(amount);
    }

    public void setMeasurePairCreateListener(MeasurePairCreateListener listener) {
        measurePairCreateListener = listener;
    }

    public void setNotesPlayListener(NotesPlayListener listener) {
        view.setNotesPlayListener(listener);
    }

    public void setNotesEndListener(NotesEndListener listener) {
        view.setNotesEndListener(listener);
    }
}
