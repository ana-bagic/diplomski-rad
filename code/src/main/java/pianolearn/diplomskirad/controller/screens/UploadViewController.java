package pianolearn.diplomskirad.controller.screens;

import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.view.screens.UploadView;

import java.io.File;

public class UploadViewController extends BaseViewController {

    private final UploadView view = new UploadView();

    public UploadViewController() {
        super();
        setupListeners();
    }

    @Override
    public Pane getView() {
        return view.getRootPane();
    }

    private void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);
        view.setFileChooserButtonListener(this::chooseFile);
    }

    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Strings.chooseFileLabel);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(Strings.fileChooserXmlFiles, "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(NavigationController.INSTANCE.getStage());
        if (selectedFile != null) {
            view.setFileChosen(selectedFile.getName());
            view.enableConfirmButton();
        }
    }
}
