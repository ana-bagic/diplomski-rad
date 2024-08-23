package pianolearn.diplomskirad.controller.components;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Config;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.helper.midi.MidiDeviceManager;
import pianolearn.diplomskirad.helper.midi.MidiInputReceiver;
import pianolearn.diplomskirad.listener.PlayPauseListener;
import pianolearn.diplomskirad.model.score.PitchModel;
import pianolearn.diplomskirad.view.components.keyboard.PianoKeyboardView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PianoKeyboardController implements BaseViewController {

    private final PianoKeyboardView view;

    private final Map<String, Boolean> rightHandNotesPlaying = new HashMap<>();
    private final Map<String, Boolean> leftHandNotesPlaying = new HashMap<>();

    private final MidiInputReceiver midiInputReceiver = MidiDeviceManager.getReceiver();

    private PlayPauseListener playPauseListener;

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

        if (rightHandNotesPlaying.containsKey(key.toString())) {
            rightHandNotesPlaying.put(key.toString(), true);
        }

        if (leftHandNotesPlaying.containsKey(key.toString())) {
            leftHandNotesPlaying.put(key.toString(), true);
        }

        if (rightHandNotesPlaying.containsValue(false) || leftHandNotesPlaying.containsValue(false)) return;

        playPauseListener.onAction(true);
    }

    private void keyReleased(int midiKey) {
        PitchModel key = PitchModel.fromMidi(midiKey);
        view.removeHighlight(key.toString());
    }

    public void playNotesRightHand(List<String> notes) {
        playPauseListener.onAction(false);

        clearNotes(rightHandNotesPlaying);

        for (String note : notes) {
            view.setHighlight(note, Colors.accent);
            rightHandNotesPlaying.put(note, false);
        }
    }

    public void playNotesLeftHand(List<String> notes) {
        playPauseListener.onAction(false);

        clearNotes(leftHandNotesPlaying);

        for (String note : notes) {
            view.setHighlight(note, Colors.highlight);
            leftHandNotesPlaying.put(note, false);
        }
    }

    public void reset() {
        clearNotes(rightHandNotesPlaying);
        clearNotes(leftHandNotesPlaying);
    }

    private void clearNotes(Map<String, Boolean> notes) {
        notes.keySet().forEach(view::removeHighlight);
        notes.clear();
    }

    public void setPlayPauseListener(PlayPauseListener listener) {
        playPauseListener = listener;
    }
}
