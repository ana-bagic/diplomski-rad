package pianolearn.diplomskirad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.controller.TitleViewController;
import pianolearn.diplomskirad.view.TitleView;

public class PianoLearn extends Application {

    @Override
    public void start(Stage stage) {
        TitleView view = new TitleView();
        TitleViewController controller = new TitleViewController(view);

        Scene scene = new Scene(view.getRoot(), 1000, 700);
        stage.setTitle(Strings.appTitle);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}