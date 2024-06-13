package pianolearn.diplomskirad;

import org.audiveris.proxymusic.ScorePartwise;
import org.audiveris.proxymusic.util.Marshalling;

import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.audiveris.proxymusic.util.Marshalling.getContext;

public class MusicXmlTest {

    /**
     * Temporary area.
     */
    private static final File TEMP_DIR = new File("src/main/resources/temp");

    /**
     * Name of the temporary XML file.
     */
    private static final String FILE_NAME = "fur-elise.xml";

    public static void main(String[] args) throws Exception {
        File xmlFile = new File(TEMP_DIR, FILE_NAME);
        InputStream is = new FileInputStream(xmlFile);

        ScorePartwise scorePartwise = (ScorePartwise) Marshalling.unmarshal(is);

        is.close();

        Marshaller marshaller = getContext(ScorePartwise.class).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(scorePartwise, System.out);
    }
}
