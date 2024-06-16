package pianolearn.diplomskirad.view.screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import pianolearn.diplomskirad.constants.Images;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.listener.ButtonClickListener;
import pianolearn.diplomskirad.view.BaseView;

public class SettingsView extends BaseView {

    private final BorderPane rootPane = new BorderPane();

    private final HBox topHBox = new HBox();
    private final Button backButton = new Button();
    private final Label headerLabel = new Label();

    private ButtonClickListener backButtonClicked;

    public SettingsView() {
        super();
        setupGUI();
    }

    @Override
    protected void addViews() {
        topHBox.getChildren().addAll(backButton, headerLabel);
        rootPane.setTop(topHBox);
    }

    @Override
    protected void styleViews() {
        rootPane.getStylesheets().add(Styles.SETTINGS_VIEW_STYLE);
        rootPane.getStyleClass().add("background");

        topHBox.getStyleClass().add("top-h-box");

        backButton.getStyleClass().add("button");
        bindImageToButton(Images.backArrowIcon, backButton);

        headerLabel.getStyleClass().addAll("header", "font-header");
        headerLabel.setText(Strings.appTitle);
    }

    @Override
    protected void setupActions() {
        backButton.setOnAction(e -> backButtonClicked.onButtonClicked());
    }

    @Override
    public Pane getRootPane() {
        return rootPane;
    }

    public void setBackButtonClicked(ButtonClickListener backButtonClicked) {
        this.backButtonClicked = backButtonClicked;
    }
}
