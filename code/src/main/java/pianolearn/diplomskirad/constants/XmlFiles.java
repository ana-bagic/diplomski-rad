package pianolearn.diplomskirad.constants;

import java.io.File;
import java.util.Objects;

public class XmlFiles {

    public static final String furElise = "fur-elise";

    public static File getXmlFile(String fileName) {
        String resource = "/xmlFiles/" + fileName + ".xml";
        return new File(Objects.requireNonNull(XmlFiles.class.getResource(resource)).getFile());
    }
}
