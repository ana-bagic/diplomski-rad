package pianolearn.diplomskirad.view.screens;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Config;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.constants.Strings;
import pianolearn.diplomskirad.listener.EventListener;
import pianolearn.diplomskirad.listener.MidiDeviceChangeListener;
import pianolearn.diplomskirad.model.Hand;
import pianolearn.diplomskirad.model.KeyboardModel;
import pianolearn.diplomskirad.view.BaseNavigationView;
import pianolearn.diplomskirad.view.components.keyboard.PianoKeyboardView;

import javax.sound.midi.MidiDevice;

import java.util.Objects;

import static pianolearn.diplomskirad.helper.StyleHelper.setTextButton;

public class SetupKeyboardView extends BaseNavigationView {

    private final VBox centerVBox = new VBox();
    private final Label selectKeyboardLabel = new Label();
    private final HBox midiDeviceHBox = new HBox();
    private final ComboBox<MidiDevice.Info> midiDeviceComboBox = new ComboBox<>();
    private final Button refreshButton = new Button();
    private final TextFlow playKeysTextFlow = new TextFlow();
    private final PianoKeyboardView pianoKeyboardView;
    private final Button confirmButton = new Button();

    private MidiDeviceChangeListener midiDeviceChangeListener;
    private EventListener refreshButtonListener;
    private EventListener confirmButtonListener;

    public SetupKeyboardView() {
        pianoKeyboardView = new PianoKeyboardView(new KeyboardModel(Config.LOWEST_PITCH, Config.HIGHEST_PITCH));

        setupView();
    }

    private void setupView() {
        setCenter(centerVBox);

        centerVBox.getChildren().addAll(selectKeyboardLabel, midiDeviceHBox, playKeysTextFlow, pianoKeyboardView, confirmButton);
        centerVBox.setAlignment(Pos.TOP_CENTER);
        centerVBox.setPadding(new Insets(0, 40, 0, 40));
        centerVBox.setSpacing(40);
        VBox.setMargin(playKeysTextFlow, new Insets(40, 0, 0, 0));

        selectKeyboardLabel.setFont(Fonts.header);
        selectKeyboardLabel.setTextFill(Colors.text);
        selectKeyboardLabel.setText(Strings.selectKeyboard);

        midiDeviceHBox.getChildren().addAll(midiDeviceComboBox, refreshButton);
        midiDeviceHBox.setSpacing(30);
        midiDeviceHBox.setAlignment(Pos.CENTER);

        midiDeviceComboBox.setPrefWidth(250);
        midiDeviceComboBox.setPrefHeight(40);
        midiDeviceComboBox.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        midiDeviceComboBox.valueProperty().addListener((o, oldV, newV) -> midiDeviceChangeListener.onAction(newV));

        setTextButton(refreshButton, Strings.refresh);
        refreshButton.setOnAction(e -> refreshButtonListener.onAction());

        playKeysTextFlow.getChildren().addAll(
                makeText(Strings.playThe, Colors.text),
                makeText(Strings.lowest, Hand.LEFT.getKeyColorFaded()),
                makeText(Strings.andThe, Colors.text),
                makeText(Strings.highest, Hand.RIGHT.getKeyColorFaded()),
                makeText(Strings.noteOnYourKeyboard, Colors.text)
        );
        playKeysTextFlow.setTextAlignment(TextAlignment.CENTER);

        pianoKeyboardView.setInsets(80);

        setTextButton(confirmButton, Strings.confirm);
        confirmButton.setOnAction(e -> confirmButtonListener.onAction());
    }

    private Text makeText(String content, Color color) {
        Text text = new Text(content);
        text.setFont(Fonts.header);
        text.setFill(color);
        return text;
    }

    public void setMidiDeviceItems(ObservableList<MidiDevice.Info> items, MidiDevice.Info selectedItem) {
        midiDeviceComboBox.setItems(items);
        midiDeviceComboBox.setValue(selectedItem);
    }

    public void setHighlight(String keyCode, Hand hand) {
        pianoKeyboardView.setHighlight(keyCode, hand);
    }

    public void removeHighlight(String keyCode) {
        pianoKeyboardView.removeHighlight(keyCode);
    }

    public void setMidiDeviceChangeListener(MidiDeviceChangeListener listener) {
        midiDeviceChangeListener = listener;
    }

    public void setRefreshButtonListener(EventListener listener) {
        refreshButtonListener = listener;
    }

    public void setConfirmButtonListener(EventListener listener) {
        confirmButtonListener = listener;
    }
}
