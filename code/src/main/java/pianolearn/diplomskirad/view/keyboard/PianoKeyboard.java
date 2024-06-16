package pianolearn.diplomskirad.view.keyboard;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pianolearn.diplomskirad.view.BaseView;

public class PianoKeyboard extends BaseView {

    private final StackPane root = new StackPane();

    private static final int WHITE_KEY_WIDTH = 10;
    private static final int WHITE_KEY_HEIGHT = 50;
    private static final int BLACK_KEY_WIDTH = 6;
    private static final int BLACK_KEY_HEIGHT = 35;

    public PianoKeyboard(int numberOfKeys) {
        super();
        drawKeyboard(numberOfKeys);
    }

    private void drawKeyboard(int numberOfKeys) {
        root.getChildren().clear();

        Label label = new Label("kkfkjfhjkdshfjkd");
        root.getChildren().add(label);

        // Draw white keys
        for (int i = 0; i < numberOfKeys; i++) {
            PianoKey whiteKey = new PianoKey(WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT, i);
            whiteKey.setFill(Color.WHITE);
            whiteKey.setStroke(Color.BLACK);
            whiteKey.setX(i * WHITE_KEY_WIDTH);
            root.getChildren().add(whiteKey);
        }

        // Draw black keys
        for (int i = 0; i < numberOfKeys; i++) {
            if (i % 7 != 2 && i % 7 != 6) { // Skip where there are no black keys
                PianoKey blackKey = new PianoKey(BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT, i);
                blackKey.setFill(Color.BLACK);
                blackKey.setX(i * WHITE_KEY_WIDTH + (WHITE_KEY_WIDTH - BLACK_KEY_WIDTH / 2));
                root.getChildren().add(blackKey);
            }
        }
    }

    @Override
    protected void addViews() {

    }

    @Override
    protected void setupConstraints() {

    }

    @Override
    public Pane getRoot() {
        return root;
    }
}
