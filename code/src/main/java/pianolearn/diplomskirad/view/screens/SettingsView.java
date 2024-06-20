package pianolearn.diplomskirad.view.screens;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.Styles;
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
        getStylesheets().add(Styles.SETTINGS_VIEW_STYLE);

        centerVBox.getStyleClass().add("center-v-box");

        settingsLabel.getStyleClass().addAll("header");
        settingsLabel.setFont(Fonts.header);
        settingsLabel.setText(Strings.settingsLabel);
    }
}
