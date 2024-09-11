package pianolearn.diplomskirad.constants;

import java.io.File;
import java.util.Objects;

public class XmlFiles {

    public static final String minuetInGMajor = "minuet-in-g-major";
    public static final String furElise = "fur-elise";
    public static final String danceOfTheSugarPlumFairy = "dance-of-the-sugar-plum-fairy";

    public static final String felizNavidad = "feliz-navidad";
    public static final String heIsAPirate = "he-is-a-pirate";
    public static final String overTheRainbow = "over-the-rainbow";

    public static File getXmlFile(String fileName) {
        String resource = "/xmlFiles/" + fileName + ".xml";
        return new File(Objects.requireNonNull(XmlFiles.class.getResource(resource)).getFile());
    }
}
