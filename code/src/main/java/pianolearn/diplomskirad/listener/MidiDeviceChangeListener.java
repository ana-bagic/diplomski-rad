package pianolearn.diplomskirad.listener;

import javax.sound.midi.MidiDevice;

public interface MidiDeviceChangeListener {

    void onAction(MidiDevice.Info deviceInfo);
}
