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
        view.showLeftHandPart(!engine.usesOneHand());
        view.setRightHandClefTimeKey(engine.getRightHandClefTimeKeyModel());
        if (!engine.usesOneHand()) {
            view.setLeftHandClefTimeKey(engine.getLeftHandClefTimeKeyModel());
        }
    }

    private void setupListeners() {
        engine.setLeftHandChangedListener(view::showLeftHandPart);
        engine.setRightHandChangedListener(view::showRightHandPart);
    }
}
