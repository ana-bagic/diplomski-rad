package pianolearn.diplomskirad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.controller.MainViewController;
import pianolearn.diplomskirad.view.MainView;

public class PianoLearn extends Application {

    @Override
    public void start(Stage stage) {
        MainViewController controller = new MainViewController();
        MainView view = new MainView(controller);

        Scene scene = new Scene(view);
        stage.setTitle(Strings.appTitle);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}