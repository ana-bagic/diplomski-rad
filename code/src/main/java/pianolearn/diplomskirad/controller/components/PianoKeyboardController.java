package pianolearn.diplomskirad.controller.components;

import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Config;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.helper.midi.MidiDeviceManager;
import pianolearn.diplomskirad.helper.midi.MidiInputReceiver;
import pianolearn.diplomskirad.model.score.PitchModel;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.components.keyboard.PianoKeyboardView;

public class PianoKeyboardController implements BaseViewController {

    private final PianoKeyboardView view;

    private final MidiInputReceiver midiInputReceiver = MidiDeviceManager.getReceiver();

    public PianoKeyboardController() {
        view = new PianoKeyboardView(Config.KEYBOARD_MODEL);

        setupListeners();
    }

    @Override
    public BaseView getView() {
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

    //    private void tempKeyPress() {
//        Scene scene = NavigationController.INSTANCE.getStage().getScene();
//        scene.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.T) {
//                System.out.println("Key 'T' was pressed!");
//            } else if (event.getCode() == KeyCode.F) {
//                System.out.println("Key 'F' was pressed!");
//            }
//        });
//    }
}
