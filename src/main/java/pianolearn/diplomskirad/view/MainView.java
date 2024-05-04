package pianolearn.diplomskirad.view;

import javafx.scene.layout.StackPane;
import pianolearn.diplomskirad.controller.MainViewController;

public class MainView extends StackPane {
    
    private final MainViewController controller;
    
    public MainView(MainViewController controller) {
        this.controller = controller;
    }
}
