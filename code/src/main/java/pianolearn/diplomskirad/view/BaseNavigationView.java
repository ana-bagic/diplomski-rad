package pianolearn.diplomskirad.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Images;
import pianolearn.diplomskirad.helper.StylesHelper;
import pianolearn.diplomskirad.listener.ButtonClickListener;

public class BaseNavigationView extends BaseView {

    protected final BorderPane rootPane = new BorderPane();
    protected final StackPane topStackPane = new StackPane();
    private final Button backButton = new Button();

    private ButtonClickListener backButtonListener;

    @Override
    protected void addViews() {
        topStackPane.getChildren().add(backButton);
        rootPane.setTop(topStackPane);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setBackground(StylesHelper.background(Colors.background, null));

        topStackPane.setPadding(new Insets(50));

        StylesHelper.setButtonSize(backButton, 80);
        StylesHelper.setButtonBackground(backButton, Colors.background, null, 0);
        bindImageToButton(Images.backArrowIcon, backButton);
        StackPane.setAlignment(backButton, Pos.CENTER_LEFT);
    }

    @Override
    protected void setupActions() {
        backButton.setOnAction(e -> backButtonListener.onButtonClicked());
    }

    public void setBackButtonListener(ButtonClickListener listener) {
        backButtonListener = listener;
    }
}
