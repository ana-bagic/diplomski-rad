package pianolearn.diplomskirad.model.viewmodel;

import java.util.LinkedList;
import java.util.List;

public class MusicNodeModel {

    private final List<NoteModel> notes = new LinkedList<>();
    private double distanceFromPrev;

    private int duration;

    public void addNote(NoteModel note, int duration) {
        notes.add(note);
        this.duration = Math.max(this.duration, duration);
    }

    public boolean isEmpty() {
        return notes.isEmpty();
    }

    public void setDistanceFromPrev(double distanceFromPrev) {
        this.distanceFromPrev = distanceFromPrev;
    }

    public List<NoteModel> getNotes() {
        return notes;
    }

    public double getDistanceFromPrev() {
        return distanceFromPrev;
    }

    public int getDuration() {
        return duration;
    }
}
