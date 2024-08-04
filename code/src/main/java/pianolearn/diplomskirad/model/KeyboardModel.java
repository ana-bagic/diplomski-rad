package pianolearn.diplomskirad.model;

import pianolearn.diplomskirad.helper.xml.ScaleHelper;
import pianolearn.diplomskirad.model.score.Pitch;

public class KeyboardModel {

    private final Pitch firstPitch;
    private final Pitch lastPitch;
    private final int numberOfWhiteKeys;

    public KeyboardModel(Pitch firstPitch, Pitch lastPitch) {
        if (lastPitch.lessThanOrEquals(firstPitch)) {
            throw new IllegalArgumentException("First pitch must be less than or equal to the last pitch");
        }

        this.firstPitch = firstPitch;
        this.lastPitch = lastPitch;

        numberOfWhiteKeys = ScaleHelper.getInterval(firstPitch, lastPitch) + 1;
    }

    public Pitch getFirstPitch() {
        return firstPitch;
    }

    public Pitch getLastPitch() {
        return lastPitch;
    }

    public int getNumberOfWhiteKeys() {
        return numberOfWhiteKeys;
    }
}

