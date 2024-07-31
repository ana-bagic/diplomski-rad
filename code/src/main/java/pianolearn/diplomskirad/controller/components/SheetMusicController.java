package pianolearn.diplomskirad.controller.components;

import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.sheetmusic.SheetMusicView;

public class SheetMusicController implements BaseViewController {

    private final SheetMusicView view = new SheetMusicView();

    @Override
    public BaseView getView() {
        return view;
    }
}
