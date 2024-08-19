package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.model.viewmodel.MusicNodeModel;
import pianolearn.diplomskirad.model.viewmodel.NoteModel;
import pianolearn.diplomskirad.view.BaseView;

import java.util.LinkedList;
import java.util.List;

public class MusicNodeView extends BaseView {

    private final Pane rootPane = new Pane();
    private final List<NoteView> noteViews = new LinkedList<>();

    public MusicNodeView(MusicNodeModel model) {
        setupGUI();

        for (NoteModel note : model.getNotes()) {
            NoteView noteView = new NoteView(note);
            rootPane.getChildren().add(noteView);
            noteViews.add(noteView);
        }
    }

    public MusicNodeView() {
        setupGUI();
        NoteView noteView = new NoteView();
        rootPane.getChildren().add(noteView);
        noteViews.add(noteView);
    }

    @Override
    protected void addViews() {
        bindToSelf(rootPane);
    }

    public void setText(String text) {
        if (!noteViews.isEmpty()) {
            noteViews.getFirst().setText(text);
        }
    }

    public void setPosition(int position) {
        noteViews.forEach(noteView -> noteView.setPosition(position));
    }

    public void putAfter(MusicNodeView prevNode, double amount, boolean computeWidth) {
        double xPosition = prevNode.getLayoutX() + amount;
        xPosition += computeWidth ? prevNode.getNodeWidth() : 0;
        setLayoutX(xPosition);
    }

    public void setFaded() {
        noteViews.forEach(NoteView::setFaded);
    }

    private double getNodeWidth() {
        return noteViews.isEmpty() ? 0 : noteViews.getFirst().prefWidth(-1);
    }
}
