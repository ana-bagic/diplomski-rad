package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.listener.NotesEndListener;
import pianolearn.diplomskirad.listener.NotesPlayListener;
import pianolearn.diplomskirad.model.Hand;
import pianolearn.diplomskirad.model.viewmodel.MusicNodeModel;

import java.util.LinkedList;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.*;
import static pianolearn.diplomskirad.constants.SheetMusicSymbols.barLine;

public class MeasureView extends Pane {

    private final MusicNodeView barLineNode = new MusicNodeView();
    private final List<MusicNodeView> nodeViews = new LinkedList<>();

    private final Hand hand;

    private NotesPlayListener notesPlayListener;
    private NotesEndListener notesEndListener;

    public MeasureView(List<MusicNodeModel> measure, Hand hand) {
        this.hand = hand;
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
            double nodeX = node.localToScene(node.getBoundsInLocal()).getMinX();
            if (nodeX < NOTE_DISAPPEAR_X) {
                node.setVisible(false);
                continue;
            }

            if (node == barLineNode) continue;

            if (nodeX < CONTROL_LINE_X && !node.isPlayed()) {
                node.setColor(hand.getKeyColor());
                notesPlayListener.onAction(node.getPitches(), hand);
                node.setPlayed();
                continue;
            }

            if (nodeX < CONTROL_LINE_X + 20 && !node.isPrepared()) {
                notesEndListener.onAction(hand);
                node.setPrepared();
            }
        }
    }

    public void setNotesPlayListener(NotesPlayListener listener) {
        notesPlayListener = listener;
    }

    public void setNotesEndListener(NotesEndListener listener) {
        notesEndListener = listener;
    }
}
