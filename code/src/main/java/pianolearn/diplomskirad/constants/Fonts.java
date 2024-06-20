package pianolearn.diplomskirad.constants;

import javafx.scene.text.Font;

public class Fonts {

    public static final Font title;
    public static final Font header;
    public static final Font body;
    public static final Font tooltip;
    public static final Font micro;
    public static final Font error = Font.font("Arial", 20);

    static {
        String resource = "/fonts/retro-osar.ttf";
        Font.loadFont(Fonts.class.getResourceAsStream(resource), 12);

        title = Font.font("Retro Osar", 100);
        header = Font.font("Retro Osar", 50);
        body = Font.font("Retro Osar", 24);
        tooltip = Font.font("Retro Osar", 18);
        micro = Font.font("Retro Osar", 18);
    }
}
