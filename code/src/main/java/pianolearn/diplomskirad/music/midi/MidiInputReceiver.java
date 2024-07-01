package pianolearn.diplomskirad.music.midi;

import pianolearn.diplomskirad.listener.PianoKeyInteractListener;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

import static pianolearn.diplomskirad.constants.Config.DEBUG;

public class MidiInputReceiver implements Receiver {

    private PianoKeyInteractListener keyPressedListener;
    private PianoKeyInteractListener keyReleasedListener;

    // released key is either status 128 or status 144 and speed 0
    // data[0] is status (144 pressed, 128 released, 176 pedal)
    // data[1] is note value (0-127) 60 -> C4
    // data[2] is speed (0-127)
    @Override
    public void send(MidiMessage message, long timeStamp) {
        int status = message.getStatus();

        if (status == 128 || status == 144) {
            byte[] data = message.getMessage();
            int key = data[1] & 0xFF;
            int speed = data[2] & 0xFF;
            // int length = message.getLength();

            if (status == 144 && speed != 0) {
                keyPressedListener.keyInteracted(key);
            } else {
                keyReleasedListener.keyInteracted(key);
            }
        }
    }

    @Override
    public void close() {
        if (DEBUG) System.out.println("MidiInputReceiver closed");
    }

    public void setKeyPressedListener(PianoKeyInteractListener listener) {
        keyPressedListener = listener;
    }

    public void setKeyReleasedListener(PianoKeyInteractListener listener) {
        keyReleasedListener = listener;
    }
}
