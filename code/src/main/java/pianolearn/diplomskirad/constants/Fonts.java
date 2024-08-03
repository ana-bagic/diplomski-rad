package pianolearn.diplomskirad.constants;

import javafx.scene.text.Font;

public class Fonts {

    public static final Font title;
    public static final Font header;
    public static final Font body;
    public static final Font tooltip;
    public static final Font micro;
    public static final Font error = Font.font("Arial", 20);
    public static final Font key = Font.font("Arial", 12);
    public static final Font music;

    static {
        String retroOsarFont = "/fonts/retro-osar.ttf";
        Font.loadFont(Fonts.class.getResourceAsStream(retroOsarFont), 12);
        String bravuraFont = "/fonts/bravura.otf";
        Font.loadFont(Fonts.class.getResourceAsStream(bravuraFont), 12);

        title = Font.font("Retro Osar", 100);
        header = Font.font("Retro Osar", 50);
        body = Font.font("Retro Osar", 24);
        tooltip = Font.font("Retro Osar", 18);
        micro = Font.font("Retro Osar", 18);
        music = Font.font("Bravura Text", 80);
    }
}
