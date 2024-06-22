package pianolearn.diplomskirad.controller.screens;

import pianolearn.diplomskirad.constants.LibraryData;
import pianolearn.diplomskirad.constants.XmlFiles;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.music.xml.Marshaller;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.screens.LibraryView;

import java.io.File;

public class LibraryViewController implements BaseViewController {

    private final LibraryView view;

    public LibraryViewController() {
        view = new LibraryView(LibraryData.getClassicalSongs(), LibraryData.getModernSongs());
        setupListeners();
    }

    @Override
    public BaseView getView() {
        return view;
    }

    private void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);
        view.setCoverButtonListeners(this::chooseSong);
    }

    private void chooseSong(String fileName) {
        File file = XmlFiles.getXmlFile(fileName);
        Marshaller.INSTANCE.unmarshall(file);
        NavigationController.INSTANCE.push(new PlayViewController());
    }
}
