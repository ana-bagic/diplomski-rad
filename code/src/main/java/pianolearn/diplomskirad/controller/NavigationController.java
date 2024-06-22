package pianolearn.diplomskirad.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pianolearn.diplomskirad.constants.Colors;

import java.util.Stack;

public enum NavigationController {

    INSTANCE;

    private Stage stage;
    private final Stack<Scene> sceneStack = new Stack<>();

    public void init(Stage stage, BaseViewController viewController) {
        this.stage = stage;
        push(viewController);
        stage.show();
    }

    public void push(BaseViewController viewController) {
        double sceneWidth = 1200;
        double sceneHeight = 800;
        if (!sceneStack.isEmpty()) {
            sceneWidth = sceneStack.peek().getWidth();
            sceneHeight = sceneStack.peek().getHeight();
        }

        Scene scene = new Scene(viewController.getView(), sceneWidth, sceneHeight);
        scene.setFill(Colors.background);
        sceneStack.push(scene);
        stage.setScene(scene);
    }

    public void pop() {
        if (!sceneStack.isEmpty()) {
            sceneStack.pop();
            stage.setScene(sceneStack.peek());
        }
    }

    public Stage getStage() {
        return stage;
    }
}
