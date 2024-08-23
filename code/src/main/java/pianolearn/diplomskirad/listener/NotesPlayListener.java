package pianolearn.diplomskirad.listener;

import pianolearn.diplomskirad.model.score.PitchModel;

import java.util.List;

public interface NotesPlayListener {

    void onAction(List<String> notes);
}
