package pianolearn.diplomskirad.constants;

import pianolearn.diplomskirad.model.viewmodel.LibraryItemModel;

public class LibraryData {

    private static final LibraryItemModel classicalEasySong
            = new LibraryItemModel("Minuet In G Major", "Christian Petzold", Images.petzoldImage, XmlFiles.minuetInGMajor);
    private static final LibraryItemModel classicalMediumSong
            = new LibraryItemModel("Für Elise", "Ludwig van Beethoven", Images.beethovenImage, XmlFiles.furElise);
    private static final LibraryItemModel classicalHardSong
            = new LibraryItemModel("Dance Of The Sugar Plum Fairy", "Pyotr Ilyich Tchaikovsky", Images.tchaikovskyImage, XmlFiles.danceOfTheSugarPlumFairy);

    private static final LibraryItemModel modernEasySong
            = new LibraryItemModel("Feliz Navidad", "José Feliciano", Images.felicianoImage, XmlFiles.felizNavidad);
    private static final LibraryItemModel modernMediumSong
            = new LibraryItemModel("He Is A Pirate", "Hans Zimmer, Klaus Badelt", Images.pirateImage, XmlFiles.heIsAPirate);
    private static final LibraryItemModel modernHardSong
            = new LibraryItemModel("Over The Rainbow", "Harold Arlen", Images.garlandImage, XmlFiles.overTheRainbow);

    public static LibraryItemModel[] getClassicalSongs() {
        return new LibraryItemModel[]{classicalEasySong, classicalMediumSong, classicalHardSong};
    }

    public static LibraryItemModel[] getModernSongs() {
        return new LibraryItemModel[]{modernEasySong, modernMediumSong, modernHardSong};
    }
}
