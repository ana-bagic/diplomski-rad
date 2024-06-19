package pianolearn.diplomskirad.constants;

import pianolearn.diplomskirad.model.LibraryItem;
import pianolearn.diplomskirad.model.SongDifficulty;

public class LibraryData {

    public static final int NUMBER_OF_SONGS_PER_ROW = 3;

    private static final LibraryItem classicalEasySong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, SongDifficulty.EASY, XmlFiles.furElise);
    private static final LibraryItem classicalMediumSong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, SongDifficulty.MEDIUM, XmlFiles.furElise);
    private static final LibraryItem classicalHardSong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, SongDifficulty.HARD, XmlFiles.furElise);

    private static final LibraryItem modernEasySong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, SongDifficulty.EASY, XmlFiles.furElise);
    private static final LibraryItem modernMediumSong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, SongDifficulty.MEDIUM, XmlFiles.furElise);
    private static final LibraryItem modernHardSong
            = new LibraryItem("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, SongDifficulty.HARD, XmlFiles.furElise);

    public static LibraryItem[] getClassicalSongs() {
        return new LibraryItem[]{classicalEasySong, classicalMediumSong, classicalHardSong};
    }

    public static LibraryItem[] getModernSongs() {
        return new LibraryItem[]{modernEasySong, modernMediumSong, modernHardSong};
    }
}
