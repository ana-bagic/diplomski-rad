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

    public ScorePartwise getScore() {
        return score;
    }

    //    private void test() {
//        ScorePartwise score = Marshaller.INSTANCE.getScore();
//        ScorePartwise.Part part = score.getPart().getFirst();
//
//        for (ScorePartwise.Part.Measure measure: part.getMeasure()) {
//            for (Object object: measure.getNoteOrBackupOrForward()) {
//                try {
//                    Note note = (Note) object;
//                    Pitch pitch = note.getPitch();
//                    if (pitch != null) {
//                        System.out.println(PitchHelper.pitchToString(note.getPitch()));
//                    } else if (note.getUnpitched() != null) {
//                        System.out.println("Unpitched: " + note.getUnpitched());
//                    } else if (note.getRest() != null) {
//                        System.out.println("Rest: " + note.getRest());
//                    }
//                } catch (ClassCastException e) {
//                    System.out.println("class cast exception " + object);
//                }
//            }
//        }
//    }
}
