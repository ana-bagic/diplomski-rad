package pianolearn.diplomskirad.music.xml;

import org.audiveris.proxymusic.ScorePartwise;
import org.audiveris.proxymusic.util.Marshalling;

import java.io.*;
import java.util.Objects;

public enum XMLConverter {

    INSTANCE;

    private ScorePartwise score;

    public boolean unmarshall(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            Object unmarshalledObject = Objects.requireNonNull(Marshalling.unmarshal(inputStream));
            score = (ScorePartwise) unmarshalledObject;
            return true;
        } catch (Marshalling.UnmarshallingException | ClassCastException | NullPointerException | IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ScorePartwise getScore() {
        return score;
    }
}
