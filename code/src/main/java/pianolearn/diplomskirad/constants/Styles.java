package pianolearn.diplomskirad.constants;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Styles {

    public static final String BASE_VIEW_STYLE = getStyle("base-view");

    public static final String TITLE_VIEW_STYLE = getStyle("title-view");

    public static final String SETTINGS_VIEW_STYLE = getStyle("settings-view");

    public static final String UPLOAD_VIEW_STYLE = getStyle("upload-view");

    public static final String LIBRARY_VIEW_STYLE = getStyle("library-view");

    public static final String PLAY_VIEW_STYLE = getStyle("play-view");

    private static String getStyle(String style) {
        String resource = "/styles/" + style + ".css";
        return Objects.requireNonNull(Styles.class.getResource(resource)).toExternalForm();
    }

    public static Background background(Color color, Integer radius) {
        CornerRadii backgroundRadius = radius == null ? CornerRadii.EMPTY : new CornerRadii(radius);
        return new Background(new BackgroundFill(color, backgroundRadius, Insets.EMPTY));
    }

    public static void setButtonBackground(Button button, Color normalColor, Color hoverColor, int radius) {
        Background normalBackground = background(normalColor, radius);
        button.setBackground(normalBackground);

        if (hoverColor != null) {
            Background hoverBackground = background(hoverColor, radius);
            button.setOnMouseEntered(e -> button.setBackground(hoverBackground));
            button.setOnMouseExited(e -> button.setBackground(normalBackground));
        }
    }

    public static void setButtonTooltip(Button button, String tooltipText) {
        String tooltipStyle = String.format(
                "-fx-background-color: %s; -fx-text-fill: %s;",
                getHex(Colors.text),
                getHex(Colors.background));

        Tooltip tooltip = new Tooltip(tooltipText);
        tooltip.setFont(Fonts.tooltip);
        tooltip.setStyle(tooltipStyle);
        Tooltip.install(button, tooltip);
    }

    private static String getHex(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);

        return String.format("#%02X%02X%02X", r, g, b);
    }
}
