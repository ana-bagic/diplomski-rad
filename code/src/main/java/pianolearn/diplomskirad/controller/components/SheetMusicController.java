package pianolearn.diplomskirad.controller.components;

import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.MainEngine;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.sheetmusic.SheetMusicView;

public class SheetMusicController implements BaseViewController {

    private final SheetMusicView view = new SheetMusicView();

    private final MainEngine engine = MainEngine.INSTANCE;

    public SheetMusicController() {
        setupView();
        if (!engine.usesOneHand()) {
            setupListeners();
        }
    }

    @Override
    public BaseView getView() {
        return view;
    }

    private void setupView() {
        view.showPart(false, !engine.usesOneHand());

        view.setClefTimeKey(true, engine.getClefTimeKey(true));
        if (!engine.usesOneHand()) {
            view.setClefTimeKey(false, engine.getClefTimeKey(false));
        }
    }

    private void setupListeners() {
        engine.setLeftHandChangedListener(show -> view.showPart(false, show));
        engine.setRightHandChangedListener(show -> view.showPart(true, show));
    }
}
