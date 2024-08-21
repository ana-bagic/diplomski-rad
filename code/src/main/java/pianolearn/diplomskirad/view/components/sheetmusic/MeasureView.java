package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.listener.PlayNotesListener;
import pianolearn.diplomskirad.model.viewmodel.MusicNodeModel;
import pianolearn.diplomskirad.view.BaseView;

import java.util.LinkedList;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.*;
import static pianolearn.diplomskirad.constants.SheetMusicSymbols.barLine;

public class MeasureView extends BaseView {

    private final Pane rootPane = new Pane();
    private final MusicNodeView barLineNode = new MusicNodeView();
    private final List<MusicNodeView> nodeViews = new LinkedList<>();

    private PlayNotesListener playNotesListener;

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
        MusicNodeView prevNode = barLineNode;
        for (MusicNodeModel node : measure) {
            MusicNodeView nodeView = new MusicNodeView(node);
            rootPane.getChildren().add(nodeView);
            nodeViews.add(nodeView);
            nodeView.putAfter(prevNode, node.getDistanceFromPrev(), false);
            prevNode = nodeView;
        }
    }

    public void translate(double amount) {
        setLayoutX(getLayoutX() - amount);

        checkIfNodeShouldChange(barLineNode);
        nodeViews.forEach(this::checkIfNodeShouldChange);
    }

    private void checkIfNodeShouldChange(MusicNodeView node) {
        Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
        double nodeX = boundsInScene.getMaxX() - 15;
        if (nodeX < NOTE_DISAPPEAR_X) {
            node.setVisible(false);
        } else if (nodeX < CONTROL_LINE_X) {
            node.setColor(Colors.notesFaded);
        } else if (nodeX < CONTROL_LINE_X + CONTROL_LINE_WIDTH && node != barLineNode) {
            node.setColor(Colors.highlight);
            playNotesListener.onAction(node.getPitches());
        }
    }

    public void setPlayNotesListener(PlayNotesListener listener) {
        playNotesListener = listener;
    }
}
