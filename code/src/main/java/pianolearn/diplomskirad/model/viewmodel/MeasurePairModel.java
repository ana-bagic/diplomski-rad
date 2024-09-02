package pianolearn.diplomskirad.model.viewmodel;

import java.util.List;

import static pianolearn.diplomskirad.constants.Config.BARLINE_NOTE_SPACE;
import static pianolearn.diplomskirad.constants.Config.NOTE_NOTE_SPACE;

public class MeasurePairModel {

    private final boolean hasBothHands;
    private final List<MusicNodeModel> rightHandMeasure;
    private final List<MusicNodeModel> leftHandMeasure;
    private double width;
    private double lastWidth;
    private int duration;
    private int lastDuration;

    public MeasurePairModel(boolean hasBothHands, List<MusicNodeModel> rightHandMeasure, List<MusicNodeModel> leftHandMeasure) {
        this.hasBothHands = hasBothHands;
        this.rightHandMeasure = rightHandMeasure;
        this.leftHandMeasure = leftHandMeasure;

        setWidthAndDuration();
    }

    private void setWidthAndDuration() {
        if (rightHandMeasure.isEmpty()) return;

        int minDurationRight = rightHandMeasure.stream().mapToInt(MusicNodeModel::getDuration).min().orElse(Integer.MAX_VALUE);
        int minDurationLeft = leftHandMeasure.stream().mapToInt(MusicNodeModel::getDuration).min().orElse(Integer.MAX_VALUE);
        double minDuration = Math.min(minDurationRight, minDurationLeft);

        setDistances(rightHandMeasure, minDuration);
        if (hasBothHands) {
            setDistances(leftHandMeasure, minDuration);
        }

        double notesWidth = rightHandMeasure.stream().mapToDouble(MusicNodeModel::getDistanceToNext).sum();
        width = notesWidth + BARLINE_NOTE_SPACE;
        duration = rightHandMeasure.stream().mapToInt(MusicNodeModel::getDuration).sum();

        MusicNodeModel lastNode = rightHandMeasure.getLast();
        if (hasBothHands && lastNode.getDuration() > leftHandMeasure.getLast().getDuration()) {
            lastNode = leftHandMeasure.getLast();
        }
        lastWidth = lastNode.getDistanceToNext() + BARLINE_NOTE_SPACE;
        lastDuration = lastNode.getDuration();
    }

    private static void setDistances(List<MusicNodeModel> measure, double minDuration) {
        double distanceFromPrev = BARLINE_NOTE_SPACE;
        for (MusicNodeModel model : measure) {
            double distanceToNext = (model.getDuration() / minDuration) * NOTE_NOTE_SPACE;
            model.setDistanceFromPrev(distanceFromPrev);
            model.setDistanceToNext(distanceToNext);
            distanceFromPrev = distanceToNext;
        }
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

    public double getLastWidth() {
        return lastWidth;
    }

    public int getDuration() {
        return duration;
    }

    public int getLastDuration() {
        return lastDuration;
    }
}
