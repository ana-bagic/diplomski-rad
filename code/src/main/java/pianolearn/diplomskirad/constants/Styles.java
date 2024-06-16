package pianolearn.diplomskirad.constants;

import java.util.Objects;

public class Styles {

    public static final String TITLE_VIEW_STYLE = getStyle("title-view");

    private static String getStyle(String style) {
        String resource = "/styles/" + style + ".css";
        return Objects.requireNonNull(Styles.class.getResource(resource)).toExternalForm();
    }
}
