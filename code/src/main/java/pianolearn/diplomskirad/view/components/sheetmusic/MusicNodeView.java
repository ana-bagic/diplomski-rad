package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.geometry.VPos;
import javafx.scene.text.Text;
import pianolearn.diplomskirad.constants.Fonts;

import static pianolearn.diplomskirad.constants.Config.*;

public class MusicNodeView extends Text {

    public MusicNodeView() {
        this("", 0);
    }

    public MusicNodeView(String text, int position) {
        super(text);
        setFont(Fonts.music);
        setTextOrigin(VPos.CENTER);
        setY(STAFF_HEIGHT / 2 + FONT_CENTER_FIX);
        position(position);
    }

    public void putAfter(Text prevNode, double space) {
        double xPosition = prevNode.getX() + prevNode.prefWidth(-1) + space * ELEMENTS_SPACING;
        setX(xPosition);
    }

    public void position(int position) {
        setTranslateY(-position * NOTE_PITCH_SPACING);
    }
}
