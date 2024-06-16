package pianolearn.diplomskirad.constants;

import javafx.scene.image.Image;

import java.util.Objects;

public class Images {

    public static final Image uploadIcon = getImage("upload.png");

    public static final Image musicLibraryIcon = getImage("music-library.png");

    public static final Image settingsIcon = getImage("settings.png");

    public static final Image backArrowIcon = getImage("back-arrow.png");

    private static Image getImage(String image) {
        String resource = "/images/" + image;
        return new Image(Objects.requireNonNull(Images.class.getResourceAsStream(resource)));
    }
}
