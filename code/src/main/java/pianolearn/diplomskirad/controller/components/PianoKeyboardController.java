package pianolearn.diplomskirad.controller.components;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Config;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.helper.midi.MidiDeviceManager;
import pianolearn.diplomskirad.helper.midi.MidiInputReceiver;
import pianolearn.diplomskirad.model.score.PitchModel;
import pianolearn.diplomskirad.view.components.keyboard.PianoKeyboardView;

import java.util.LinkedList;
import java.util.List;

public class PianoKeyboardController implements BaseViewController {

    private final PianoKeyboardView view;

    private final List<PitchModel> rightHandNotesPlaying = new LinkedList<>();
    private final List<PitchModel> leftHandNotesPlaying = new LinkedList<>();

    private final MidiInputReceiver midiInputReceiver = MidiDeviceManager.getReceiver();

    public PianoKeyboardController() {
        view = new PianoKeyboardView(Config.KEYBOARD_DISPLAY_MODEL);

        setupListeners();
    }

    @Override
    public Pane getView() {
        return view;
    }

    private void setupListeners() {
        midiInputReceiver.setKeyPressedListener(this::keyPressed);
        midiInputReceiver.setKeyReleasedListener(this::keyReleased);
    }

    private void keyPressed(int midiKey) {
        PitchModel key = PitchModel.fromMidi(midiKey);
        view.setHighlight(key.toString(), Colors.accent);
    }

    private void keyReleased(int midiKey) {
        PitchModel key = PitchModel.fromMidi(midiKey);
        view.removeHighlight(key.toString());
    }

    public void playNotesRightHand(List<PitchModel> notes) {
        clearNotes(rightHandNotesPlaying);

        for (PitchModel note : notes) {
            if (note != null) {
                view.setHighlight(note.toString(), Colors.accent);
                rightHandNotesPlaying.add(note);
            }
        }
    }

    public void playNotesLeftHand(List<PitchModel> notes) {
        clearNotes(leftHandNotesPlaying);

        for (PitchModel note : notes) {
            if (note != null) {
                view.setHighlight(note.toString(), Colors.highlight);
                leftHandNotesPlaying.add(note);
            }
        }
    }

    public void reset() {
        clearNotes(rightHandNotesPlaying);
        clearNotes(leftHandNotesPlaying);
    }

    private void clearNotes(List<PitchModel> notes) {
        notes.forEach(n -> view.removeHighlight(n.toString()));
        notes.clear();
    }
}
