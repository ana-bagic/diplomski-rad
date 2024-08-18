package pianolearn.diplomskirad.controller.components;

import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.MainEngine;
import pianolearn.diplomskirad.model.viewmodel.MeasurePair;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.sheetmusic.SheetMusicView;

public class SheetMusicController implements BaseViewController {

    private final SheetMusicView view = new SheetMusicView();

    private final MainEngine engine = MainEngine.INSTANCE;

    public SheetMusicController() {
        setupView();
        setupListeners();
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

        addNextMeasure();
    }

    private void setupListeners() {
        if (engine.usesBothHands()) {
            engine.setLeftHandChangedListener(show -> view.showPart(false, show));
            engine.setRightHandChangedListener(show -> view.showPart(true, show));
        }
        engine.setStopClickedListener(this::reset);
        engine.setTranslateMeasuresListener(view::translateMeasures);

        view.setNewMeasureNeededListener(this::addNextMeasure);
    }

    private void addNextMeasure() {
        MeasurePair measurePair = engine.getNextMeasure();
        if (measurePair == null) return;
        view.addMeasure(measurePair);
    }

    private void reset() {
        view.reset();
        addNextMeasure();
    }
}
