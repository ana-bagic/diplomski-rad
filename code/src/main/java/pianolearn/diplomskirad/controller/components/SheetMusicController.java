package pianolearn.diplomskirad.controller.components;

import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.MainEngine;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.model.viewmodel.MeasurePair;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.sheetmusic.SheetMusicView;

public class SheetMusicController implements BaseViewController {

    private final SheetMusicView view = new SheetMusicView();

    private int nextMeasureIndex = 0;

    private final MainEngine engine = MainEngine.INSTANCE;

    public SheetMusicController() {
        setupView();
        if (engine.usesBothHands()) {
            setupListeners();
        }
    }

    @Override
    public BaseView getView() {
        return view;
    }

    private void setupView() {
        view.showPart(false, engine.usesBothHands());

        view.setClefTimeKey(true, engine.getClefTimeKey(true));
        if (engine.usesBothHands()) {
            view.setClefTimeKey(false, engine.getClefTimeKey(false));
        }

        setupInitialMeasures();
    }

    private void setupListeners() {
        engine.setLeftHandChangedListener(show -> view.showPart(false, show));
        engine.setRightHandChangedListener(show -> view.showPart(true, show));
    }

    private void setupInitialMeasures() {
        ScorePartwise.Part part = engine.getPart();
        if (part == null || part.getMeasure().size() < 2) return;

  //      while (nextMeasureIndex < rightHandPart.getMeasure().size()) {
        while (nextMeasureIndex < 2) {
            ScorePartwise.Part.Measure measure = part.getMeasure().get(nextMeasureIndex);
            MeasurePair measurePair = Score.measures(measure);
            view.addMeasure(measurePair);
            nextMeasureIndex++;
        }
    }
}
