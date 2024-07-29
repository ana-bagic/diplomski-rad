package pianolearn.diplomskirad.music.midi;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.DEBUG;
import static pianolearn.diplomskirad.constants.Config.KEYBOARD_CONNECTED;

public class MidiDeviceManager {

    public static MidiInputReceiver getReceiver() {
        MidiInputReceiver receiver = new MidiInputReceiver();

        if (KEYBOARD_CONNECTED) {
            MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

            for (int i = 0; i < infos.length; i++) {
                var info = infos[i];
                System.out.printf("%2d. %s%n    Opis: %s%n", i + 1, info.getVendor(), info.getDescription());
            }

            MidiDevice device;
            try {
                device = MidiSystem.getMidiDevice(infos[3]);
                device.open();
            } catch (MidiUnavailableException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Device: " + device.getDeviceInfo().getDescription());

            Transmitter transmitter;
            try {
                transmitter = device.getTransmitter();
            } catch (MidiUnavailableException e) {
                throw new RuntimeException(e);
            }
            List<Transmitter> transmitters = device.getTransmitters();
            if (DEBUG) System.out.println("Broj postojećih transmittera: " + transmitters.size());

            transmitter.setReceiver(receiver);
        }

        return receiver;
    }
}
