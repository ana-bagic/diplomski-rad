package pianolearn.diplomskirad.controller.screens;

import javafx.stage.FileChooser;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.music.xml.Marshaller;
import pianolearn.diplomskirad.view.BaseView;
import pianolearn.diplomskirad.view.screens.UploadView;

import java.io.File;

public class UploadViewController implements BaseViewController {

    private final UploadView view = new UploadView();

    public UploadViewController() {
        setupListeners();
    }

    @Override
    public BaseView getView() {
        return view;
    }

    private void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);
        view.setFileChooserButtonListener(this::chooseFile);
        view.setConfirmButtonListener(() -> NavigationController.INSTANCE.push(new PlayViewController()));
    }

    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Strings.chooseFile);
        FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter(Strings.fileChooserXmlFiles, "*.xml");
        fileChooser.getExtensionFilters().add(xmlFilter);

        File selectedFile = fileChooser.showOpenDialog(NavigationController.INSTANCE.getStage());
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            view.setFileChosen(fileName);
            boolean success = Marshaller.INSTANCE.unmarshall(selectedFile);

            if (success) {
                view.setCanConfirm(true);
                view.clearError();
            } else {
                view.setCanConfirm(false);
                view.setError(Strings.xmlLoadError(fileName));
            }
        }
    }
}
