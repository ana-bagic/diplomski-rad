package pianolearn.diplomskirad.controller.components;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Config;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.helper.midi.MidiDeviceManager;
import pianolearn.diplomskirad.helper.midi.MidiInputReceiver;
import pianolearn.diplomskirad.listener.PlayChangedListener;
import pianolearn.diplomskirad.model.Hand;
import pianolearn.diplomskirad.model.score.PitchModel;
import pianolearn.diplomskirad.view.components.keyboard.PianoKeyboardView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PianoKeyboardController implements BaseViewController {

    private final PianoKeyboardView view;

    private final Map<String, Boolean> rightHandNotesPlaying = new HashMap<>();
    private final Map<String, Boolean> leftHandNotesPlaying = new HashMap<>();

    private boolean isWait = true;
    private boolean rightHandShows = true;
    private boolean leftHandShows = true;

    private final MidiInputReceiver midiInputReceiver = MidiDeviceManager.getReceiver();

    private PlayChangedListener playPauseListener;

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

        if (!isWait) return;

        if (rightHandNotesPlaying.containsKey(key.toString())) {
            rightHandNotesPlaying.put(key.toString(), true);
        }

        if (leftHandNotesPlaying.containsKey(key.toString())) {
            leftHandNotesPlaying.put(key.toString(), true);
        }

        if (rightHandShows && rightHandNotesPlaying.containsValue(false)
                || leftHandShows && leftHandNotesPlaying.containsValue(false)) return;

        playPauseListener.onAction(true);
    }

    private void keyReleased(int midiKey) {
        PitchModel key = PitchModel.fromMidi(midiKey);
        view.removeHighlight(key.toString());
    }

    public void playNotesRightHand(List<String> notes) {
        clearNotes(rightHandNotesPlaying);

        if (rightHandShows && isWait && !notes.isEmpty()) playPauseListener.onAction(false);
        if (rightHandShows) notes.forEach(note -> view.setHighlight(note, Hand.RIGHT.getKeyColor()));
        notes.forEach(note -> rightHandNotesPlaying.put(note, false));
    }

    public void playNotesLeftHand(List<String> notes) {
        clearNotes(leftHandNotesPlaying);

        if (leftHandShows && isWait && !notes.isEmpty()) playPauseListener.onAction(false);
        if (leftHandShows) notes.forEach(note -> view.setHighlight(note, Hand.LEFT.getKeyColor()));
        notes.forEach(note -> leftHandNotesPlaying.put(note, false));
    }

    public void reset() {
        clearNotes(rightHandNotesPlaying);
        clearNotes(leftHandNotesPlaying);
    }

    private void clearNotes(Map<String, Boolean> notes) {
        notes.keySet().forEach(view::removeHighlight);
        notes.clear();
    }

    public void setWait(boolean isWait) {
        this.isWait = isWait;
    }

    public void handChanged(Hand hand, boolean handShows) {
        if (hand == Hand.RIGHT) rightHandShows = handShows;
        else leftHandShows = handShows;

        for (String note : hand == Hand.RIGHT ? rightHandNotesPlaying.keySet() : leftHandNotesPlaying.keySet()) {
            if (handShows) {
                view.setHighlight(note, hand.getKeyColor());
            } else {
                view.removeHighlight(note);
            }
        }
    }

    public void setPlayPauseListener(PlayChangedListener listener) {
        playPauseListener = listener;
    }
}
