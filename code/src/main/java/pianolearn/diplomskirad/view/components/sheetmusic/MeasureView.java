package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.listener.NotesPlayListener;
import pianolearn.diplomskirad.model.viewmodel.MusicNodeModel;

import java.util.LinkedList;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.*;
import static pianolearn.diplomskirad.constants.SheetMusicSymbols.barLine;

public class MeasureView extends Pane {

    private final MusicNodeView barLineNode = new MusicNodeView();
    private final List<MusicNodeView> nodeViews = new LinkedList<>();

    private NotesPlayListener notesPlayListener;

    public MeasureView(List<MusicNodeModel> measure) {
        setupView();
        setModel(measure);
    }

    private void setupView() {
        getChildren().add(barLineNode);
        setMinHeight(STAFF_HEIGHT);
        setMaxHeight(STAFF_HEIGHT);

        barLineNode.setText(barLine);

        nodeViews.add(barLineNode);
    }

    private void setModel(List<MusicNodeModel> measure) {
        for (MusicNodeModel node : measure) {
            MusicNodeView nodeView = new MusicNodeView(node);
            getChildren().add(nodeView);
            nodeView.putAfter(nodeViews.getLast(), node.getDistanceFromPrev(), false);
            nodeViews.add(nodeView);
        }
    }

    public void translate(double amount) {
        setLayoutX(getLayoutX() - amount);

        for (MusicNodeView node : nodeViews) {
            double nodeX = node.localToScene(node.getBoundsInLocal()).getMaxX() - 15;
            if (nodeX < NOTE_DISAPPEAR_X) {
                node.setVisible(false);
            } else if (nodeX < CONTROL_LINE_X) {
                node.setColor(Colors.notesFaded);
            } else if (nodeX < CONTROL_LINE_X + CONTROL_LINE_WIDTH && node != barLineNode) {
                node.setColor(Colors.highlight);
                notesPlayListener.onAction(node.getNotes());
            }
        }
    }

    public void setPlayNotesListener(NotesPlayListener listener) {
        notesPlayListener = listener;
    }
}
