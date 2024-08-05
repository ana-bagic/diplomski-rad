package pianolearn.diplomskirad.model.score;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class KeyIterator implements Iterator<PitchModel> {

    private PitchModel currentPitch;
    private final PitchModel lastPitch;

    public KeyIterator(PitchModel firstPitch, PitchModel lastPitch) {
        currentPitch =  firstPitch;
        this.lastPitch = lastPitch;
    }

    @Override
    public boolean hasNext() {
        return currentPitch.lessThanOrEquals(lastPitch);
    }

    @Override
    public PitchModel next() {
        if (!hasNext()) throw new NoSuchElementException();

        PitchModel pitch = currentPitch;
        currentPitch = currentPitch.getIncreased();
        return pitch;
    }
}
