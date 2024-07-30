package pianolearn.diplomskirad.helper.xml;

import org.audiveris.proxymusic.Note;
import org.audiveris.proxymusic.ScorePartwise;

import java.util.Iterator;

public class ScorePartIterator {

    private final Iterator<ScorePartwise.Part.Measure> measureIterator;
    private Iterator<Object> noteIterator;

    public ScorePartIterator(ScorePartwise.Part part) {
        measureIterator = part.getMeasure().iterator();
        if (measureIterator.hasNext()) {
            noteIterator = measureIterator.next().getNoteOrBackupOrForward().iterator();
        }
    }

    public boolean hasNext() {
        return noteIterator != null && noteIterator.hasNext() ||
                measureIterator.hasNext();
    }

    public Note next() {
        if (!hasNext()) return null;

        Note note = null;

        while (!noteIterator.hasNext()) {
            noteIterator = measureIterator.next().getNoteOrBackupOrForward().iterator();
        }

        try {
            note = (Note) noteIterator.next();
        } catch (ClassCastException e) {
            System.out.println("Class cast exception");
        }

        return note;
    }
}
