package pianolearn.diplomskirad.controller.components;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.constants.Config;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.helper.midi.MidiDeviceManager;
import pianolearn.diplomskirad.helper.midi.MidiInputReceiver;
import pianolearn.diplomskirad.helper.midi.MidiPlayback;
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

    private final Map<PitchModel, Boolean> rightHandNotesPlaying = new HashMap<>();
    private final Map<PitchModel, Boolean> leftHandNotesPlaying = new HashMap<>();

    private boolean isWait = true;

    private final MidiInputReceiver midiInputReceiver = MidiDeviceManager.INSTANCE.getReceiver();
    private final KeyboardModel keyboardModel = MidiDeviceManager.INSTANCE.getKeyboardModel();
    private final MidiPlayback midiPlayback = MidiPlayback.INSTANCE;

    private PlayChangeListener playPauseListener;

    public PianoKeyboardController() {
        view = new PianoKeyboardView(new KeyboardModel(Config.LOWEST_PITCH, Config.HIGHEST_PITCH));
        midiPlayback.open();

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

        if (rightHandNotesPlaying.containsKey(key)) {
            rightHandNotesPlaying.put(key, true);
        }

        if (leftHandNotesPlaying.containsKey(key)) {
            leftHandNotesPlaying.put(key, true);
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
        for (PitchModel note : hand == Hand.RIGHT ? rightHandNotesPlaying.keySet() : leftHandNotesPlaying.keySet()) {
            if (hand.shows()) {
                view.setHighlight(note.toString(), hand);
            } else {
                view.removeHighlight(note.toString());
            }
        }
    }

    public void playNotes(List<PitchModel> notes, Hand hand) {
        Map<PitchModel, Boolean> notesPlaying = hand == Hand.RIGHT ? rightHandNotesPlaying : leftHandNotesPlaying;
        if (notes.isEmpty()) return;

        if (hand.shows()) {
            notes.forEach(note -> {
                view.setHighlight(note.toString(), hand);
                midiPlayback.play(note.toMidi());
            });

            if (isWait) {
                boolean pause = notes.stream().anyMatch(keyboardModel::containsPitch);
                playPauseListener.onAction(!pause);
            }
        }

        notes.forEach(note -> {
            boolean containsPitch = keyboardModel.containsPitch(note);
            notesPlaying.put(note, !containsPitch);
        });
    }

    public void endNotes(Hand hand) {
        Map<PitchModel, Boolean> notesPlaying = hand == Hand.RIGHT ? rightHandNotesPlaying : leftHandNotesPlaying;
        notesPlaying.keySet().forEach(note -> midiPlayback.stop(note.toMidi()));

        notesPlaying.keySet().forEach(note -> view.removeHighlight(note.toString()));
        notesPlaying.clear();
    }

    public void reset() {
        endNotes(Hand.RIGHT);
        endNotes(Hand.LEFT);
    }

    public void setWait(boolean isWait) {
        this.isWait = isWait;
    }

    public void setPlayPauseListener(PlayChangeListener listener) {
        playPauseListener = listener;
    }
}
