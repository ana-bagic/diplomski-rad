package pianolearn.diplomskirad.controller.components;

import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.model.score.ClefTimeKeyModel;
import pianolearn.diplomskirad.model.score.MeasureModel;
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
        ClefTimeKeyModel rightHandModel = Score.clefTimeKey(rightHandMeasure);
        MeasureModel measureModel = Score.measure(rightHandMeasure, true);
        view.setRightHandClefTimeKey(rightHandModel);

        if (Score.numberOfParts() == 2) {
            ScorePartwise.Part.Measure leftHandMeasure = Objects.requireNonNull(Score.leftHandPart()).getMeasure().getFirst();
            ClefTimeKeyModel leftHandModel = Score.clefTimeKey(leftHandMeasure);
            measureModel = Score.measure(leftHandMeasure, false);
            view.setLeftHandClefTimeKey(leftHandModel);
        }
    }
}
