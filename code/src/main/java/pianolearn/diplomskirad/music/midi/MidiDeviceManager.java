package pianolearn.diplomskirad.music.midi;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.DEBUG;

public class MidiDeviceManager {

    public static MidiInputReceiver getReceiver() {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

        for (int i = 0; i < infos.length; i++) {
            var info = infos[i];
            System.out.printf("%2d. %s%n    Opis: %s%n", i + 1, info.getVendor(), info.getDescription());
        }

        MidiDevice device = null;
        try {
            device = MidiSystem.getMidiDevice(infos[3]);
            device.open();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Device: " + device.getDeviceInfo().getDescription());

        Transmitter transmitter = null;
        try {
            transmitter = device.getTransmitter();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
        List<Transmitter> transmitters = device.getTransmitters();
        if (DEBUG) System.out.println("Broj postojećih transmittera: " + transmitters.size());

        MidiInputReceiver receiver = new MidiInputReceiver();
        transmitter.setReceiver(receiver);
        return receiver;
    }
}
