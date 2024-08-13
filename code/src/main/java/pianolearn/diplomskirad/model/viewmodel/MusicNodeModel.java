package pianolearn.diplomskirad.model.viewmodel;

import pianolearn.diplomskirad.constants.Config;

import java.util.LinkedList;
import java.util.List;

public class MusicNodeModel {

    private final List<NoteModel> notes = new LinkedList<>();
    private double distanceFromPrev = Config.ELEMENTS_SPACING;

    public void addNote(NoteModel note) {
        notes.add(note);
    }

    public List<NoteModel> getNotes() {
        return notes;
    }

    public boolean isEmpty() {
        return notes.isEmpty();
    }

    public double getDistanceFromPrev() {
        return distanceFromPrev;
    }
}
