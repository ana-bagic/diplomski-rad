package pianolearn.diplomskirad.model;

import pianolearn.diplomskirad.model.note.Pitch;

public class KeyboardModel {

    private final Pitch firstPitch;
    private final Pitch lastPitch;
    private final int numberOfWhiteKeys;
    private final int indexSkip1;
    private final int indexSkip2;

    public KeyboardModel(Pitch firstPitch, Pitch lastPitch) {
        this.firstPitch = firstPitch;
        this.lastPitch = lastPitch;
        numberOfWhiteKeys = 52;
        indexSkip1 = 3;
        indexSkip2 = 9;
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

    public int getIndexSkip1() {
        return indexSkip1;
    }

    public int getIndexSkip2() {
        return indexSkip2;
    }
}

