package pianolearn.diplomskirad.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pianolearn.diplomskirad.constants.Images;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.view.base.BaseView;

public class TitleView extends BaseView<VBox> {

    private final Label titleLabel = new Label();
    private final HBox buttonsStack = new HBox();
    private final Button fromComputerButton = new Button();
    private final Button fromLibraryButton = new Button();
    private final Button settingsButton = new Button();

    public TitleView() {
        super(new VBox());
        setupGUI();
    }

    @Override
    protected void addViews() {
        buttonsStack.getChildren().addAll(fromComputerButton, fromLibraryButton, settingsButton);
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
        fromComputerButton.getStyleClass().add("button");
        bindImageToButton(Images.uploadIcon, fromComputerButton, BUTTON_SIZE, BUTTON_SIZE);

        fromLibraryButton.getStyleClass().add("button");
        bindImageToButton(Images.musicLibraryIcon, fromLibraryButton, BUTTON_SIZE, BUTTON_SIZE);

        settingsButton.getStyleClass().add("button");
        bindImageToButton(Images.settingsIcon, settingsButton, BUTTON_SIZE, BUTTON_SIZE);
    }

    @Override
    protected void setupConstraints() {
        root.setAlignment(Pos.CENTER);
        root.setSpacing(60);

        buttonsStack.setAlignment(Pos.CENTER);
        buttonsStack.setSpacing(50);
    }
}
