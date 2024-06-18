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

    private final Label header = new Label();
    private final VBox centerVBox = new VBox();
    private final HBox fileChooserHBox = new HBox();
    private final Button fileChooserButton = new Button();
    private final Label fileChosenLabel = new Label();
    private final Button confirmButton = new Button();
    private final Label errorLabel = new Label();

    private ButtonClickListener fileChooserButtonListener;
    private ButtonClickListener confirmButtonListener;

    public UploadView() {
        super();
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
        topHBox.getChildren().add(header);
        fileChooserHBox.getChildren().addAll(fileChooserButton, fileChosenLabel);
        centerVBox.getChildren().addAll(fileChooserHBox, confirmButton, errorLabel);
        rootPane.setCenter(centerVBox);
    }

    @Override
    protected void styleViews() {
        super.styleViews();
        rootPane.getStylesheets().add(Styles.UPLOAD_VIEW_STYLE);

        header.getStyleClass().addAll("header", "font-header");
        header.setText(Strings.loadLabel);

        centerVBox.getStyleClass().add("center-v-box");

        fileChooserHBox.getStyleClass().add("file-chooser-h-box");

        fileChooserButton.getStyleClass().addAll("file-chooser-button", "font-body");
        fileChooserButton.setText(Strings.chooseFileLabel);

        fileChosenLabel.getStyleClass().addAll("file-chosen-label", "font-body");

        confirmButton.getStyleClass().addAll("confirm-button", "font-body");
        confirmButton.setText(Strings.confirm);
        confirmButton.setDisable(true);

        errorLabel.getStyleClass().addAll("error-label", "font-micro");
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
        fileChosenLabel.setText(fileChosen);
    }

    public void enableConfirm(boolean enabled) {
        confirmButton.setDisable(!enabled);
    }

    public void setError(String error) {
        errorLabel.setText(error);
    }
}
