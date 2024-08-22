package pianolearn.diplomskirad.model;

import pianolearn.diplomskirad.helper.ScaleHelper;
import pianolearn.diplomskirad.model.score.PitchModel;

public class KeyboardModel {

    private final PitchModel firstPitch;
    private final PitchModel lastPitch;
    private final int numberOfWhiteKeys;

    public KeyboardModel(PitchModel firstPitch, PitchModel lastPitch) {
        if (firstPitch.compareTo(lastPitch) >= 0) {
            throw new IllegalArgumentException("First pitch must lower than the last pitch");
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

