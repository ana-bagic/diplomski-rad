package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.model.score.ClefTimeKey;
import pianolearn.diplomskirad.view.BaseView;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.barLine;

public class ClefTimeKeyView extends BaseView {

    private final Pane rootPane = new Pane();
    private final MusicNode barLineText = new MusicNode();
    private final MusicNode clefText = new MusicNode();
    private final MusicNode timeNumeratorText = new MusicNode();
    private final MusicNode timeDenominatorText = new MusicNode();

    public ClefTimeKeyView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        rootPane.getChildren().addAll(barLineText, clefText, timeNumeratorText, timeDenominatorText);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        barLineText.setText(barLine);

        clefText.putAfter(barLineText, 1);

        timeNumeratorText.raiseBy(6);

        timeDenominatorText.raiseBy(2);
    }

    public void setModel(ClefTimeKey model) {
        clefText.setText(model.clef());
        timeNumeratorText.setText(model.numerator());
        timeDenominatorText.setText(model.denominator());

        timeNumeratorText.putAfter(clefText, 1);
        timeDenominatorText.putAfter(clefText, 1);

        MusicNode prevAcc = null;
        for (Integer accidental : model.accidentals()) {
            MusicNode acc = new MusicNode(model.accidental());
            if (prevAcc == null) {
                acc.putAfter(timeNumeratorText, 2.5);
            } else {
                acc.putAfter(prevAcc, 0);
            }
            acc.raiseBy(accidental);
            rootPane.getChildren().add(acc);
            prevAcc = acc;
        }
    }
}
