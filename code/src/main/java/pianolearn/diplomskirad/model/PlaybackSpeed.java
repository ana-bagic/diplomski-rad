package pianolearn.diplomskirad.model;

import pianolearn.diplomskirad.constants.Strings;

public enum PlaybackSpeed {

    WAIT(Strings.sliderWait, 0),
    SPEED50(Strings.sliderSpeed50, 0.5),
    SPEED80(Strings.sliderSpeed80, 0.8),
    SPEED100(Strings.sliderSpeed100, 1);

    private final String label;
    private final double speed;

    PlaybackSpeed(String label, double speed) {
        this.label = label;
        this.speed = speed;
    }

    public String getLabel() {
        return label;
    }

    public double getSpeed() {
        return speed;
    }
}
