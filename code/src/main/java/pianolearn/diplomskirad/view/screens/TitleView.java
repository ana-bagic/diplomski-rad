package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pianolearn.diplomskirad.constants.Images;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.listener.ButtonClickListener;
import pianolearn.diplomskirad.view.BaseView;

public class TitleView extends BaseView {

    private final VBox root = new VBox();

    private final Label titleLabel = new Label();
    private final HBox buttonsStack = new HBox();
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
        buttonsStack.getChildren().addAll(uploadButton, libraryButton, settingsButton);
        root.getChildren().addAll(titleLabel, buttonsStack);
    }

    @Override
    protected void styleViews() {
        root.getStylesheets().add(Styles.TITLE_VIEW_STYLE);
        root.getStyleClass().add("background");

        titleLabel.getStyleClass().add("title");
        titleLabel.getStyleClass().add("font-title");
        titleLabel.setText(Strings.appTitle);

        double BUTTON_SIZE = 100;
        uploadButton.getStyleClass().add("button");
        bindImageToButton(Images.uploadIcon, uploadButton, BUTTON_SIZE, BUTTON_SIZE);
        uploadButton.setOnAction(e -> uploadButtonClicked.onButtonClicked());

        libraryButton.getStyleClass().add("button");
        bindImageToButton(Images.musicLibraryIcon, libraryButton, BUTTON_SIZE, BUTTON_SIZE);
        libraryButton.setOnAction(e -> libraryButtonClicked.onButtonClicked());

        settingsButton.getStyleClass().add("button");
        bindImageToButton(Images.settingsIcon, settingsButton, BUTTON_SIZE, BUTTON_SIZE);
        settingsButton.setOnAction(e -> settingsButtonClicked.onButtonClicked());
    }

    @Override
    protected void setupConstraints() {
        root.setAlignment(Pos.CENTER);
        root.setSpacing(60);

        buttonsStack.setAlignment(Pos.CENTER);
        buttonsStack.setSpacing(50);
    }

    @Override
    public VBox getRoot() {
        return root;
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
