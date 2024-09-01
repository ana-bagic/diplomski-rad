package pianolearn.diplomskirad.helper.midi;

import pianolearn.diplomskirad.listener.PianoKeyInteractListener;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class MidiInputReceiver implements Receiver {

    private PianoKeyInteractListener keyPressedListener;
    private PianoKeyInteractListener keyReleasedListener;

    // data[1] is note value (0-127) 60 -> C4
    // data[2] is speed (0-127)
    // released key is either status 128 or status 144 and speed 0
    @Override
    public void send(MidiMessage message, long timeStamp) {
        int status = message.getStatus();

        if (status == 128 || status == 144) {
            byte[] data = message.getMessage();
            int key = data[1] & 0xFF;
            int speed = data[2] & 0xFF;

            if (status == 144 && speed != 0 && keyPressedListener != null) {
                keyPressedListener.onAction(key);
            } else if (keyReleasedListener != null) {
                keyReleasedListener.onAction(key);
            }
        }
    }

    @Override
    public void close() {}

    public void setKeyPressedListener(PianoKeyInteractListener listener) {
        keyPressedListener = listener;
    }

    public void setKeyReleasedListener(PianoKeyInteractListener listener) {
        keyReleasedListener = listener;
    }
}
