package pianolearn.diplomskirad.model.viewmodel;

import pianolearn.diplomskirad.model.score.PitchModel;

public class NoteModel {

    private final String type;
    private int position = 0;
    private String accidental = "";
    private String dot = "";
    private PitchModel pitch = null;

    public NoteModel(String type) {
        this.type = type;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setAccidental(String accidental) {
        this.accidental = accidental;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public void setPitch(PitchModel pitch) {
        this.pitch = pitch;
    }

    public String getType() {
        return type;
    }

    public int getPosition() {
        return position;
    }

    public String getAccidental() {
        return accidental;
    }

    public String getDot() {
        return dot;
    }

    public PitchModel getPitch() {
        return pitch;
    }
}
