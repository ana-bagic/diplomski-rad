package pianolearn.diplomskirad.view.components.keyboard;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pianolearn.diplomskirad.controller.NavigationController;
import pianolearn.diplomskirad.helper.ScaleHelper;
import pianolearn.diplomskirad.model.KeyboardModel;
import pianolearn.diplomskirad.model.score.NoteAlphabet;
import pianolearn.diplomskirad.model.score.PitchModel;

import java.util.HashMap;

import static pianolearn.diplomskirad.constants.Config.*;

public class PianoKeyboardView extends StackPane {

    private final Pane whiteKeysHBox = new Pane();
    private final Pane blackKeysHBox = new Pane();
    private final PianoKeyView[] keys;

    private final HashMap<String, PianoKeyView> keysMap = new HashMap<>();
    private final KeyboardModel model;
    private final Scene scene = NavigationController.INSTANCE.getStage().getScene();

    public PianoKeyboardView(KeyboardModel model) {
        this.model = model;
        keys = new PianoKeyView[model.getNumberOfWhiteKeys() * 2 - 1];

        setupView();
    }

    private void setupView() {
        scene.widthProperty().addListener(e -> changeSize());
        scene.heightProperty().addListener(e -> changeSize());

        getChildren().addAll(whiteKeysHBox, blackKeysHBox);

        PitchModel pitch = model.getFirstPitch();
        boolean isCurrentWhite = true;
        NoteAlphabet lastWhiteKey = null;
        int i = 0;

        while (pitch.compareTo(model.getLastPitch()) <= 0) {
            if (isCurrentWhite) {
                PianoKeyView key = PianoKeyView.whiteKey();
                whiteKeysHBox.getChildren().add(key);
                keys[i] = key;

                lastWhiteKey = pitch.key();
                keysMap.put(pitch.toString(), key);

                if (pitch.key() == NoteAlphabet.C) {
                    key.addLabel(pitch.toString());
                }

                pitch = ScaleHelper.adjustPitch(pitch.key(), pitch.octave(), 1);
            } else {
                PianoKeyView key = PianoKeyView.blackKey();
                blackKeysHBox.getChildren().add(key);
                keys[i] = key;

                if (lastWhiteKey == NoteAlphabet.E || lastWhiteKey == NoteAlphabet.B) {
                    key.setVisible(false);
                } else {
                    keysMap.put(pitch.toString(), key);
                    pitch = ScaleHelper.adjustPitch(pitch.key(), pitch.octave(), 1);
                }
            }

            isCurrentWhite = !isCurrentWhite;
            i++;
        }

        changeSize();
    }

    private void changeSize() {
        double sceneWidth = scene.getWidth();
        int numberOfKeys = model.getNumberOfWhiteKeys();
        double whiteKeyWidth = Math.floor(sceneWidth / numberOfKeys);
        double whiteKeyHeight = whiteKeyWidth * WHITE_KEY_HEIGHT_MULTIPLIER;
        double remainingWidth = sceneWidth - (whiteKeyWidth * numberOfKeys);
        double blackKeyWidth = whiteKeyWidth * BLACK_KEY_WIDTH_MULTIPLIER;
        double blackKeyHeight = Math.ceil(blackKeyWidth * BLACK_KEY_HEIGHT_MULTIPLIER);

        setMinHeight(whiteKeyHeight);
        setMaxHeight(whiteKeyHeight);

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
