package pianolearn.diplomskirad.view.screens;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.listener.ButtonClickListener;
import pianolearn.diplomskirad.view.BaseView;

public class UploadView extends BaseView {

    private final BorderPane rootPane = new BorderPane();

    private final Button backButton = new Button();

    private ButtonClickListener backButtonClicked;

    public UploadView() {
        super();
        setupGUI();
    }

    @Override
    protected void addViews() {
        rootPane.setTop(backButton);
    }

    @Override
    protected void setupActions() {
        backButton.setOnAction(e -> backButtonClicked.onButtonClicked());
    }

    @Override
    public Pane getRootPane() {
        return rootPane;
    }

    public void setBackButtonClicked(ButtonClickListener backButtonClicked) {
        this.backButtonClicked = backButtonClicked;
    }
}
