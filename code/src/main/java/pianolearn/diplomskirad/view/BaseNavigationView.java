package pianolearn.diplomskirad.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Images;
import pianolearn.diplomskirad.constants.Styles;
import pianolearn.diplomskirad.listener.ButtonClickListener;

public class BaseNavigationView extends BaseView {

    protected final BorderPane rootPane = new BorderPane();
    protected final HBox topHBox = new HBox();
    private final Button backButton = new Button();

    private ButtonClickListener backButtonListener;

    @Override
    protected void addViews() {
        topHBox.getChildren().addAll(backButton);
        rootPane.setTop(topHBox);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setBackground(Styles.background(Colors.background, null));

        topHBox.setAlignment(Pos.CENTER_LEFT);
        topHBox.setPadding(new Insets(30, 0, 0, 50));

        Styles.setButtonSize(backButton, 60);
        Styles.setButtonBackground(backButton, Colors.background, null, 0);
        bindImageToButton(Images.backArrowIcon, backButton);
    }

    @Override
    protected void setupActions() {
        backButton.setOnAction(e -> backButtonListener.onButtonClicked());
    }

    public void setBackButtonListener(ButtonClickListener listener) {
        backButtonListener = listener;
    }
}
