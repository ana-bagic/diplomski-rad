package pianolearn.diplomskirad.constants;

import javafx.scene.text.Font;

public class Fonts {

    public static void loadTitleFont() {
        String resource = "/fonts/retro-osar.ttf";
        Font loaded = Font.loadFont(Fonts.class.getResourceAsStream(resource), 12);
        System.out.println("font called " + loaded.getName());
    }
}
