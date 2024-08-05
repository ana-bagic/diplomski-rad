package pianolearn.diplomskirad.model;

import pianolearn.diplomskirad.helper.xml.ScaleHelper;
import pianolearn.diplomskirad.model.score.PitchModel;

public class KeyboardModel {

    private final PitchModel firstPitch;
    private final PitchModel lastPitch;
    private final int numberOfWhiteKeys;

    public KeyboardModel(PitchModel firstPitch, PitchModel lastPitch) {
        if (lastPitch.lessThanOrEquals(firstPitch)) {
            throw new IllegalArgumentException("First pitch must be less than or equal to the last pitch");
        }

        this.firstPitch = firstPitch;
        this.lastPitch = lastPitch;

        numberOfWhiteKeys = ScaleHelper.getInterval(firstPitch, lastPitch) + 1;
    }

    public PitchModel getFirstPitch() {
        return firstPitch;
    }

    public PitchModel getLastPitch() {
        return lastPitch;
    }

    public int getNumberOfWhiteKeys() {
        return numberOfWhiteKeys;
    }
}

