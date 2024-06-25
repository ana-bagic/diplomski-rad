package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import pianolearn.diplomskirad.view.BaseNavigationView;
import pianolearn.diplomskirad.view.BaseView;

public class PlayView extends BaseNavigationView {

    private final HBox timeHBox = new HBox();
    private final Button waitButton = new Button();
    private final Button fiftySpeedButton = new Button();
    private final Button eightySpeedButton = new Button();
    private final Button hundredSpeedButton = new Button();
    private final HBox handHBox = new HBox();
    private final Button leftHandButton = new Button();
    private final Button rightHandButton = new Button();

    public PlayView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
        timeHBox.getChildren().addAll(waitButton, fiftySpeedButton, eightySpeedButton, hundredSpeedButton);
        handHBox.getChildren().addAll(leftHandButton, rightHandButton);
        topHBox.getChildren().addAll(timeHBox, handHBox);
    }

    @Override
    protected void styleViews() {
        super.styleViews();

        timeHBox.setAlignment(Pos.CENTER);
        timeHBox.setSpacing(20);

        handHBox.setAlignment(Pos.CENTER);
        handHBox.setSpacing(20);
    }

    public void setBottom(BaseView view) {
        rootPane.setBottom(view);
    }
}
