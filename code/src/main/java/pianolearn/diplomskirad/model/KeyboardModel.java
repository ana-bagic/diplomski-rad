package pianolearn.diplomskirad.model;

import pianolearn.diplomskirad.model.note.Pitch;

public class KeyboardModel {

    private final Pitch firstPitch;
    private final Pitch lastPitch;
    private final int numberOfWhiteKeys;

    public KeyboardModel(Pitch firstPitch, Pitch lastPitch) {
        this.firstPitch = firstPitch;
        this.lastPitch = lastPitch;
        numberOfWhiteKeys = 52;
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

