package pianolearn.diplomskirad.model.note;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class KeyIterator implements Iterator<String> {

    private final Pitch currentPitch;
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
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        String next = currentPitch.toString();
        currentPitch.increase();
        return next;
    }
}
