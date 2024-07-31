package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Images;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.listener.ButtonClickListener;
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

    private ButtonClickListener leftHandButtonListener;
    private ButtonClickListener rightHandButtonListener;

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

        Insets insets = topHBox.getPadding();
        topHBox.setPadding(new Insets(20, insets.getRight(), 20, insets.getLeft()));

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

    public void setSheetMusicView(BaseView view) {
        rootPane.setCenter(view);
    }

    public void setPianoKeyboardView(BaseView view) {
        rootPane.setBottom(view);
    }

    public void setLeftHandButtonListener(ButtonClickListener listener) {
        leftHandButtonListener = listener;
    }

    public void setRightHandButtonListener(ButtonClickListener listener) {
        rightHandButtonListener = listener;
    }
}
