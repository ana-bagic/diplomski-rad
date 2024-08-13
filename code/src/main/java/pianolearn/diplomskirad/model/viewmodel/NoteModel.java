package pianolearn.diplomskirad.model.viewmodel;

import pianolearn.diplomskirad.model.score.PitchModel;

public record NoteModel(

        String type,
        int position,
        PitchModel pitch
) {}
