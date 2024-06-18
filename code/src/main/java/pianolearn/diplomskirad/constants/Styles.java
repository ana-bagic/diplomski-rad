package pianolearn.diplomskirad.constants;

import java.util.Objects;

public class Styles {

    public static final String BASE_VIEW_STYLE = getStyle("base-view");

    public static final String TITLE_VIEW_STYLE = getStyle("title-view");

    public static final String SETTINGS_VIEW_STYLE = getStyle("settings-view");

    public static final String UPLOAD_VIEW_STYLE = getStyle("upload-view");

    public static final String LIBRARY_VIEW_STYLE = getStyle("library-view");

    public static final String PLAY_VIEW_STYLE = getStyle("play-view");

    private static String getStyle(String style) {
        String resource = "/styles/" + style + ".css";
        return Objects.requireNonNull(Styles.class.getResource(resource)).toExternalForm();
    }
}
