package pianolearn.diplomskirad.controller.screens;

import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.controller.BaseViewController;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.helper.xml.XMLConverter;
import pianolearn.diplomskirad.view.screens.UploadView;

import java.io.File;

public class UploadViewController implements BaseViewController {

    private final UploadView view = new UploadView();

    public UploadViewController() {
        setupListeners();
    }

    @Override
    public Pane getView() {
        return view;
    }

    private void setupListeners() {
        view.setBackButtonListener(NavigationController.INSTANCE::pop);

        view.setFileChooseButtonListener(this::chooseFile);
        view.setConfirmButtonListener(() -> NavigationController.INSTANCE.push(new PlayViewController()));
    }

    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Strings.chooseFile);
        FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter(Strings.fileChooserXmlFiles, "*.xml", "*.musicxml");
        fileChooser.getExtensionFilters().add(xmlFilter);

        File selectedFile = fileChooser.showOpenDialog(NavigationController.INSTANCE.getStage());
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            view.setFileChosen(fileName);
            view.setCanConfirm(false);

            boolean success = XMLConverter.INSTANCE.unmarshall(selectedFile);
            if (!success) {
                view.setError(Strings.xmlLoadError(fileName));
                return;
            }

            if (Score.noPianoPart()) {
                view.setError(Strings.xmlPartsError(fileName));
                return;
            }

            int staves = Score.staves();
            if (staves > 2) {
                view.setError(Strings.xmlStavesError(fileName, staves));
                return;
            }
            
            view.setCanConfirm(true);
            view.clearError();
        }
    }
}
