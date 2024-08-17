package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;
import pianolearn.diplomskirad.view.BaseView;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.barLine;

public class ClefTimeKeyView extends BaseView {

    private final Pane rootPane = new Pane();
    private final NoteView barLineNode = new NoteView();
    private final NoteView clefNode = new NoteView();
    private final NoteView timeNumeratorNode = new NoteView();
    private final NoteView timeDenominatorNode = new NoteView();

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

        clefNode.putAfter(barLineNode, ClefTimeKeyModel.spacing());

        timeNumeratorNode.position(6);

        timeDenominatorNode.position(2);
    }

    public void setModel(ClefTimeKeyModel model) {
        clefNode.setText(model.clef());
        timeNumeratorNode.setText(model.numerator());
        timeDenominatorNode.setText(model.denominator());

        timeNumeratorNode.putAfter(clefNode, ClefTimeKeyModel.spacing());
        timeDenominatorNode.putAfter(clefNode, ClefTimeKeyModel.spacing());

        NoteView prevAcc = null;
        for (Integer position : model.accidentalPositions()) {
            NoteView acc = new NoteView(model.accidental(), position);
            if (prevAcc == null) {
                acc.putAfter(timeNumeratorNode, ClefTimeKeyModel.timeAccidentalSpacing());
            } else {
                acc.putAfter(prevAcc, 0);
            }
            rootPane.getChildren().add(acc);
            prevAcc = acc;
        }
    }
}
