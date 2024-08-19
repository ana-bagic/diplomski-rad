package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;
import pianolearn.diplomskirad.view.BaseView;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.barLine;

public class ClefTimeKeyView extends BaseView {

    private final Pane rootPane = new Pane();
    private final NoteView barLineNode = new NoteView();
    private final NoteView clefNode = new NoteView();
    private final NoteView beatsNode = new NoteView();
    private final NoteView beatUnitNode = new NoteView();

    public ClefTimeKeyView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        rootPane.getChildren().addAll(barLineNode, clefNode, beatsNode, beatUnitNode);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setBackground(StylesHelper.background(Colors.text, null));

        barLineNode.setText(barLine);

        clefNode.putAfter(barLineNode, ClefTimeKeyModel.spacing());

        beatsNode.position(6);

        beatUnitNode.position(2);
    }

    public void setModel(ClefTimeKeyModel model) {
        clefNode.setText(model.clef());
        beatsNode.setText(model.beats());
        beatUnitNode.setText(model.beatsUnit());

        beatsNode.putAfter(clefNode, ClefTimeKeyModel.spacing());
        beatUnitNode.putAfter(clefNode, ClefTimeKeyModel.spacing());

        NoteView prevAcc = null;
        for (Integer position : model.accidentalPositions()) {
            NoteView acc = new NoteView(model.accidental(), position);
            if (prevAcc == null) {
                acc.putAfter(beatsNode, ClefTimeKeyModel.timeAccidentalSpacing());
            } else {
                acc.putAfter(prevAcc, 0);
            }
            rootPane.getChildren().add(acc);
            prevAcc = acc;
        }
    }
}
