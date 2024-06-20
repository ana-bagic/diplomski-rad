package pianolearn.diplomskirad.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.view.BaseNavigationView;

public class SettingsView extends BaseNavigationView {

    private final VBox centerVBox = new VBox();
    private final Label settingsLabel = new Label();

    public SettingsView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
        centerVBox.getChildren().add(settingsLabel);
        rootPane.setCenter(centerVBox);
    }

    @Override
    protected void styleViews() {
        super.styleViews();

        centerVBox.setAlignment(Pos.TOP_CENTER);
        centerVBox.setPadding(new Insets(50));

        settingsLabel.setFont(Fonts.header);
        settingsLabel.setTextFill(Colors.text);
        settingsLabel.setText(Strings.settingsLabel);
    }
}
