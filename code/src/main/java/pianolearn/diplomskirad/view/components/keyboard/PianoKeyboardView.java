package pianolearn.diplomskirad.view.components.keyboard;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.model.KeyboardModel;
import pianolearn.diplomskirad.model.score.KeyIterator;
import pianolearn.diplomskirad.model.score.NoteAlphabet;
import pianolearn.diplomskirad.model.score.Pitch;
import pianolearn.diplomskirad.view.BaseView;

import java.util.HashMap;
import java.util.Iterator;

import static pianolearn.diplomskirad.constants.Config.*;

public class PianoKeyboardView extends BaseView {

    private final StackPane rootPane = new StackPane();
    private final Pane whiteKeysHBox = new Pane();
    private final Pane blackKeysHBox = new Pane();
    private final PianoKeyView[] keys;

    private final HashMap<String, PianoKeyView> keysMap = new HashMap<>();
    private final KeyboardModel model;
    private final Scene scene = NavigationController.INSTANCE.getStage().getScene();

    public PianoKeyboardView(KeyboardModel model) {
        this.model = model;
        keys = new PianoKeyView[model.getNumberOfWhiteKeys() * 2 - 1];
        setupGUI();
    }

    @Override
    protected void addViews() {
        Iterator<Pitch> keysIterator = new KeyIterator(model.getFirstPitch(), model.getLastPitch());
        boolean isCurrentWhite = true;
        NoteAlphabet lastWhiteKey = null;
        int i = 0;

        while (keysIterator.hasNext()) {
            if (isCurrentWhite) {
                PianoKeyView key = PianoKeyView.whiteKey();
                whiteKeysHBox.getChildren().add(key);
                keys[i] = key;

                Pitch pitch = keysIterator.next();
                lastWhiteKey = pitch.key();
                keysMap.put(pitch.toString(), key);

                if (pitch.key() == NoteAlphabet.C) {
                    key.addLabel(pitch.toString());
                }
            } else {
                PianoKeyView key = PianoKeyView.blackKey();
                blackKeysHBox.getChildren().add(key);
                keys[i] = key;

                if (lastWhiteKey == NoteAlphabet.E || lastWhiteKey == NoteAlphabet.B) {
                    key.setVisible(false);
                } else {
                    String keyCode = keysIterator.next().toString();
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
        changeSize();
        scene.widthProperty().addListener(e -> changeSize());
        scene.heightProperty().addListener(e -> changeSize());
    }

    private void changeSize() {
        double sceneWidth = scene.getWidth();
        int numberOfKeys = model.getNumberOfWhiteKeys();
        double whiteKeyWidth = Math.floor(sceneWidth / numberOfKeys);
        double whiteKeyHeight = whiteKeyWidth * WHITE_KEY_HEIGHT_MULTIPLIER;
        double remainingWidth = sceneWidth - (whiteKeyWidth * numberOfKeys);
        double blackKeyWidth = whiteKeyWidth * BLACK_KEY_WIDTH_MULTIPLIER;
        double blackKeyHeight = Math.ceil(blackKeyWidth * BLACK_KEY_HEIGHT_MULTIPLIER);

        rootPane.setMinHeight(whiteKeyHeight);
        rootPane.setMaxHeight(whiteKeyHeight);

        double whiteKeyX = 0;
        for (PianoKeyView key : keys) {
            if (key.isWhite()) {
                double addOn = remainingWidth > 0 ? 1 : 0;
                remainingWidth -= addOn;
                double width = whiteKeyWidth + addOn;

                key.setWidth(width);
                key.setHeight(whiteKeyHeight);

                key.setLayoutX(whiteKeyX + width/2);
                key.setLayoutY(whiteKeyHeight);

                whiteKeyX += width;
            } else {
                key.setWidth(blackKeyWidth);
                key.setHeight(blackKeyHeight);

                key.setLayoutX(whiteKeyX);
                key.setLayoutY(blackKeyHeight);
            }
        }
    }

    public void setHighlight(String keyCode, Color color) {
        keysMap.get(keyCode).setHighlight(color);
    }

    public void removeHighlight(String keyCode) {
        keysMap.get(keyCode).removeHighlight();
    }
}
