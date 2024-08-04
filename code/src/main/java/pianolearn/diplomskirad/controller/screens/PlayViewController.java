package pianolearn.diplomskirad.controller.screens;

import org.audiveris.proxymusic.Note;
import org.audiveris.proxymusic.Pitch;
import org.audiveris.proxymusic.ScorePartwise;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.controller.components.PianoKeyboardController;
import pianolearn.diplomskirad.controller.components.SheetMusicController;
import pianolearn.diplomskirad.helper.PitchHelper;
import pianolearn.diplomskirad.helper.midi.MidiDeviceManager;
import pianolearn.diplomskirad.helper.midi.MidiInputReceiver;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.helper.xml.ScorePartIterator;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.screens.PlayView;

public class PlayViewController implements BaseViewController {

    private final PlayView view = new PlayView();

    private final SheetMusicController sheetMusicController = new SheetMusicController();
    private final PianoKeyboardController pianoKeyboardController = new PianoKeyboardController();

    private final MidiInputReceiver midiInputReceiver = MidiDeviceManager.getReceiver();

    public PlayViewController() {
        setupViews();
        setupListeners();
        //play();
    }

    @Override
    public BaseView getView() {
        return view;
    }

    private void setupViews() {
        view.setSheetMusicView(sheetMusicController.getView());
        view.setPianoKeyboardView(pianoKeyboardController.getView());
    }

    private void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);

        midiInputReceiver.setKeyPressedListener(this::keyPressed);
        midiInputReceiver.setKeyReleasedListener(this::keyReleased);
    }

    private void keyPressed(int midiKey) {
        pianoKeyboardController.keyPressed(midiKey);
    }

    private void keyReleased(int midiKey) {
        pianoKeyboardController.keyReleased(midiKey);
    }

    private void play() {
        ScorePartwise.Part part = Score.rightHandPart();
        if (part == null) return;

        ScorePartIterator iterator = new ScorePartIterator(part);

        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note != null) {
                Pitch pitch = note.getPitch();

                if (pitch != null) {
                    System.out.println(PitchHelper.pitchToString(note.getPitch()) + " " + note.getType().getValue());
                } else if (note.getUnpitched() != null) {
                    System.out.println("Unpitched: " + note.getUnpitched());
                } else if (note.getRest() != null) {
                    System.out.println("\t" + note.getType().getValue());
                } else {
                    System.out.println("Something else");
                }
            }
        }
    }
}
