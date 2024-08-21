package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.model.viewmodel.ClefTimeKeyModel;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.barLine;

public class ClefTimeKeyView extends Pane {

    private final MusicNodeView barLineNode = new MusicNodeView();
    private final MusicNodeView clefNode = new MusicNodeView();
    private final MusicNodeView beatsNode = new MusicNodeView();
    private final MusicNodeView beatUnitNode = new MusicNodeView();

    public ClefTimeKeyView() {
        setupView();
    }

    private void setupView() {
        getChildren().addAll(barLineNode, clefNode, beatsNode, beatUnitNode);

        barLineNode.setText(barLine);

        clefNode.putAfter(barLineNode, ClefTimeKeyModel.spacing(), true);

        beatsNode.setPosition(6);

        beatUnitNode.setPosition(2);
    }

    public void setModel(ClefTimeKeyModel model) {
        clefNode.setText(model.clef());
        beatsNode.setText(model.beats());
        beatUnitNode.setText(model.beatsUnit());

        beatsNode.putAfter(clefNode, ClefTimeKeyModel.spacing(), true);
        beatUnitNode.putAfter(clefNode, ClefTimeKeyModel.spacing(), true);

        MusicNodeView prevAcc = null;
        for (Integer position : model.accidentalPositions()) {
            MusicNodeView acc = new MusicNodeView();
            acc.setText(model.accidental());
            acc.setPosition(position);
            if (prevAcc == null) {
                acc.putAfter(beatsNode, ClefTimeKeyModel.timeAccidentalSpacing(), true);
            } else {
                acc.putAfter(prevAcc, 0, true);
            }
            getChildren().add(acc);
            prevAcc = acc;
        }
    }
}
