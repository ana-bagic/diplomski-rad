package pianolearn.diplomskirad.helper;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import pianolearn.diplomskirad.constants.Colors;
import pianolearn.diplomskirad.constants.Fonts;
import pianolearn.diplomskirad.model.PlaybackSpeed;

public class StylesHelper {

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

    public static void setButtonSize(Button button, double size) {
        button.setMinSize(size, size);
        button.setMaxSize(size, size);
    }

    public static void setImageViewSizeAndRadius(ImageView imageView, double size, double radius) {
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);

        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(radius);
        clip.setArcHeight(radius);

        imageView.setClip(clip);
    }

    public static void setupLabelSlider(Slider slider, PlaybackSpeed[] speeds) {
        slider.setMin(0);
        slider.setMax(speeds.length - 1);
        slider.setValue(0);

        slider.setMinWidth(300);

        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);

        slider.setLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Double aDouble) {
                int index = (int) Math.round(aDouble);
                return speeds[index].getLabel();
            }

            @Override
            public Double fromString(String s) {
                return 0.0;
            }
        });
    }

    private static String getHex(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);

        return String.format("#%02X%02X%02X", r, g, b);
    }
}
