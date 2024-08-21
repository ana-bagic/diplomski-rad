package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import pianolearn.diplomskirad.constants.*;
import pianolearn.diplomskirad.helper.StyleHelper;
import pianolearn.diplomskirad.listener.EventListener;

import static pianolearn.diplomskirad.helper.StyleHelper.*;

public class TitleView extends VBox {

    private final Label titleLabel = new Label();
    private final HBox buttonsStackHBox = new HBox();
    private final Button uploadButton = new Button();
    private final Button libraryButton = new Button();
    private final Button settingsButton = new Button();

    private EventListener uploadButtonListener;
    private EventListener libraryButtonListener;
    private EventListener settingsButtonListener;

    public TitleView() {
        setupView();
    }

    private void setupView() {
        getChildren().addAll(titleLabel, buttonsStackHBox);
        setBackground(background(Colors.background, null));
        setAlignment(Pos.CENTER);
        setSpacing(70);

        buttonsStackHBox.getChildren().addAll(uploadButton, libraryButton, settingsButton);
        buttonsStackHBox.setAlignment(Pos.CENTER);
        buttonsStackHBox.setSpacing(50);

        titleLabel.setFont(Fonts.title);
        titleLabel.setTextFill(Colors.accent);
        titleLabel.setText(Strings.appTitle);

        setupButton(uploadButton, Strings.uploadButtonTooltip, Images.uploadIcon);
        uploadButton.setOnAction(e -> uploadButtonListener.onAction());

        setupButton(libraryButton, Strings.libraryButtonTooltip, Images.musicLibraryIcon);
        libraryButton.setOnAction(e -> libraryButtonListener.onAction());

        setupButton(settingsButton, Strings.settingsButtonTooltip, Images.settingsIcon);
        settingsButton.setOnAction(e -> settingsButtonListener.onAction());
    }

    private void setupButton(Button button, String tooltipText, Image image) {
        StyleHelper.setButtonSize(button, 200);
        StyleHelper.setButtonBackground(button, Colors.text, Colors.highlight, 20);
        StyleHelper.setButtonImage(button, image);
        StyleHelper.setButtonTooltip(button, tooltipText);
    }

    public void setUploadButtonListener(EventListener listener) {
        uploadButtonListener = listener;
    }

    public void setLibraryButtonListener(EventListener listener) {
        libraryButtonListener = listener;
    }

    public void setSettingsButtonListener(EventListener listener) {
        settingsButtonListener = listener;
    }
}
