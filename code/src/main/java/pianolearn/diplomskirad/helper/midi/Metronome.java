package pianolearn.diplomskirad.helper.midi;

import javax.sound.midi.*;

import static pianolearn.diplomskirad.constants.Config.*;

public class Metronome implements Runnable {

    private Synthesizer synthesizer;
    private MidiChannel channel;

    private long interval;
    private int beats;

    @Override
    public void run() {
        try {
            start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void start() throws InterruptedException {
        for (int i = 0; i < beats; i++) {
            int sound = i == 0 ? METRONOME_ACCENT_SOUND : METRONOME_SOUND;
            channel.noteOn(sound, METRONOME_VOLUME);
            Thread.sleep(interval);
            channel.noteOff(sound);
        }
    }

    public void open() throws MidiUnavailableException {
        synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();

        Instrument[] instruments = synthesizer.getDefaultSoundbank().getInstruments();
        synthesizer.loadInstrument(instruments[0]);

        channel = synthesizer.getChannels()[9];
    }

    public void close() {
        synthesizer.close();
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setBeats(int beats) {
        this.beats = beats;
    }
}
