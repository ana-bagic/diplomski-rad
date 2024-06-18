package pianolearn.diplomskirad.music.midi;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

import static pianolearn.diplomskirad.constants.Config.DEBUG;

public class MidiInputReceiver implements Receiver {

    // data[0] is status (144 pressed, 128 released, 176 pedal)
    // data[1] is note value (0-127) 60 -> C4
    // data[2] is speed (0-127)
    @Override
    public void send(MidiMessage message, long timeStamp) {
        int status = message.getStatus();

        if (status == 128 || status == 144) {
            if (DEBUG) System.out.println("Message received: @" + timeStamp);
            byte[] data = message.getMessage();
            int length = message.getLength();
            if (DEBUG) System.out.println("  status: " + status + "  length: " + length);

            int key = data[1] & 0xFF;
            int speed = data[2] & 0xFF;
            if (DEBUG)
                System.out.println((status == 144 ? "Pressed" : "Released") + " key " + key + ", speed " + speed);
        }
    }

    @Override
    public void close() {
        if (DEBUG) System.out.println("MidiInputReceiver closed");
    }
}
