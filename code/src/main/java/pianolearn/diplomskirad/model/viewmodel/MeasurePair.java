package pianolearn.diplomskirad.model.viewmodel;

public record MeasurePair(

        boolean hasBothHands,
        MeasureModel rightHandModel,
        MeasureModel leftHandModel
) {}
