package pianolearn.diplomskirad.view.screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private ButtonClickListener uploadButtonClicked;
    private ButtonClickListener libraryButtonClicked;
    private ButtonClickListener settingsButtonClicked;

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

        uploadButton.getStyleClass().add("button");
        bindImageToButton(Images.uploadIcon, uploadButton);

        libraryButton.getStyleClass().add("button");
        bindImageToButton(Images.musicLibraryIcon, libraryButton);

        settingsButton.getStyleClass().add("button");
        bindImageToButton(Images.settingsIcon, settingsButton);
    }

    @Override
    protected void setupActions() {
        uploadButton.setOnAction(e -> uploadButtonClicked.onButtonClicked());
        libraryButton.setOnAction(e -> libraryButtonClicked.onButtonClicked());
        settingsButton.setOnAction(e -> settingsButtonClicked.onButtonClicked());
    }

    @Override
    public VBox getRootPane() {
        return rootPane;
    }

    public void setUploadButtonClicked(ButtonClickListener uploadButtonClicked) {
        this.uploadButtonClicked = uploadButtonClicked;
    }

    public void setLibraryButtonClicked(ButtonClickListener libraryButtonClicked) {
        this.libraryButtonClicked = libraryButtonClicked;
    }

    public void setSettingsButtonClicked(ButtonClickListener settingsButtonClicked) {
        this.settingsButtonClicked = settingsButtonClicked;
    }
}
