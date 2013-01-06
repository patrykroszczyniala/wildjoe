package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.model.StaticModel;
import pl.pepuch.wildjoe.view.StaticView;

public abstract class StaticActor {

    protected StaticView view;
    protected StaticModel model;
    private boolean isVisible;

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        view().layer().setVisible(isVisible);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void destroy() {
        view().destroy();
        model().destroy();
    }

    public void paint(float alpha) {
        view().paint(alpha);
    }

    public void update(float delta) {
        view().update(delta);
    }

    protected abstract StaticView view();

    protected abstract StaticModel model();
}
