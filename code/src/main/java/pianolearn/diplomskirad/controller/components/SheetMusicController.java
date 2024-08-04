package pianolearn.diplomskirad.controller.components;

import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.model.score.ClefTimeKey;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.sheetmusic.SheetMusicView;

import java.util.Objects;

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
        ScorePartwise.Part.Measure rightHandMeasure = Objects.requireNonNull(Score.rightHandPart()).getMeasure().getFirst();
        ClefTimeKey rightHandModel = Score.clefTimeKey(rightHandMeasure);
        view.setRightHandClefTimeKey(rightHandModel);

        if (Score.numberOfParts() == 2) {
            ScorePartwise.Part.Measure leftHandMeasure = Objects.requireNonNull(Score.leftHandPart()).getMeasure().getFirst();
            ClefTimeKey leftHandModel = Score.clefTimeKey(leftHandMeasure);
            view.setLeftHandClefTimeKey(leftHandModel);
        }
    }
}
