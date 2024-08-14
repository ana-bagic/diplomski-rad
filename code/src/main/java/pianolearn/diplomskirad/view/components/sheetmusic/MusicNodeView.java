package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.model.viewmodel.MusicNodeModel;
import pianolearn.diplomskirad.model.viewmodel.NoteModel;
import pianolearn.diplomskirad.view.BaseView;

public class MusicNodeView extends BaseView {

    private final Pane rootPane = new Pane();

    public MusicNodeView(MusicNodeModel model) {
        setupGUI();

        for (NoteModel note : model.getNotes()) {
            NoteView noteView = new NoteView(note);
            rootPane.getChildren().add(noteView);
        }
    }

    @Override
    protected void addViews() {
        bindToSelf(rootPane);
    }

    public void putAfter(Node prevNode, double amount) {
        double xPosition = prevNode.getLayoutX() + amount;
        setLayoutX(xPosition);
    }
}
