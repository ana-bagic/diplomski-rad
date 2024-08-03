package pianolearn.diplomskirad;

import javafx.application.Application;
import javafx.stage.Stage;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.XmlFiles;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.controller.screens.PlayViewController;
import pianolearn.diplomskirad.controller.screens.TitleViewController;
import pianolearn.diplomskirad.helper.xml.XMLConverter;

import java.io.File;

public class PianoLearn extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle(Strings.appTitle);
        stage.setMinWidth(850);
        stage.setMinHeight(750);

        NavigationController.INSTANCE.init(stage, new TitleViewController());

        File file = XmlFiles.getXmlFile(XmlFiles.echigoJishi);
        XMLConverter.INSTANCE.unmarshall(file);
        NavigationController.INSTANCE.push(new PlayViewController());
    }

    public static void main(String[] args) {
        launch();
    }
}