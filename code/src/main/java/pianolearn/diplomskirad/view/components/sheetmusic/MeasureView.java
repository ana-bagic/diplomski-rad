package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.model.viewmodel.MeasureModel;
import pianolearn.diplomskirad.model.viewmodel.MusicNodeModel;
import pianolearn.diplomskirad.view.BaseView;

import static pianolearn.diplomskirad.constants.SheetMusicSymbols.barLine;

public class MeasureView extends BaseView {

    private final Pane rootPane = new Pane();
    private final NoteView barLineNode = new NoteView();

    public MeasureView(MeasureModel model) {
        setupGUI();
        setModel(model);
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

    private void setModel(MeasureModel model) {
        Node prevNode = barLineNode;
        for (MusicNodeModel node : model.elements()) {
            MusicNodeView nodeView = new MusicNodeView(node);
            rootPane.getChildren().add(nodeView);
            nodeView.putAfter(prevNode, node.getDistanceFromPrev() * 3);
            prevNode = nodeView;
        }
    }
}
