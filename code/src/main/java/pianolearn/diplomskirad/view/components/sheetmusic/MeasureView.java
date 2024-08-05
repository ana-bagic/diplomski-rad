package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.model.score.MeasureModel;
import pianolearn.diplomskirad.model.score.MusicNodeModel;
import pianolearn.diplomskirad.view.BaseView;

import java.util.Objects;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.barLine;

public class MeasureView extends BaseView {

    private final Pane rootPane = new Pane();
    private final MusicNodeView barLineNode = new MusicNodeView();

    public MeasureView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        rootPane.getChildren().add(barLineNode);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        barLineNode.setText(barLine);
    }

    public void setModel(MeasureModel model) {
        MusicNodeView prevNode = null;
        for (MusicNodeModel node : model.elements()) {
            MusicNodeView nodeView = new MusicNodeView(node.type(), node.position());
            nodeView.putAfter(Objects.requireNonNullElse(prevNode, barLineNode), 1);
            rootPane.getChildren().add(nodeView);
            prevNode = nodeView;
        }
    }
}
