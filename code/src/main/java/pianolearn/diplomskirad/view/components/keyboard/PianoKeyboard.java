package pianolearn.diplomskirad.view.components.keyboard;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.view.BaseView;

public class PianoKeyboard extends BaseView {

    private final Pane root = new Pane();

    private final double WHITE_KEY_WIDTH;
    private final double WHITE_KEY_HEIGHT = 50;
    private final double BLACK_KEY_WIDTH;
    private final double BLACK_KEY_HEIGHT = 35;

    public PianoKeyboard(int numberOfKeys) {
        super();
        setPrefHeight(200);
        double frameWidth = NavigationController.INSTANCE.getStage().getWidth();
        setPrefWidth(frameWidth);
        WHITE_KEY_WIDTH = frameWidth / numberOfKeys;
        BLACK_KEY_WIDTH = WHITE_KEY_WIDTH * 0.6;
        drawKeyboard(numberOfKeys);
    }

    private void drawKeyboard(int numberOfKeys) {
        bindToSelf(root);
        root.getChildren().clear();

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
}
