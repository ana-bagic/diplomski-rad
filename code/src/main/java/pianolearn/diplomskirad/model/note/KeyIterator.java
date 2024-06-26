package pianolearn.diplomskirad.model.note;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class KeyIterator implements Iterator<Pitch> {

    private Pitch currentPitch;
    private final Pitch lastPitch;

    public KeyIterator(Pitch firstPitch, Pitch lastPitch) {
        currentPitch =  firstPitch;
        this.lastPitch = lastPitch;
    }

    @Override
    public boolean hasNext() {
        return currentPitch.lessThanOrEquals(lastPitch);
    }

    @Override
    public Pitch next() {
        if (!hasNext()) throw new NoSuchElementException();
        Pitch pitch = currentPitch;
        currentPitch = currentPitch.getIncreased();
        return pitch;
    }
}
