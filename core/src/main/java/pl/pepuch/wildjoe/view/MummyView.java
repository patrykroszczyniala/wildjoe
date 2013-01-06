package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.MummyModel;
import playn.core.Image;

public class MummyView extends DynamicView {

    // public tylko tymczasowo, do testow
    public Animation animationRun;

    public MummyView(MummyModel model) {
        super(model);

        Image imageRun = AssetsFactory.getImage("images/runMummy.png");

        int size = 64;
        animationRun = new Animation(imageRun, size, size, 10, 120, true);

        animationRun.setVisible(false);

        addLayerAt(animationRun.getLayer(), 0, size / -2);
        showMoving();
    }

    public void paint(float alpha) {
        if (animationRun.isVisible()) {
            animationRun.paint(alpha);
        }
        super.paint(alpha);
    }

    public void update(float delta) {
        if (animationRun.isVisible()) {
            animationRun.update(delta);
        }
        super.update(delta);
    }

    public void turnLeft() {
        if (model().isTurnedRight()) {
            animationRun.mirrorHorizontally();
        }
    }

    public void turnRight() {
        if (model().isTurnedLeft()) {
            animationRun.mirrorHorizontally();
        }
    }

    public void showMoving() {
        animationRun.setVisible(true);
    }
}
