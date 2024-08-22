package pianolearn.diplomskirad.helper.xml;

import org.audiveris.proxymusic.ScorePartwise;
import org.audiveris.proxymusic.util.Marshalling;

import java.io.*;
import java.util.Objects;

public enum XMLConverter {

    INSTANCE;

    private ScorePartwise score = null;

    public boolean unmarshall(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            Object unmarshalledObject = Objects.requireNonNull(Marshalling.unmarshal(inputStream));
            score = (ScorePartwise) unmarshalledObject;
            return true;
        } catch (Marshalling.UnmarshallingException | ClassCastException | NullPointerException | IOException e) {
            return false;
        }
    }

    public ScorePartwise getScore() {
        return score;
    }
}
