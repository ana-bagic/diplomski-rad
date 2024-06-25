package pianolearn.diplomskirad.view.components.keyboard;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.model.KeyboardModel;
import pianolearn.diplomskirad.model.note.KeyIterator;
import pianolearn.diplomskirad.view.BaseView;

import java.util.HashMap;
import java.util.Iterator;

public class PianoKeyboardView extends BaseView {

    public static final double STROKE_WIDTH = 1;

    private final StackPane rootPane = new StackPane();
    private final HBox whiteKeysHBox = new HBox();
    private final Pane blackKeysHBox = new Pane();
    private final PianoKey[] keys;

    private final HashMap<String, PianoKey> keysMap = new HashMap<>();
    private final KeyboardModel model;
    private final Scene scene = NavigationController.INSTANCE.getStage().getScene();

    public PianoKeyboardView(KeyboardModel model) {
        this.model = model;
        keys = new PianoKey[model.getNumberOfWhiteKeys() * 2 - 1];
        setupGUI();
    }

    @Override
    protected void addViews() {
        Iterator<String> keysIterator = new KeyIterator(model.getFirstPitch(), model.getLastPitch());
        boolean isCurrentWhite = true;
        int i = 0;

        while (keysIterator.hasNext()) {
            if (isCurrentWhite) {
                PianoKey key = PianoKey.whiteKey();
                whiteKeysHBox.getChildren().add(key);
                keys[i] = key;

                String keyCode = keysIterator.next();
                keysMap.put(keyCode, key);
            } else {
                PianoKey key = PianoKey.blackKey();
                blackKeysHBox.getChildren().add(key);
                keys[i] = key;

                if (i % 14 == model.getIndexSkip1() || i % 14 == model.getIndexSkip2()) {
                    key.setVisible(false);
                } else {
                    String keyCode = keysIterator.next();
                    keysMap.put(keyCode, key);
                }
            }

            isCurrentWhite = !isCurrentWhite;
            i++;
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
    }

    private void changeSize() {
        double sceneWidth = scene.getWidth();
        int numberOfKeys = model.getNumberOfWhiteKeys();
        double whiteKeyWidth = Math.floor((sceneWidth - STROKE_WIDTH * numberOfKeys) / numberOfKeys);
        double whiteKeyHeight = whiteKeyWidth * 5;
        double remainingWidth = sceneWidth - (whiteKeyWidth * numberOfKeys + STROKE_WIDTH * numberOfKeys);
        double blackKeyWidth = Math.floor(whiteKeyWidth * 0.7);
        double blackKeyHeight = blackKeyWidth * 5;

        rootPane.setMaxHeight(whiteKeyHeight);

        double prevWhiteKeyX = 0;
        for (PianoKey key : keys) {
            if (key.isWhite()) {
                double addOn = remainingWidth > 0 ? 1 : 0;
                remainingWidth -= addOn;
                double width = whiteKeyWidth + addOn;
                prevWhiteKeyX += width + STROKE_WIDTH;

                key.setWidth(width);
                key.setHeight(whiteKeyHeight);
            } else {
                key.setWidth(blackKeyWidth);
                key.setHeight(blackKeyHeight);

                double blackKeyX = prevWhiteKeyX - (blackKeyWidth / 2);
                key.setX(blackKeyX);
            }
        }
    }
}
