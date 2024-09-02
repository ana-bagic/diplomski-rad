package pianolearn.diplomskirad.helper.midi;

import pianolearn.diplomskirad.constants.Config;

import javax.sound.midi.*;

public enum MidiPlayback {

    INSTANCE;

    private Synthesizer synthesizer;
    private MidiChannel channel;

    public void open() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();

            Instrument[] instruments = synthesizer.getDefaultSoundbank().getInstruments();
            synthesizer.loadInstrument(instruments[0]);

            channel = synthesizer.getChannels()[0];
            channel.programChange(0);
        } catch (MidiUnavailableException ignored) {}
    }

    public void close() {
        if (synthesizer != null) {
            synthesizer.close();
        }
    }

    public void play(int midiKey) {
        if (channel != null) {
            channel.noteOn(midiKey, Config.PLAYBACK_VOLUME);
        }
    }

    public void stop(int midiKey) {
        if (channel != null) {
            channel.noteOff(midiKey);
        }
    }
}
