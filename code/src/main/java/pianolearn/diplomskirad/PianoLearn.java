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
        stage.setMinWidth(1000);
        stage.setMinHeight(850);

        NavigationController.INSTANCE.init(stage, new TitleViewController());

        // ovo treba obrisati za finalnu verziju
        File file = XmlFiles.getXmlFile(XmlFiles.mozartSonata);
        XMLConverter.INSTANCE.unmarshall(file);
        //NavigationController.INSTANCE.push(new PlayViewController());
    }

    public static void main(String[] args) {
        launch();
    }
}