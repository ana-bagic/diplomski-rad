package pianolearn.diplomskirad.controller.components;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.constants.Config;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.helper.midi.MidiDeviceManager;
import pianolearn.diplomskirad.helper.midi.MidiInputReceiver;
import pianolearn.diplomskirad.listener.PlayChangeListener;
import pianolearn.diplomskirad.model.Hand;
import pianolearn.diplomskirad.model.KeyboardModel;
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

    private final MidiInputReceiver midiInputReceiver = MidiDeviceManager.INSTANCE.getReceiver();
    private final KeyboardModel keyboardModel = MidiDeviceManager.INSTANCE.getKeyboardModel();

    private PlayChangeListener playPauseListener;

    public PianoKeyboardController() {
        view = new PianoKeyboardView(new KeyboardModel(Config.LOWEST_PITCH, Config.HIGHEST_PITCH));

        setupListeners();
    }

    @Override
    public Pane getView() {
        return view;
    }

    private void setupListeners() {
        midiInputReceiver.setKeyPressedListener(this::keyPressed);
        midiInputReceiver.setKeyReleasedListener(this::keyReleased);

        Hand.RIGHT.addShowsListener((o, ov, nv) -> handChanged(Hand.RIGHT));
        Hand.LEFT.addShowsListener((o, ov, nv) -> handChanged(Hand.LEFT));
    }

    private void keyPressed(int midiKey) {
        PitchModel key = PitchModel.fromMidi(midiKey);
        view.setClicked(key.toString());

        if (!isWait) return;

        if (rightHandNotesPlaying.containsKey(key.toString())) {
            rightHandNotesPlaying.put(key.toString(), true);
        }

        if (leftHandNotesPlaying.containsKey(key.toString())) {
            leftHandNotesPlaying.put(key.toString(), true);
        }

        if (Hand.RIGHT.shows() && rightHandNotesPlaying.containsValue(false)
                || Hand.LEFT.shows() && leftHandNotesPlaying.containsValue(false)) return;

        playPauseListener.onAction(true);
    }

    private void keyReleased(int midiKey) {
        PitchModel key = PitchModel.fromMidi(midiKey);
        view.setReleased(key.toString());
    }

    private void handChanged(Hand hand) {
        for (String note : hand == Hand.RIGHT ? rightHandNotesPlaying.keySet() : leftHandNotesPlaying.keySet()) {
            if (hand.shows()) {
                view.setHighlight(note, hand);
            } else {
                view.removeHighlight(note);
            }
        }
    }

    public void playNotes(List<PitchModel> pitches, Hand hand) {
        Map<String, Boolean> notesPlaying = hand == Hand.RIGHT ? rightHandNotesPlaying : leftHandNotesPlaying;
        clearNotes(notesPlaying);
        if (pitches.isEmpty()) return;

        if (hand.shows()) {
            pitches.forEach(pitch -> view.setHighlight(pitch.toString(), hand));

            if (isWait) {
                boolean pause = pitches.stream().anyMatch(keyboardModel::containsPitch);
                playPauseListener.onAction(!pause);
            }
        }

        pitches.forEach(pitch -> {
            boolean containsPitch = keyboardModel.containsPitch(pitch);
            notesPlaying.put(pitch.toString(), !containsPitch);
        });
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

    public void setPlayPauseListener(PlayChangeListener listener) {
        playPauseListener = listener;
    }
}
