package pianolearn.diplomskirad.controller.screens;

import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.music.xml.Marshaller;
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
        FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter(Strings.fileChooserXmlFiles, "*.xml");
        fileChooser.getExtensionFilters().add(xmlFilter);

        File selectedFile = fileChooser.showOpenDialog(NavigationController.INSTANCE.getStage());
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            view.setFileChosen(fileName);
            boolean success = Marshaller.INSTANCE.unmarshall(selectedFile);

            if (success) {
                view.enableConfirm(true);
                view.setError(Strings.empty);
            } else {
                view.enableConfirm(false);
                view.setError(Strings.xmlLoadError(fileName));
            }
        } else {
            view.enableConfirm(false);
            view.setError(Strings.fileNotChosenError);
        }
    }
}
