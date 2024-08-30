package pianolearn.diplomskirad.helper.midi;

import pianolearn.diplomskirad.model.KeyboardModel;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;
import java.util.*;

public class MidiDeviceManager {

    private static MidiDevice.Info chosenDeviceInfo;
    private static MidiInputReceiver receiver;
    private static MidiDevice currentDevice;

    private static KeyboardModel keyboardModel;

    public static List<MidiDevice.Info> getDeviceInfos() {
        return Arrays.stream(MidiSystem.getMidiDeviceInfo()).toList();
    }

    public static MidiDevice.Info getChosenDeviceInfo() {
        List<MidiDevice.Info> deviceInfos = getDeviceInfos();
        if (deviceInfos.isEmpty()) return null;

        if (chosenDeviceInfo == null || !deviceInfos.contains(chosenDeviceInfo)) {
            chosenDeviceInfo = deviceInfos.getLast();
        }
        return chosenDeviceInfo;
    }

    public static void setDevice(MidiDevice.Info deviceInfo) {
        receiver = new MidiInputReceiver();
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

    public static KeyboardModel getKeyboardModel() {
        return keyboardModel;
    }

    public static void setKeyboardModel(KeyboardModel keyboardModel) {
        MidiDeviceManager.keyboardModel = keyboardModel;
    }

    public static MidiInputReceiver getReceiver() {
        if (receiver == null) setDevice(null);
        return receiver;
    }

    public static void close() {
        if (currentDevice != null && currentDevice.isOpen()) {
            currentDevice.close();
        }
    }
}
