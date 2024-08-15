package pianolearn.diplomskirad.model.score;

import pianolearn.diplomskirad.helper.ScaleHelper;

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
        currentPitch = ScaleHelper.adjustPitch(currentPitch.key().getChromaNumber(), currentPitch.octave(), 1);
        return pitch;
    }
}
