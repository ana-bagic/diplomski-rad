package pianolearn.diplomskirad.controller.screens;

import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.MainEngine;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.controller.components.PianoKeyboardController;
import pianolearn.diplomskirad.controller.components.SheetMusicController;
import pianolearn.diplomskirad.helper.midi.MidiDeviceManager;
import pianolearn.diplomskirad.helper.midi.MidiInputReceiver;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.screens.PlayView;

public class PlayViewController implements BaseViewController {

    private final PlayView view = new PlayView();

    private final SheetMusicController sheetMusicController;
    private final PianoKeyboardController pianoKeyboardController;

    private final MidiInputReceiver midiInputReceiver = MidiDeviceManager.getReceiver();
    private final MainEngine engine = MainEngine.INSTANCE;

    public PlayViewController() {
        sheetMusicController = new SheetMusicController();
        pianoKeyboardController = new PianoKeyboardController();

        setupView();
        setupListeners();
    }

    @Override
    public BaseView getView() {
        return view;
    }

    private void setupView() {
        view.setUsesBothHands(engine.usesBothHands());
        view.setSheetMusicView(sheetMusicController.getView());
        view.setPianoKeyboardView(pianoKeyboardController.getView());
    }

    private void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);

        view.setPlayPauseButtonListener(engine::playPauseButtonClicked);
        view.setStopButtonListener(engine::stopButtonClicked);
        view.setSpeedSliderListener(engine::speedChanged);
        view.setLeftHandButtonListener(engine::leftHandButtonClicked);
        view.setRightHandButtonListener(engine::rightHandButtonClicked);

        engine.setFinishedListener(view::setPaused);

        midiInputReceiver.setKeyPressedListener(this::keyPressed);
        midiInputReceiver.setKeyReleasedListener(this::keyReleased);
    }

    private void keyPressed(int midiKey) {
        pianoKeyboardController.keyPressed(midiKey);
    }

    private void keyReleased(int midiKey) {
        pianoKeyboardController.keyReleased(midiKey);
    }
}
