package pianolearn.diplomskirad.constants;

import pianolearn.diplomskirad.model.viewmodel.LibraryItemModel;

public class LibraryData {

    private static final LibraryItemModel classicalEasySong
            = new LibraryItemModel("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.mozartSonata);
    private static final LibraryItemModel classicalMediumSong
            = new LibraryItemModel("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.mozartSonata);
    private static final LibraryItemModel classicalHardSong
            = new LibraryItemModel("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.mozartSonata);

    private static final LibraryItemModel modernEasySong
            = new LibraryItemModel("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.mozartSonata);
    private static final LibraryItemModel modernMediumSong
            = new LibraryItemModel("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.mozartSonata);
    private static final LibraryItemModel modernHardSong
            = new LibraryItemModel("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.mozartSonata);

    public static LibraryItemModel[] getClassicalSongs() {
        return new LibraryItemModel[]{classicalEasySong, classicalMediumSong, classicalHardSong};
    }

    public static LibraryItemModel[] getModernSongs() {
        return new LibraryItemModel[]{modernEasySong, modernMediumSong, modernHardSong};
    }
}
