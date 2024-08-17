package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.model.viewmodel.MusicNodeModel;
import pianolearn.diplomskirad.view.BaseView;

import java.util.List;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.barLine;

public class MeasureView extends BaseView {

    private final Pane rootPane = new Pane();
    private final NoteView barLineNode = new NoteView();

    public MeasureView(List<MusicNodeModel> measure) {
        setupGUI();
        setModel(measure);
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

    private void setModel(List<MusicNodeModel> measure) {
        Node prevNode = barLineNode;
        for (MusicNodeModel node : measure) {
            MusicNodeView nodeView = new MusicNodeView(node);
            rootPane.getChildren().add(nodeView);
            nodeView.putAfter(prevNode, node.getDistanceFromPrev());
            prevNode = nodeView;
        }
    }
}
