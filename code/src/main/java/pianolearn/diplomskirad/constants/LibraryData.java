package pianolearn.diplomskirad.constants;

import pianolearn.diplomskirad.model.LibraryItem;

public class LibraryData {

    private static final LibraryItem classicalEasySong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.furElise);
    private static final LibraryItem classicalMediumSong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.furElise);
    private static final LibraryItem classicalHardSong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.furElise);

    private static final LibraryItem modernEasySong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.furElise);
    private static final LibraryItem modernMediumSong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.furElise);
    private static final LibraryItem modernHardSong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.furElise);

    public static LibraryItem[] getClassicalSongs() {
        return new LibraryItem[]{classicalEasySong, classicalMediumSong, classicalHardSong};
    }

    public static LibraryItem[] getModernSongs() {
        return new LibraryItem[]{modernEasySong, modernMediumSong, modernHardSong};
    }
}
