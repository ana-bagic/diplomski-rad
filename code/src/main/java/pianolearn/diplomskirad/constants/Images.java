package pianolearn.diplomskirad.constants;

import javafx.scene.image.Image;

import java.util.Objects;

public class Images {

    private static final String iconsPath = "icons/";

    public static final Image uploadIcon = getImage(iconsPath + "upload.png");
    public static final Image musicLibraryIcon = getImage(iconsPath + "music-library.png");
    public static final Image settingsIcon = getImage(iconsPath + "settings.png");

    public static final Image backArrowIcon = getImage(iconsPath + "back-arrow.png");

    public static final Image beethovenImage = getImage("beethoven.jpg");

    private static Image getImage(String image) {
        String resource = "/images/" + image;
        return new Image(Objects.requireNonNull(Images.class.getResourceAsStream(resource)));
    }
}
