package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import pianolearn.diplomskirad.constants.*;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.listener.ButtonClickListener;
import pianolearn.diplomskirad.view.BaseView;

public class TitleView extends BaseView {

    private final VBox rootPane = new VBox();
    private final Label titleLabel = new Label();
    private final HBox buttonsStackHBox = new HBox();
    private final Button uploadButton = new Button();
    private final Button libraryButton = new Button();
    private final Button settingsButton = new Button();

    private ButtonClickListener uploadButtonListener;
    private ButtonClickListener libraryButtonListener;
    private ButtonClickListener settingsButtonListener;

    public TitleView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        buttonsStackHBox.getChildren().addAll(uploadButton, libraryButton, settingsButton);
        rootPane.getChildren().addAll(titleLabel, buttonsStackHBox);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setBackground(StylesHelper.background(Colors.background, null));
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setSpacing(70);

        titleLabel.setFont(Fonts.title);
        titleLabel.setTextFill(Colors.accent);
        titleLabel.setText(Strings.appTitle);

        buttonsStackHBox.setAlignment(Pos.CENTER);
        buttonsStackHBox.setSpacing(50);

        setupButton(uploadButton, Strings.uploadButtonTooltip, Images.uploadIcon);
        setupButton(libraryButton, Strings.libraryButtonTooltip, Images.musicLibraryIcon);
        setupButton(settingsButton, Strings.settingsButtonTooltip, Images.settingsIcon);
    }

    @Override
    protected void setupActions() {
        uploadButton.setOnAction(e -> uploadButtonListener.onButtonClicked());
        libraryButton.setOnAction(e -> libraryButtonListener.onButtonClicked());
        settingsButton.setOnAction(e -> settingsButtonListener.onButtonClicked());
    }

    private void setupButton(Button button, String tooltipText, Image image) {
        StylesHelper.setButtonSize(button, 200);
        StylesHelper.setButtonBackground(button, Colors.text, Colors.highlight, 20);
        StylesHelper.setButtonTooltip(button, tooltipText);
        bindImageToButton(image, button);
    }

    public void setUploadButtonListener(ButtonClickListener listener) {
        uploadButtonListener = listener;
    }

    public void setLibraryButtonListener(ButtonClickListener listener) {
        libraryButtonListener = listener;
    }

    public void setSettingsButtonListener(ButtonClickListener listener) {
        settingsButtonListener = listener;
    }
}
