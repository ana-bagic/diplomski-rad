package pianolearn.diplomskirad.controller.components;

import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.model.score.ClefTimeKey;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.sheetmusic.SheetMusicView;

import java.util.*;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.*;

public class SheetMusicController implements BaseViewController {

    private final SheetMusicView view = new SheetMusicView();

    public SheetMusicController() {
        setupClefTimeKey();
    }

    @Override
    public BaseView getView() {
        return view;
    }

    private void setupClefTimeKey() {
        List<Integer> accidentals = new LinkedList<>();
        accidentals.add(4);
        accidentals.add(1);
        accidentals.add(5);
        ClefTimeKey model = new ClefTimeKey(trebleClef, time4, time8, accidentals, sharp);
        view.setRightHandClefTimeKey(model);
        view.setLeftHandClefTimeKey(model);
    }
}
