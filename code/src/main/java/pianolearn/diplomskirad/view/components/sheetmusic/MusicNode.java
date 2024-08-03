package pianolearn.diplomskirad.view.components.sheetmusic;

import javafx.geometry.VPos;
import javafx.scene.text.Text;
import pianolearn.diplomskirad.constants.Fonts;

import static pianolearn.diplomskirad.constants.Config.*;

public class MusicNode extends Text {

    public MusicNode() {
        this("");
    }

    public MusicNode(String text) {
        super(text);
        setFont(Fonts.music);
        setTextOrigin(VPos.CENTER);
        setY(STAFF_HEIGHT / 2 + FONT_CENTER_FIX);
    }

    public void raiseBy(int positions) {
        translate(-positions);
    }

    public void lowerBy(int positions) {
        translate(positions);
    }

    public void putAfter(Text prevNode, double space) {
        double xPosition = prevNode.getX() + prevNode.prefWidth(-1) + space * ELEMENTS_SPACING;
        setX(xPosition);
    }

    private void translate(int positions) {
        setTranslateY(positions * NOTE_PITCH_SPACING);
    }
}
