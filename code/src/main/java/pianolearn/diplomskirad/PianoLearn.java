package pianolearn.diplomskirad;

import javafx.application.Application;
import javafx.stage.Stage;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.controller.screens.TitleViewController;

public class PianoLearn extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle(Strings.appTitle);
        stage.setMinWidth(1000);
        stage.setMinHeight(850);

        NavigationController.INSTANCE.init(stage, new TitleViewController());
    }

    public static void main(String[] args) {
        launch();
    }
}