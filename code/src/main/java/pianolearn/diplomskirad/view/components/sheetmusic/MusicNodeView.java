package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pianolearn.diplomskirad.model.score.PitchModel;
import pianolearn.diplomskirad.model.viewmodel.MusicNodeModel;
import pianolearn.diplomskirad.model.viewmodel.NoteModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MusicNodeView extends Pane {

    private final List<NoteView> noteViews = new LinkedList<>();

    private boolean played = false;
    private boolean prepared = false;

    public MusicNodeView() {
        NoteView noteView = new NoteView();
        getChildren().add(noteView);
        noteViews.add(noteView);
    }

    public MusicNodeView(MusicNodeModel model) {
        for (NoteModel note : model.getNotes()) {
            NoteView noteView = new NoteView(note);
            getChildren().add(noteView);
            noteViews.add(noteView);
        }
    }

    public List<PitchModel> getPitches() {
        return noteViews.stream().map(NoteView::getPitch).filter(Objects::nonNull).toList();
    }

    private double getNodeWidth() {
        return noteViews.isEmpty() ? 0 : noteViews.getFirst().prefWidth(-1);
    }

    public boolean isPlayed() {
        return played;
    }

    public boolean isPrepared() {
        return prepared;
    }

    public void setText(String text) {
        noteViews.forEach(noteView -> noteView.setText(text));
    }

    public void setPosition(int position) {
        noteViews.forEach(noteView -> noteView.setPosition(position));
    }

    public void putAfter(MusicNodeView prevNode, double amount, boolean computeWidth) {
        double xPosition = prevNode.getLayoutX() + amount;
        xPosition += computeWidth ? prevNode.getNodeWidth() : 0;
        setLayoutX(xPosition);
    }

    public void setColor(Color color) {
        noteViews.forEach(n -> n.setColor(color));
    }

    public void setPlayed() {
        played = true;
    }

    public void setPrepared() {
        prepared = true;
    }
}
