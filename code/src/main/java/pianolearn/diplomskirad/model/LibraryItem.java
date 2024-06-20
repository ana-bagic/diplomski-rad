package pianolearn.diplomskirad.model;

import javafx.scene.image.Image;

public record LibraryItem(

        String songName,
        String artist,
        Image coverImage,
        String fileName
) {}
