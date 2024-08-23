package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.model.score.PitchModel;
import pianolearn.diplomskirad.model.viewmodel.NoteModel;

import static pianolearn.diplomskirad.constants.Config.*;
import static pianolearn.diplomskirad.constants.Config.NOTE_PITCH_SPACING;

public class NoteView extends Text {

    private PitchModel pitch = null;

    public NoteView() {
        setupNode();
    }

    public NoteView(NoteModel model) {
        super(String.format("%s %s %s", model.getAccidental(), model.getType(), model.getDot()));
        setPosition(model.getPosition());
        pitch = model.getPitch();
        setupNode();
    }

    private void setupNode() {
        setFont(Fonts.music);
        setTextOrigin(VPos.CENTER);
        setY(STAFF_HEIGHT / 2 + FONT_CENTER_FIX);
        setFill(Colors.notes);
    }

    public String getNote() {
        return pitch == null ? null : pitch.toString();
    }

    public void setPosition(int position) {
        setTranslateY(-position * NOTE_PITCH_SPACING);
    }

    public void setColor(Color color) {
        setFill(color);
    }
}
