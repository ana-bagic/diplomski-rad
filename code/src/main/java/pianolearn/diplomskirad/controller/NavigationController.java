package pianolearn.diplomskirad.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class NavigationController {

    private final Stage stage;
    private final Stack<BaseViewController> controllerStack = new Stack<>();

    public NavigationController(Stage stage) {
        this.stage = stage;
    }

    public void start(BaseViewController viewController) {
        push(viewController);
        stage.show();
    }

    public void push(BaseViewController viewController) {
        controllerStack.push(viewController);
        showScene(viewController);
    }

    public void pop() {
        if (!controllerStack.isEmpty()) {
            controllerStack.pop();
            showScene(controllerStack.peek());
        }
    }

    private void showScene(BaseViewController viewController) {
        Scene scene = new Scene(viewController.getView(), 1200, 800);
        stage.setScene(scene);
    }
}
