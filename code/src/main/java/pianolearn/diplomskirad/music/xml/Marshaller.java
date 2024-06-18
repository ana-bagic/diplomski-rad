package pianolearn.diplomskirad.music.xml;

import org.audiveris.proxymusic.ScorePartwise;
import org.audiveris.proxymusic.util.Marshalling;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.Objects;

import static org.audiveris.proxymusic.util.Marshalling.getContext;

public enum Marshaller {

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

    public void marshall() throws JAXBException {
        javax.xml.bind.Marshaller marshaller = getContext(ScorePartwise.class).createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(score, System.out);
    }
}
