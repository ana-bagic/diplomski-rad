package pianolearn.diplomskirad.listener;

import pianolearn.diplomskirad.model.score.PitchModel;

import java.util.List;

public interface PlayNotesListener {

    void onAction(List<PitchModel> pitches);
}
