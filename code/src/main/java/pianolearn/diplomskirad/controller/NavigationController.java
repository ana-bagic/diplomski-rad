package pianolearn.diplomskirad.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class NavigationController {

    private final Stage stage;
    private final Stack<Scene> sceneStack = new Stack<>();

    public NavigationController(Stage stage) {
        this.stage = stage;
    }

    public void start(BaseViewController viewController) {
        push(viewController);
        stage.show();
    }

    public void push(BaseViewController viewController) {
        Scene scene = new Scene(viewController.getView(), 1200, 800);
        sceneStack.push(scene);
        stage.setScene(scene);
    }

    public void pop() {
        if (!sceneStack.isEmpty()) {
            sceneStack.pop();
            stage.setScene(sceneStack.peek());
        }
    }
}
