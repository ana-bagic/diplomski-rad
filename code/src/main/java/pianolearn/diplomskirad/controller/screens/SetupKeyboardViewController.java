package pianolearn.diplomskirad.controller.screens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.constants.Config;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.helper.midi.MidiDeviceManager;
import pianolearn.diplomskirad.helper.midi.MidiInputReceiver;
import pianolearn.diplomskirad.model.Hand;
import pianolearn.diplomskirad.model.KeyboardModel;
import pianolearn.diplomskirad.model.score.PitchModel;
import pianolearn.diplomskirad.view.screens.SetupKeyboardView;

import javax.sound.midi.MidiDevice;

public class SetupKeyboardViewController implements BaseViewController {

    private final SetupKeyboardView view = new SetupKeyboardView();

    private PitchModel lowestPitch;
    private PitchModel highestPitch;
    private PitchModel lastPressed;

    private final MidiInputReceiver midiInputReceiver = MidiDeviceManager.INSTANCE.getReceiver();

    public SetupKeyboardViewController() {
        lowestPitch = Config.LOWEST_PITCH;
        highestPitch = Config.HIGHEST_PITCH;
        lastPressed = highestPitch;

        setupListeners();
        setupView();
    }

    @Override
    public Pane getView() {
        return view;
    }

    private void setupListeners() {
        view.setBackButtonListener(this::goBack);
        view.setMidiDeviceChangeListener(MidiDeviceManager.INSTANCE::setDevice);
        view.setRefreshButtonListener(this::refreshMidiDevices);
        view.setConfirmButtonListener(this::confirm);

        midiInputReceiver.setKeyPressedListener(this::keyPressed);
    }

    private void setupView() {
        refreshMidiDevices();
        setHighlights();
    }

    private void goBack() {
        NavigationController.INSTANCE.pop();
    }

    private void refreshMidiDevices() {
        ObservableList<MidiDevice.Info> deviceInfos = FXCollections.observableList(MidiDeviceManager.INSTANCE.getDeviceInfos());
        view.setMidiDeviceItems(deviceInfos, MidiDeviceManager.INSTANCE.getChosenDeviceInfo());
    }

    private void confirm() {
        MidiDeviceManager.INSTANCE.setKeyboardModel(new KeyboardModel(lowestPitch, highestPitch));
        goBack();
    }

    private void keyPressed(int midiKey) {
        PitchModel key = PitchModel.fromMidi(midiKey);
        if (key.equals(lastPressed)) return;

        view.removeHighlight(lowestPitch.toString());
        view.removeHighlight(highestPitch.toString());
        if (key.compareTo(lastPressed) < 0) {
            lowestPitch = key;
            highestPitch = lastPressed;
        } else {
            lowestPitch = lastPressed;
            highestPitch = key;
        }
        setHighlights();

        lastPressed = key;
    }

    private void setHighlights() {
        view.setHighlight(lowestPitch.toString(), Hand.LEFT);
        view.setHighlight(highestPitch.toString(), Hand.RIGHT);
    }
}
