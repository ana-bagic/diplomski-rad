package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
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

        centerVBox.setAlignment(Pos.TOP_CENTER);
        centerVBox.setSpacing(80);
        centerVBox.setPadding(new Insets(150, 0, 0, 0));

        infoHBox.setAlignment(Pos.CENTER);

        infoLabel.setFont(Fonts.header);
        infoLabel.setTextFill(Colors.text);
        infoLabel.setText(Strings.loadLabel);

        chosenFileLabel.setFont(Fonts.header);
        chosenFileLabel.setTextFill(Colors.accent);
        showNode(chosenFileLabel, false);

        errorLabel.setFont(Fonts.micro);
        errorLabel.setTextFill(Colors.error);
        errorLabel.setTextAlignment(TextAlignment.CENTER);
        showNode(errorLabel, false);

        actionHBox.setAlignment(Pos.CENTER);

        confirmButton.setFont(Fonts.body);
        confirmButton.setTextFill(Colors.background);
        confirmButton.setPadding(new Insets(20));
        Styles.setButtonBackground(confirmButton, Colors.text, Colors.highlight, 20);
        confirmButton.setText(Strings.confirm);
        showNode(confirmButton, false);

        orLabel.setFont(Fonts.body);
        orLabel.setTextFill(Colors.text);
        orLabel.setPadding(new Insets(40));
        orLabel.setText(Strings.or);
        showNode(orLabel, false);

        fileChooserButton.setFont(Fonts.body);
        fileChooserButton.setTextFill(Colors.background);
        fileChooserButton.setPadding(new Insets(20));
        Styles.setButtonBackground(fileChooserButton, Colors.text, Colors.highlight, 20);
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
