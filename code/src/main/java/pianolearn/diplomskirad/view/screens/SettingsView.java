package pianolearn.diplomskirad.view.screens;

import javafx.scene.control.Label;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.view.BaseNavigationView;

public class SettingsView extends BaseNavigationView {

    private final Label headerLabel = new Label();

    public SettingsView() {
        super();
        setupGUI();
    }

    @Override
    protected void addViews() {
        super.addViews();
        topHBox.getChildren().add(headerLabel);
    }

    @Override
    protected void styleViews() {
        super.styleViews();
        getStylesheets().add(Styles.SETTINGS_VIEW_STYLE);

        headerLabel.getStyleClass().addAll("header", "font-header");
        headerLabel.setText(Strings.settingsLabel);
    }
}
