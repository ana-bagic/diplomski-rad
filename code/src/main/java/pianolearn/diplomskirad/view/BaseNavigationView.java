package pianolearn.diplomskirad.view;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    }

    @Override
    protected void styleViews() {
        rootPane.getStylesheets().add(Styles.BASE_VIEW_STYLE);
        rootPane.getStyleClass().add("background");

        topHBox.getStyleClass().add("top-h-box");

        backButton.getStyleClass().add("back-button");
        bindImageToButton(Images.backArrowIcon, backButton);
    }

    @Override
    protected void setupActions() {
        backButton.setOnAction(e -> backButtonListener.onButtonClicked());
    }

    @Override
    public Pane getRootPane() {
        return rootPane;
    }

    public void setBackButtonListener(ButtonClickListener listener) {
        backButtonListener = listener;
    }
}
