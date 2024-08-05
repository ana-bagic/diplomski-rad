package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.model.score.ClefTimeKeyModel;
import pianolearn.diplomskirad.view.BaseView;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.barLine;

public class ClefTimeKeyView extends BaseView {

    private final Pane rootPane = new Pane();
    private final MusicNodeView barLineNode = new MusicNodeView();
    private final MusicNodeView clefNode = new MusicNodeView();
    private final MusicNodeView timeNumeratorNode = new MusicNodeView();
    private final MusicNodeView timeDenominatorNode = new MusicNodeView();

    public ClefTimeKeyView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        rootPane.getChildren().addAll(barLineNode, clefNode, timeNumeratorNode, timeDenominatorNode);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        barLineNode.setText(barLine);

        clefNode.putAfter(barLineNode, 1);

        timeNumeratorNode.position(6);

        timeDenominatorNode.position(2);
    }

    public void setModel(ClefTimeKeyModel model) {
        clefNode.setText(model.clef());
        timeNumeratorNode.setText(model.numerator());
        timeDenominatorNode.setText(model.denominator());

        timeNumeratorNode.putAfter(clefNode, 1);
        timeDenominatorNode.putAfter(clefNode, 1);

        MusicNodeView prevAcc = null;
        for (Integer position : model.accidentalPositions()) {
            MusicNodeView acc = new MusicNodeView(model.accidental(), position);
            if (prevAcc == null) {
                acc.putAfter(timeNumeratorNode, 2.5);
            } else {
                acc.putAfter(prevAcc, 0);
            }
            rootPane.getChildren().add(acc);
            prevAcc = acc;
        }
    }
}
