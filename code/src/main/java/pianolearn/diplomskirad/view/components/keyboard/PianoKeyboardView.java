package pianolearn.diplomskirad.view.components.keyboard;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.view.BaseView;

public class PianoKeyboardView extends BaseView {

    private final int NUMBER_OF_KEYS = 52;
    private final double STROKE_WIDTH = 1;

    private final StackPane rootPane = new StackPane();
    private final HBox whiteKeysHBox = new HBox();
    private final PianoKey[] whiteKeys = new PianoKey[NUMBER_OF_KEYS];
    private final Pane blackKeysHBox = new Pane();
    private final PianoKey[] blackKeys = new PianoKey[NUMBER_OF_KEYS - 1];

    private final Scene scene = NavigationController.INSTANCE.getStage().getScene();

    public PianoKeyboardView() {
        setupGUI();
    }

    @Override
    protected void addViews() {
        for (int i = 0; i < whiteKeys.length; i++) {
            PianoKey key = new PianoKey(i);
            whiteKeys[i] = key;
            whiteKeysHBox.getChildren().add(key);
        }

        for (int i = 0; i < blackKeys.length; i++) {
            PianoKey key = new PianoKey(i);
            blackKeys[i] = key;
            blackKeysHBox.getChildren().add(key);
        }

        rootPane.getChildren().addAll(whiteKeysHBox, blackKeysHBox);
        bindToSelf(rootPane);
    }

    @Override
    protected void styleViews() {
        rootPane.setAlignment(Pos.TOP_CENTER);

        changeSize();
        scene.widthProperty().addListener(e -> changeSize());
        scene.heightProperty().addListener(e -> changeSize());

        int j = 0;
        for (PianoKey key: whiteKeys) {
            key.setFill((j == 0 || j == 51) ? Colors.accent : Colors.whiteKey);
            key.setStroke(Colors.blackKey);
            key.setStrokeWidth(STROKE_WIDTH);
            j++;
        }

        for (int i = 0; i < blackKeys.length; i++) {
            PianoKey key = blackKeys[i];
            key.setFill(Colors.blackKey);
            if (i % 7 == 2 || i % 7 == 6) {
                key.setVisible(false);
            }
        }
    }

    private void changeSize() {
        double sceneWidth = scene.getWidth();
        double whiteKeyWidth = Math.floor((sceneWidth - STROKE_WIDTH * NUMBER_OF_KEYS) / NUMBER_OF_KEYS);
        double whiteKeyHeight = whiteKeyWidth * 5;
        double remainingWidth = sceneWidth - (whiteKeyWidth * NUMBER_OF_KEYS + STROKE_WIDTH * NUMBER_OF_KEYS);
        double blackKeyWidth = Math.floor(whiteKeyWidth * 0.7);
        double blackKeyHeight = blackKeyWidth * 5;

        rootPane.setMaxHeight(whiteKeyHeight);

        for (int i = 0; i < NUMBER_OF_KEYS; i++) {
            PianoKey key = whiteKeys[i];
            double addOn = i < remainingWidth ? 1 : 0;
            key.setWidth(whiteKeyWidth + addOn);
            key.setHeight(whiteKeyHeight);
        }

        double prevWhiteKeyX = STROKE_WIDTH;
        for (int i = 0; i < blackKeys.length; i++) {
            PianoKey blackKey = blackKeys[i];
            blackKey.setWidth(blackKeyWidth);
            blackKey.setHeight(blackKeyHeight);

            double whiteKeyW = whiteKeys[i].getWidth();
            double blackKeyX = prevWhiteKeyX + whiteKeyW - (blackKeyWidth / 2);
            blackKey.setX(blackKeyX);
            prevWhiteKeyX += whiteKeyW + STROKE_WIDTH;
        }
    }
}
