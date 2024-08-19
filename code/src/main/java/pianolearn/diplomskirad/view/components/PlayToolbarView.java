package pianolearn.diplomskirad.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.constants.Images;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.listener.EventListener;
import pianolearn.diplomskirad.listener.SpeedChangeListener;
import pianolearn.diplomskirad.model.PlaybackSpeed;
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

    private EventListener playPauseButtonListener;
    private EventListener stopButtonListener;
    private SpeedChangeListener speedSliderListener;
    private EventListener leftHandButtonListener;
    private EventListener rightHandButtonListener;

    private boolean isPlaying = false;
    private int oldSliderIndex = 0;
    private final PlaybackSpeed[] playbackSpeeds = PlaybackSpeed.values();
    private boolean isLeftShown = true;
    private boolean isRightShown = true;

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

        setupButton(playPauseButton, Colors.text, Strings.playButtonTooltip, Images.playIcon);
        setupButton(stopButton, Colors.text, Strings.stopButtonTooltip, Images.stopIcon);

        speedVBox.setSpacing(10);
        speedVBox.setPadding(new Insets(0, 15, 0, 15));
        speedVBox.setAlignment(Pos.CENTER);

        speedLabel.setFont(Fonts.micro);
        speedLabel.setTextFill(Colors.text);
        speedLabel.setText(Strings.speedLabel);

        StylesHelper.setupLabelSlider(speedSlider, playbackSpeeds);
        speedSlider.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

        setupButton(leftHandButton, Colors.accent, Strings.leftHandButtonTooltip, Images.leftHandIcon);
        setupButton(rightHandButton, Colors.accent, Strings.rightHandButtonTooltip, Images.rightHandIcon);
    }

    private void setupButton(Button button, Color backgroundColor, String tooltipText, Image image) {
        StylesHelper.setButtonSize(button, 80);
        StylesHelper.setButtonBackground(button, backgroundColor, Colors.highlight, 20);
        StylesHelper.setButtonTooltip(button, tooltipText);
        bindImageToButton(image, button);
    }

    @Override
    protected void setupActions() {
        playPauseButton.setOnAction(e -> playPauseClicked());
        stopButton.setOnAction(e -> stopButtonClicked());
        speedSlider.setOnMouseReleased(e -> sliderChanged());
        leftHandButton.setOnAction(e -> leftHandButtonClicked());
        rightHandButton.setOnAction(e -> rightHandButtonClicked());
    }

    private void playPauseClicked() {
        setPaused(isPlaying);
        playPauseButtonListener.onAction();
    }

    private void stopButtonClicked() {
        setPaused(true);
        stopButtonListener.onAction();
    }

    private void sliderChanged() {
        int index = speedSlider.valueProperty().intValue();
        if (index != oldSliderIndex) {
            oldSliderIndex = index;
            speedSliderListener.onSpeedChanged(playbackSpeeds[index]);
        }
    }

    private void leftHandButtonClicked() {
        if (!isLeftShown || isRightShown) {
            isLeftShown = !isLeftShown;
            Color backgroundColor = isLeftShown ? Colors.accent : Colors.text;
            StylesHelper.setButtonBackground(leftHandButton, backgroundColor, Colors.highlight, 20);
            leftHandButtonListener.onAction();
        }
    }

    private void rightHandButtonClicked() {
        if (!isRightShown || isLeftShown) {
            isRightShown = !isRightShown;
            Color backgroundColor = isRightShown ? Colors.accent : Colors.text;
            StylesHelper.setButtonBackground(rightHandButton, backgroundColor, Colors.highlight, 20);
            rightHandButtonListener.onAction();
        }
    }

    private void changePlayPauseButton(boolean isPlaying) {
        StylesHelper.setButtonTooltip(playPauseButton, isPlaying ? Strings.pauseButtonTooltip : Strings.playButtonTooltip);
        bindImageToButton(isPlaying ? Images.pauseIcon : Images.playIcon, playPauseButton);
    }

    public void setUsesBothHands(boolean usesBothHands) {
        if (!usesBothHands) {
            showNode(leftHandButton, false);
            showNode(rightHandButton, false);
        }
    }

    public void setPaused(boolean isPaused) {
        isPlaying = !isPaused;
        changePlayPauseButton(isPlaying);
    }

    public void setPlayPauseButtonListener(EventListener listener) {
        playPauseButtonListener = listener;
    }

    public void setStopButtonListener(EventListener listener) {
        stopButtonListener = listener;
    }

    public void setSpeedSliderListener(SpeedChangeListener listener) {
        speedSliderListener = listener;
    }

    public void setLeftHandButtonListener(EventListener listener) {
        leftHandButtonListener = listener;
    }

    public void setRightHandButtonListener(EventListener listener) {
        rightHandButtonListener = listener;
    }
}
