package pianolearn.diplomskirad.view;

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
        getStylesheets().add(Styles.BASE_VIEW_STYLE);
        rootPane.setBackground(Styles.background(Colors.background, null));

        topHBox.getStyleClass().add("top-h-box");

        backButton.getStyleClass().add("back-button");
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
