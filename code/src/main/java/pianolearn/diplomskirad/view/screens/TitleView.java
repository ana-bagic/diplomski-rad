package pianolearn.diplomskirad.view.screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pianolearn.diplomskirad.constants.*;
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
        getStylesheets().add(Styles.TITLE_VIEW_STYLE);
        rootPane.setBackground(Styles.background(Colors.background, null));
        rootPane.getStyleClass().add("background");

        titleLabel.setFont(Fonts.title);
        titleLabel.setTextFill(Colors.accent);
        titleLabel.setText(Strings.appTitle);

        buttonsStackHBox.getStyleClass().add("buttons-stack-h-box");

        uploadButton.getStyleClass().add("option-button");
        Styles.setButtonBackground(uploadButton, Colors.text, Colors.highlight, 20);
        Styles.setButtonTooltip(uploadButton, Strings.uploadButtonTooltip);
        bindImageToButton(Images.uploadIcon, uploadButton);

        libraryButton.getStyleClass().add("option-button");
        Styles.setButtonBackground(libraryButton, Colors.text, Colors.highlight, 20);
        Styles.setButtonTooltip(libraryButton, Strings.libraryButtonTooltip);
        bindImageToButton(Images.musicLibraryIcon, libraryButton);

        settingsButton.getStyleClass().add("option-button");
        Styles.setButtonBackground(settingsButton, Colors.text, Colors.highlight, 20);
        Styles.setButtonTooltip(settingsButton, Strings.settingsButtonTooltip);
        bindImageToButton(Images.settingsIcon, settingsButton);
    }

    @Override
    protected void setupActions() {
        uploadButton.setOnAction(e -> uploadButtonListener.onButtonClicked());
        libraryButton.setOnAction(e -> libraryButtonListener.onButtonClicked());
        settingsButton.setOnAction(e -> settingsButtonListener.onButtonClicked());
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
