package pianolearn.diplomskirad;

import pianolearn.diplomskirad.music.midi.MidiInputReceiver;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;
import java.util.List;

import static pianolearn.diplomskirad.constants.Config.DEBUG;

public class TestApp {

    public static void main(String[] args) throws MidiUnavailableException {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

        for (int i = 0; i < infos.length; i++) {
            var info = infos[i];
            System.out.printf("%2d. %s%n    Opis: %s%n", i + 1, info.getVendor(), info.getDescription());
        }

        MidiDevice device = MidiSystem.getMidiDevice(infos[3]);
        System.out.println("Device: " + device.getDeviceInfo().getDescription());
        device.open();

        Transmitter transmitter = device.getTransmitter();
        List<Transmitter> transmitters = device.getTransmitters();
        if (DEBUG) System.out.println("Broj postojećih transmittera: " + transmitters.size());
        transmitter.setReceiver(new MidiInputReceiver());
    }
}
