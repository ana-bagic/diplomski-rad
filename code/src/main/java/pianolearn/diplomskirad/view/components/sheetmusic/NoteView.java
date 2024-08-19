package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.geometry.VPos;
import javafx.scene.text.Text;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.model.viewmodel.NoteModel;

import static pianolearn.diplomskirad.constants.Config.*;
import static pianolearn.diplomskirad.constants.Config.NOTE_PITCH_SPACING;

public class NoteView extends Text {

    public NoteView() {
        setupNode();
    }

    public NoteView(NoteModel model) {
        this(String.format("%s %s %s", model.getAccidental(), model.getType(), model.getDot()), model.getPosition());
    }

    public NoteView(String type, int position) {
        super(type);
        setupNode();
        setPosition(position);
    }

    private void setupNode() {
        setFont(Fonts.music);
        setTextOrigin(VPos.CENTER);
        setY(STAFF_HEIGHT / 2 + FONT_CENTER_FIX);
        setFill(Colors.notes);
    }

    public void setPosition(int position) {
        setTranslateY(-position * NOTE_PITCH_SPACING);
    }

    public void setFaded() {
        setFill(Colors.notesFaded);
    }
}
