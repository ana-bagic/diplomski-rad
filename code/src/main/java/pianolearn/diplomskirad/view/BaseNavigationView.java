package pianolearn.diplomskirad.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Images;
import pianolearn.diplomskirad.helper.StyleHelper;
import pianolearn.diplomskirad.listener.EventListener;

import static pianolearn.diplomskirad.helper.StyleHelper.*;

public class BaseNavigationView extends BorderPane {

    protected final StackPane topStackPane = new StackPane();
    private final Button backButton = new Button();

    private EventListener backButtonListener;

    public BaseNavigationView() {
        setupView();
    }

    private void setupView() {
        setTop(topStackPane);
        setBackground(StyleHelper.background(Colors.background, null));

        topStackPane.getChildren().add(backButton);
        topStackPane.setPadding(new Insets(50, 50, 10, 50));

        setButtonSize(backButton, 80);
        setButtonBackground(backButton, Colors.background, null, 0);
        setButtonImage(backButton, Images.backArrowIcon);
        StackPane.setAlignment(backButton, Pos.CENTER_LEFT);
        backButton.setOnAction(e -> backButtonListener.onAction());
    }

    public void setBackButtonListener(EventListener listener) {
        backButtonListener = listener;
    }
}
