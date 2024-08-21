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
import pianolearn.diplomskirad.listener.EventListener;
import pianolearn.diplomskirad.listener.SpeedChangeListener;
import pianolearn.diplomskirad.model.PlaybackSpeed;

import java.util.Objects;

import static pianolearn.diplomskirad.helper.StyleHelper.*;

public class PlayToolbarView extends HBox {

    private final Button playPauseButton = new Button();
    private final Button stopButton = new Button();
    private final VBox speedVBox = new VBox();
    private final Label speedLabel = new Label();
    private final Slider speedSlider = new Slider();
    private final Button leftHandButton = new Button();
    private final Button rightHandButton = new Button();

    private boolean isPlaying = false;
    private int oldSliderIndex = 0;
    private final PlaybackSpeed[] playbackSpeeds = PlaybackSpeed.values();
    private boolean isLeftShown = true;
    private boolean isRightShown = true;

    private EventListener playPauseButtonListener;
    private EventListener stopButtonListener;
    private SpeedChangeListener speedSliderListener;
    private EventListener leftHandButtonListener;
    private EventListener rightHandButtonListener;

    public PlayToolbarView() {
        setupView();
    }

    private void setupView() {
        getChildren().addAll(playPauseButton, stopButton, speedVBox, leftHandButton, rightHandButton);
        setSpacing(20);
        setAlignment(Pos.CENTER);

        setupButton(playPauseButton, Colors.text, Strings.playButtonTooltip, Images.playIcon);
        playPauseButton.setOnAction(e -> playPauseClicked());

        setupButton(stopButton, Colors.text, Strings.stopButtonTooltip, Images.stopIcon);
        stopButton.setOnAction(e -> stopButtonClicked());

        speedVBox.getChildren().addAll(speedLabel, speedSlider);
        speedVBox.setSpacing(10);
        speedVBox.setPadding(new Insets(0, 15, 0, 15));
        speedVBox.setAlignment(Pos.CENTER);

        speedLabel.setFont(Fonts.micro);
        speedLabel.setTextFill(Colors.text);
        speedLabel.setText(Strings.speedLabel);

        setupLabelSlider(speedSlider, playbackSpeeds);
        speedSlider.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        speedSlider.setOnMouseReleased(e -> sliderChanged());

        setupButton(leftHandButton, Colors.accent, Strings.leftHandButtonTooltip, Images.leftHandIcon);
        leftHandButton.setOnAction(e -> leftHandButtonClicked());

        setupButton(rightHandButton, Colors.accent, Strings.rightHandButtonTooltip, Images.rightHandIcon);
        rightHandButton.setOnAction(e -> rightHandButtonClicked());
    }

    private void setupButton(Button button, Color backgroundColor, String tooltipText, Image image) {
        setButtonSize(button, 80);
        setButtonBackground(button, backgroundColor, Colors.highlight, 20);
        setButtonTooltip(button, tooltipText);
        setButtonImage(button, image);
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
            speedSliderListener.onAction(playbackSpeeds[index]);
        }
    }

    private void leftHandButtonClicked() {
        if (!isLeftShown || isRightShown) {
            isLeftShown = !isLeftShown;
            Color backgroundColor = isLeftShown ? Colors.accent : Colors.text;
            setButtonBackground(leftHandButton, backgroundColor, Colors.highlight, 20);
            leftHandButtonListener.onAction();
        }
    }

    private void rightHandButtonClicked() {
        if (!isRightShown || isLeftShown) {
            isRightShown = !isRightShown;
            Color backgroundColor = isRightShown ? Colors.accent : Colors.text;
            setButtonBackground(rightHandButton, backgroundColor, Colors.highlight, 20);
            rightHandButtonListener.onAction();
        }
    }

    public void setPaused(boolean isPaused) {
        isPlaying = !isPaused;
        setButtonTooltip(playPauseButton, isPlaying ? Strings.pauseButtonTooltip : Strings.playButtonTooltip);
        setButtonImage(playPauseButton, isPlaying ? Images.pauseIcon : Images.playIcon);
    }

    public void setUsesBothHands(boolean usesBothHands) {
        if (!usesBothHands) {
            showNode(leftHandButton, false);
            showNode(rightHandButton, false);
        }
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
