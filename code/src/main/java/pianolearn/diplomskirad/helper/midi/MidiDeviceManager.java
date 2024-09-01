package pianolearn.diplomskirad.helper.midi;

import pianolearn.diplomskirad.constants.Config;
import pianolearn.diplomskirad.model.KeyboardModel;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;
import java.util.*;

public enum MidiDeviceManager {

    INSTANCE;

    private MidiDevice.Info chosenDeviceInfo;
    private MidiDevice currentDevice;
    private final MidiInputReceiver receiver = new MidiInputReceiver();

    private KeyboardModel keyboardModel;

    public List<MidiDevice.Info> getDeviceInfos() {
        return Arrays.stream(MidiSystem.getMidiDeviceInfo()).toList();
    }

    public MidiDevice.Info getChosenDeviceInfo() {
        List<MidiDevice.Info> deviceInfos = getDeviceInfos();
        if (deviceInfos.isEmpty()) return null;

        if (chosenDeviceInfo == null || !deviceInfos.contains(chosenDeviceInfo)) {
            chosenDeviceInfo = deviceInfos.getLast();
        }
        return chosenDeviceInfo;
    }

    public void setDevice(MidiDevice.Info deviceInfo) {
        if (getDeviceInfos().isEmpty()) return;

        if (deviceInfo != null) chosenDeviceInfo = deviceInfo;
        if (chosenDeviceInfo == null) chosenDeviceInfo = getChosenDeviceInfo();

        close();

        try {
            assert chosenDeviceInfo != null;
            currentDevice = MidiSystem.getMidiDevice(chosenDeviceInfo);
            currentDevice.open();

            Transmitter transmitter = currentDevice.getTransmitter();
            transmitter.setReceiver(receiver);
        } catch (MidiUnavailableException | NullPointerException ignored) {}
    }

    public KeyboardModel getKeyboardModel() {
        if (keyboardModel == null) {
            keyboardModel = new KeyboardModel(Config.LOWEST_PITCH, Config.HIGHEST_PITCH);
        }
        return keyboardModel;
    }

    public void setKeyboardModel(KeyboardModel keyboardModel) {
        this.keyboardModel = keyboardModel;
    }

    public MidiInputReceiver getReceiver() {
        if (currentDevice == null) {
            setDevice(null);
        }
        return receiver;
    }

    public void close() {
        if (currentDevice != null && currentDevice.isOpen()) {
            currentDevice.close();
            currentDevice = null;
        }
    }
}
