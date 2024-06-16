package pianolearn.diplomskirad;

import javafx.application.Application;
import javafx.stage.Stage;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.controller.TitleViewController;

public class PianoLearn extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle(Strings.appTitle);

        NavigationController navigationController = new NavigationController(stage);
        TitleViewController controller = new TitleViewController(navigationController);

        navigationController.start(controller);
    }

    public static void main(String[] args) {
        launch();
    }
}