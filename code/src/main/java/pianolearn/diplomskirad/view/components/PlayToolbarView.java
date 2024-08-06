package pianolearn.diplomskirad.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.constants.Images;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.helper.xml.Score;
import pianolearn.diplomskirad.listener.ButtonClickListener;
import pianolearn.diplomskirad.view.BaseView;

import java.util.Objects;

public class PlayToolbarView extends BaseView {

    private final HBox rootPane = new HBox();
    private final Button playPauseButton = new Button();
    private final Button stopButton = new Button();
    private final VBox speedVBox = new VBox();
    private final Label speedLabel = new Label();
    private final Slider speedSlider = new Slider();
    private final Button leftHandButton = new Button();
    private final Button rightHandButton = new Button();

    private ButtonClickListener leftHandButtonListener;
    private ButtonClickListener rightHandButtonListener;

    private boolean isPlay = true;

    public PlayToolbarView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        speedVBox.getChildren().addAll(speedLabel, speedSlider);
        rootPane.getChildren().addAll(playPauseButton, stopButton, speedVBox, leftHandButton, rightHandButton);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setSpacing(20);
        rootPane.setAlignment(Pos.CENTER);

        setupButton(playPauseButton, Strings.playButtonTooltip, Images.playIcon);
        setupButton(stopButton, Strings.stopButtonTooltip, Images.stopIcon);

        speedVBox.setSpacing(10);
        speedVBox.setPadding(new Insets(0, 15, 0, 15));
        speedVBox.setAlignment(Pos.CENTER);

        speedLabel.setFont(Fonts.micro);
        speedLabel.setTextFill(Colors.text);
        speedLabel.setText(Strings.speedLabel);

        String[] labels = new String[]
                {Strings.sliderWait, Strings.sliderSpeed50, Strings.sliderSpeed80, Strings.sliderSpeed100};
        StylesHelper.setupLabelSlider(speedSlider, labels);
        speedSlider.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

        setupButton(leftHandButton, Strings.leftHandButtonTooltip, Images.leftHandIcon);
        setupButton(rightHandButton, Strings.rightHandButtonTooltip, Images.rightHandIcon);

        if (Score.numberOfParts() == 1) {
            leftHandButton.setVisible(false);
            rightHandButton.setVisible(false);
        }
    }

    @Override
    protected void setupActions() {
        playPauseButton.setOnAction(e -> playPauseClicked());
        speedSlider.valueProperty().addListener((o, oldValue, newValue) -> sliderChanged(oldValue.intValue(), newValue.intValue()));
        leftHandButton.setOnAction(e -> leftHandButtonListener.onButtonClicked());
        rightHandButton.setOnAction(e -> rightHandButtonListener.onButtonClicked());
    }

    private void setupButton(Button button, String tooltipText, Image image) {
        StylesHelper.setButtonSize(button, 80);
        StylesHelper.setButtonBackground(button, Colors.text, Colors.highlight, 20);
        StylesHelper.setButtonTooltip(button, tooltipText);
        bindImageToButton(image, button);
    }

    private void playPauseClicked() {
        isPlay = !isPlay;
        if (isPlay) {
            StylesHelper.setButtonTooltip(playPauseButton, Strings.playButtonTooltip);
            bindImageToButton(Images.playIcon, playPauseButton);
        } else  {
            StylesHelper.setButtonTooltip(playPauseButton, Strings.pauseButtonTooltip);
            bindImageToButton(Images.pauseIcon, playPauseButton);
        }
    }

    private void sliderChanged(int oldValue, int newValue) {
        System.out.println("oldValue: " + oldValue + " newValue: " + newValue);
    }

    public void setLeftHandButtonListener(ButtonClickListener listener) {
        leftHandButtonListener = listener;
    }

    public void setRightHandButtonListener(ButtonClickListener listener) {
        rightHandButtonListener = listener;
    }
}
