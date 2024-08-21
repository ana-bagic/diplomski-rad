package pianolearn.diplomskirad.helper.midi;

import javax.sound.midi.*;

import java.util.Timer;
import java.util.TimerTask;

import static pianolearn.diplomskirad.constants.Config.*;

public class Metronome implements Runnable {

    private Synthesizer synthesizer;
    private MidiChannel channel;

    private Timer timer;
    private int beatCounter;

    private long interval;
    private int beats;

    @Override
    public void run() {
        beatCounter = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (beatCounter < beats) {
                    int sound = beatCounter == 0 ? METRONOME_ACCENT_SOUND : METRONOME_SOUND;
                    channel.noteOn(sound, METRONOME_VOLUME);
                    beatCounter++;
                } else {
                    timer.cancel();
                }
            }
        }, 0, interval);
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
        if (timer != null) {
            timer.cancel();
        }
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setBeats(int beats) {
        this.beats = beats;
    }
}
