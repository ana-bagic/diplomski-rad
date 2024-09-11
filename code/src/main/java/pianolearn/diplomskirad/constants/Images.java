package pianolearn.diplomskirad.constants;

import javafx.scene.image.Image;

import java.util.Objects;

public class Images {

    private static final String iconsPath = "icons/";
    private static final String coversPath = "covers/";

    public static final Image uploadIcon = getImage(iconsPath + "upload.png");
    public static final Image musicLibraryIcon = getImage(iconsPath + "music-library.png");
    public static final Image keyboardIcon = getImage(iconsPath + "keyboard.png");

    public static final Image backArrowIcon = getImage(iconsPath + "back-arrow.png");

    public static final Image playIcon = getImage(iconsPath + "play.png");
    public static final Image pauseIcon = getImage(iconsPath + "pause.png");
    public static final Image stopIcon = getImage(iconsPath + "stop.png");
    public static final Image leftHandIcon = getImage(iconsPath + "left-hand.png");
    public static final Image rightHandIcon = getImage(iconsPath + "right-hand.png");

    public static final Image petzoldImage = getImage(coversPath + "petzold.jpeg");
    public static final Image beethovenImage = getImage(coversPath + "beethoven.jpg");
    public static final Image tchaikovskyImage = getImage(coversPath + "tchaikovsky.jpg");

    public static final Image felicianoImage = getImage(coversPath + "feliciano.jpeg");
    public static final Image pirateImage = getImage(coversPath + "pirate.png");
    public static final Image garlandImage = getImage(coversPath + "garland.jpg");

    private static Image getImage(String image) {
        String resource = "/images/" + image;
        return new Image(Objects.requireNonNull(Images.class.getResourceAsStream(resource)));
    }
}
