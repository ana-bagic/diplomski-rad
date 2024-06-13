package pianolearn.diplomskirad.view.base;

import javafx.scene.layout.Pane;

public abstract class BaseView {

    protected final Pane root;

    public BaseView(Pane root) {
        this.root = root;

        addViews();
        styleViews();
        setupConstraints();
        setupActions();
    }

    protected abstract void addViews();

    protected void styleViews() {
    }

    protected abstract void setupConstraints();

    protected void setupActions() {
    }

    public Pane getRoot() {
        return root;
    }
}
