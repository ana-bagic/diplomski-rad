package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.listener.EventListener;
import pianolearn.diplomskirad.view.BaseNavigationView;

import static pianolearn.diplomskirad.helper.StyleHelper.*;

public class UploadView extends BaseNavigationView {

    private final VBox centerVBox = new VBox();
    private final FlowPane infoFlowPane = new FlowPane();
    private final Label infoLabel = new Label();
    private final Label chosenFileLabel = new Label();
    private final Label errorLabel = new Label();
    private final HBox actionHBox = new HBox();
    private final Button confirmButton = new Button();
    private final Label orLabel = new Label();
    private final Button fileChooserButton = new Button();

    private EventListener fileChooserButtonListener;
    private EventListener confirmButtonListener;

    public UploadView() {
        setupView();
    }

    private void setupView() {
        setCenter(centerVBox);

        centerVBox.getChildren().addAll(infoFlowPane, errorLabel, actionHBox);
        centerVBox.setAlignment(Pos.TOP_CENTER);
        centerVBox.setSpacing(80);
        centerVBox.setPadding(new Insets(150, 20, 0, 20));

        infoFlowPane.getChildren().addAll(infoLabel, chosenFileLabel);
        infoFlowPane.setAlignment(Pos.CENTER);

        infoLabel.setFont(Fonts.header);
        infoLabel.setTextFill(Colors.text);
        infoLabel.setText(Strings.loadLabel);

        chosenFileLabel.setFont(Fonts.header);
        chosenFileLabel.setTextFill(Colors.accent);
        chosenFileLabel.maxWidthProperty().bind(widthProperty().add(-40));
        showNode(chosenFileLabel, false);

        errorLabel.setFont(Fonts.error);
        errorLabel.setTextFill(Colors.error);
        errorLabel.setTextAlignment(TextAlignment.CENTER);
        showNode(errorLabel, false);

        actionHBox.getChildren().addAll(confirmButton, orLabel, fileChooserButton);
        actionHBox.setAlignment(Pos.CENTER);

        confirmButton.setFont(Fonts.body);
        confirmButton.setTextFill(Colors.background);
        setButtonBackground(confirmButton, Colors.text, Colors.highlight, 20);
        confirmButton.setText(Strings.confirm);
        confirmButton.setPadding(new Insets(20));
        showNode(confirmButton, false);
        confirmButton.setOnAction(e -> confirmButtonListener.onAction());

        orLabel.setFont(Fonts.body);
        orLabel.setTextFill(Colors.text);
        orLabel.setPadding(new Insets(40));
        orLabel.setText(Strings.or);
        showNode(orLabel, false);

        fileChooserButton.setFont(Fonts.body);
        fileChooserButton.setTextFill(Colors.background);
        setButtonBackground(fileChooserButton, Colors.text, Colors.highlight, 20);
        fileChooserButton.setText(Strings.chooseFile);
        fileChooserButton.setPadding(new Insets(20));
        fileChooserButton.setOnAction(e -> fileChooserButtonListener.onAction());
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

    public void setFileChooserButtonListener(EventListener listener) {
        fileChooserButtonListener = listener;
    }

    public void setConfirmButtonListener(EventListener listener) {
        confirmButtonListener = listener;
    }
}
