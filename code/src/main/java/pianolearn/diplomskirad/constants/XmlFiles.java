package pianolearn.diplomskirad.constants;

import java.io.File;
import java.util.Objects;

public class XmlFiles {

    public static final String mozartSonata = "mozart-sonata";
    public static final String echigoJishi = "echigo-jishi";

    public static File getXmlFile(String fileName) {
        String resource = "/xmlFiles/" + fileName + ".xml";
        return new File(Objects.requireNonNull(XmlFiles.class.getResource(resource)).getFile());
    }
}
