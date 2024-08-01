package pianolearn.diplomskirad.view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Images;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.listener.ButtonClickListener;
import pianolearn.diplomskirad.view.BaseView;

public class PlayToolbarView extends BaseView {

    private final HBox rootPane = new HBox();
    private final HBox timeHBox = new HBox();
    private final Button waitButton = new Button();
    private final Button fiftySpeedButton = new Button();
    private final Button eightySpeedButton = new Button();
    private final Button hundredSpeedButton = new Button();
    private final HBox handHBox = new HBox();
    private final Button leftHandButton = new Button();
    private final Button rightHandButton = new Button();

    private ButtonClickListener leftHandButtonListener;
    private ButtonClickListener rightHandButtonListener;

    public PlayToolbarView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        timeHBox.getChildren().addAll(waitButton, fiftySpeedButton, eightySpeedButton, hundredSpeedButton);
        handHBox.getChildren().addAll(leftHandButton, rightHandButton);
        rootPane.getChildren().addAll(timeHBox, handHBox);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setSpacing(20);

        timeHBox.setAlignment(Pos.CENTER);
        timeHBox.setSpacing(20);

        handHBox.setAlignment(Pos.CENTER);
        handHBox.setSpacing(20);

        StylesHelper.setButtonSize(leftHandButton, 80);
        StylesHelper.setButtonBackground(leftHandButton, Colors.text, Colors.highlight, 10);
        StylesHelper.setButtonTooltip(leftHandButton, Strings.leftHandButtonTooltip);
        bindImageToButton(Images.leftHandIcon, leftHandButton);

        StylesHelper.setButtonSize(rightHandButton, 80);
        StylesHelper.setButtonBackground(rightHandButton, Colors.text, Colors.highlight, 10);
        StylesHelper.setButtonTooltip(rightHandButton, Strings.rightHandButtonTooltip);
        bindImageToButton(Images.rightHandIcon, rightHandButton);
    }

    @Override
    protected void setupActions() {
        leftHandButton.setOnAction(e -> leftHandButtonListener.onButtonClicked());
        rightHandButton.setOnAction(e -> rightHandButtonListener.onButtonClicked());
    }

    public void setLeftHandButtonListener(ButtonClickListener listener) {
        leftHandButtonListener = listener;
    }

    public void setRightHandButtonListener(ButtonClickListener listener) {
        rightHandButtonListener = listener;
    }
}
