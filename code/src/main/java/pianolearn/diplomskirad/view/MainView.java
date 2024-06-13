package pianolearn.diplomskirad.view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import pianolearn.diplomskirad.view.base.BaseView;

import static pianolearn.diplomskirad.constants.Colors.BACKGROUND;

public class MainView extends BaseView {

    public MainView() {
        super(new StackPane());
    }

    @Override
    protected void addViews() {

    }

    @Override
    protected void styleViews() {
        root.setBackground(new Background(new BackgroundFill(BACKGROUND, null, null)));
    }

    @Override
    protected void setupConstraints() {

    }
}
