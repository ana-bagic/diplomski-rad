package pianolearn.diplomskirad.helper.midi;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;

public class MidiDeviceManager {

    public static MidiInputReceiver getReceiver() {
        MidiInputReceiver receiver = new MidiInputReceiver();
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

        try {
            MidiDevice device = MidiSystem.getMidiDevice(infos[3]);
            device.open();

            Transmitter transmitter = device.getTransmitter();
            transmitter.setReceiver(receiver);
        } catch (MidiUnavailableException | IndexOutOfBoundsException e) {
            return receiver;
        }

        return receiver;
    }
}
