package pianolearn.diplomskirad.controller.screens;

import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.constants.LibraryData;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.XmlFiles;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.music.xml.Marshaller;
import pianolearn.diplomskirad.view.screens.LibraryView;

import java.io.File;

public class LibraryViewController implements BaseViewController {

    private final LibraryView view = new LibraryView();

    public LibraryViewController() {
        super();
        setupListeners();
        addSongs();
    }

    @Override
    public Pane getView() {
        return view;
    }

    private void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);
        view.setCoverButtonListeners(this::chooseSong);
    }

    private void addSongs() {
        view.setClassicalSongs(LibraryData.getClassicalSongs());
        view.setModernSongs(LibraryData.getModernSongs());
    }

    private void chooseSong(String fileName) {
        File file = XmlFiles.getXmlFile(fileName);
        Marshaller.INSTANCE.unmarshall(file);
        NavigationController.INSTANCE.push(new PlayViewController());
    }
}
