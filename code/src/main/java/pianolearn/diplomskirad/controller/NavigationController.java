package pianolearn.diplomskirad.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public enum NavigationController {

    INSTANCE;

    private Stage stage;
    private final Stack<BaseViewController> controllerStack = new Stack<>();

    public void init(Stage stage, BaseViewController viewController) {
        this.stage = stage;
        controllerStack.push(viewController);
        stage.setScene(new Scene(viewController.getView(), 1200, 800));
        stage.show();
    }

    public void push(BaseViewController viewController) {
        controllerStack.push(viewController);
        stage.getScene().setRoot(viewController.getView());
    }

    public void pop() {
        if (!controllerStack.isEmpty()) {
            controllerStack.pop();
            stage.getScene().setRoot(controllerStack.peek().getView());
        }
    }

    public Stage getStage() {
        return stage;
    }
}
