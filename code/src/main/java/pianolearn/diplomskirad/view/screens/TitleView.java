package pianolearn.diplomskirad.view.screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import pianolearn.diplomskirad.constants.Images;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.Styles;
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
        super();
        setupGUI();
    }

    @Override
    protected void addViews() {
        buttonsStackHBox.getChildren().addAll(uploadButton, libraryButton, settingsButton);
        rootPane.getChildren().addAll(titleLabel, buttonsStackHBox);
    }

    @Override
    protected void styleViews() {
        rootPane.getStylesheets().add(Styles.TITLE_VIEW_STYLE);
        rootPane.getStyleClass().add("background");

        titleLabel.getStyleClass().addAll("title", "font-title");
        titleLabel.setText(Strings.appTitle);

        buttonsStackHBox.getStyleClass().add("buttons-stack-h-box");

        uploadButton.getStyleClass().add("option-button");
        bindImageToButton(Images.uploadIcon, uploadButton);
        Tooltip.install(uploadButton, new Tooltip(Strings.uploadButtonTooltip));

        libraryButton.getStyleClass().add("option-button");
        bindImageToButton(Images.musicLibraryIcon, libraryButton);
        Tooltip.install(libraryButton, new Tooltip(Strings.libraryButtonTooltip));

        settingsButton.getStyleClass().add("option-button");
        bindImageToButton(Images.settingsIcon, settingsButton);
        Tooltip.install(settingsButton, new Tooltip(Strings.settingsButtonTooltip));
    }

    @Override
    protected void setupActions() {
        uploadButton.setOnAction(e -> uploadButtonListener.onButtonClicked());
        libraryButton.setOnAction(e -> libraryButtonListener.onButtonClicked());
        settingsButton.setOnAction(e -> settingsButtonListener.onButtonClicked());
    }

    @Override
    public VBox getRootPane() {
        return rootPane;
    }

    public void setUploadButtonListener(ButtonClickListener uploadButtonListener) {
        this.uploadButtonListener = uploadButtonListener;
    }

    public void setLibraryButtonListener(ButtonClickListener libraryButtonListener) {
        this.libraryButtonListener = libraryButtonListener;
    }

    public void setSettingsButtonListener(ButtonClickListener settingsButtonListener) {
        this.settingsButtonListener = settingsButtonListener;
    }
}
