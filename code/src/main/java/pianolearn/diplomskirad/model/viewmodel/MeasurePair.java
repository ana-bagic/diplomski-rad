package pianolearn.diplomskirad.model.viewmodel;

import java.util.List;

public class MeasurePair {

    private final boolean hasBothHands;
    private final List<MusicNodeModel> rightHandMeasure;
    private final List<MusicNodeModel> leftHandMeasure;
    private double width;
    private double notesWidthWithoutLast;
    private List<MusicNodeModel> mainHand;

    public MeasurePair(boolean hasBothHands, List<MusicNodeModel> rightHandMeasure, List<MusicNodeModel> leftHandMeasure) {
        this.hasBothHands = hasBothHands;
        this.rightHandMeasure = rightHandMeasure;
        this.leftHandMeasure = leftHandMeasure;
    }

    public boolean hasBothHands() {
        return hasBothHands;
    }

    public List<MusicNodeModel> getRightHandMeasure() {
        return rightHandMeasure;
    }

    public List<MusicNodeModel> getLeftHandMeasure() {
        return leftHandMeasure;
    }

    public double getWidth() {
        return width;
    }

    public double getNotesWidthWithoutLast() {
        return notesWidthWithoutLast;
    }

    public List<MusicNodeModel> getMainHand() {
        return mainHand;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setNotesWidthWithoutLast(double notesWidthWithoutLast) {
        this.notesWidthWithoutLast = notesWidthWithoutLast;
    }

    public void setMainHand(boolean isRight) {
        mainHand = isRight ? rightHandMeasure : leftHandMeasure;
    }
}
