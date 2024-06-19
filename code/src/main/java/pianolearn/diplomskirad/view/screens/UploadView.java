package pianolearn.diplomskirad.view.screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.listener.ButtonClickListener;
import pianolearn.diplomskirad.view.BaseNavigationView;

public class UploadView extends BaseNavigationView {

    private final VBox centerVBox = new VBox();
    private final HBox infoHBox = new HBox();
    private final Label infoLabel = new Label();
    private final Label chosenFileLabel = new Label();
    private final Label errorLabel = new Label();
    private final HBox actionHBox = new HBox();
    private final Button confirmButton = new Button();
    private final Label orLabel = new Label();
    private final Button fileChooserButton = new Button();

    private ButtonClickListener fileChooserButtonListener;
    private ButtonClickListener confirmButtonListener;

    public UploadView() {
        super();
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
        infoHBox.getChildren().addAll(infoLabel, chosenFileLabel);
        actionHBox.getChildren().addAll(confirmButton, orLabel, fileChooserButton);
        centerVBox.getChildren().addAll(infoHBox, errorLabel, actionHBox);
        rootPane.setCenter(centerVBox);
    }

    @Override
    protected void styleViews() {
        super.styleViews();
        getStylesheets().add(Styles.UPLOAD_VIEW_STYLE);

        centerVBox.getStyleClass().add("center-v-box");

        infoHBox.getStyleClass().add("h-box");

        infoLabel.getStyleClass().addAll("header", "font-header");
        infoLabel.setText(Strings.loadLabel);

        chosenFileLabel.getStyleClass().addAll("chosen-file-label", "font-header");
        showNode(chosenFileLabel, false);

        errorLabel.getStyleClass().addAll("error-label", "font-micro");
        showNode(errorLabel, false);

        actionHBox.getStyleClass().add("h-box");

        confirmButton.getStyleClass().addAll("text-button", "font-body");
        confirmButton.setText(Strings.confirm);
        showNode(confirmButton, false);

        orLabel.getStyleClass().addAll("or-label", "font-body");
        orLabel.setText(Strings.or);
        showNode(orLabel, false);

        fileChooserButton.getStyleClass().addAll("text-button", "font-body");
        fileChooserButton.setText(Strings.chooseFile);
    }

    @Override
    protected void setupActions() {
        super.setupActions();
        fileChooserButton.setOnAction(e -> fileChooserButtonListener.onButtonClicked());
        confirmButton.setOnAction(e -> confirmButtonListener.onButtonClicked());
    }

    public void setFileChooserButtonListener(ButtonClickListener listener) {
        fileChooserButtonListener = listener;
    }

    public void setConfirmButtonListener(ButtonClickListener listener) {
        confirmButtonListener = listener;
    }

    public void setFileChosen(String fileChosen) {
        infoLabel.setText(Strings.fileChosen);
        chosenFileLabel.setText(fileChosen);
        showNode(chosenFileLabel, true);
    }

    public void setCanConfirm(boolean canConfirm) {
        fileChooserButton.setText(Strings.loadAnotherFile);
        showNode(confirmButton, canConfirm);
        showNode(orLabel, canConfirm);
    }

    public void setError(String error) {
        errorLabel.setText(error);
        showNode(errorLabel, true);
    }

    public void clearError() {
        errorLabel.setText(Strings.empty);
        showNode(errorLabel, false);
    }
}
