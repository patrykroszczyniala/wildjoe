package pl.pepuch.wildjoe.controller;

import java.math.BigDecimal;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.model.DynamicModel;
import pl.pepuch.wildjoe.view.DynamicView;
import playn.core.PlayN;

public abstract class DynamicActor {

    protected DynamicView view;
    protected DynamicModel model;
    public boolean isMoving;
    private boolean isVisible;

    public void stop() {
        isMoving = false;
        model().body().setLinearVelocity(new Vec2(0, 0));
    }

    public void go() {
        isMoving = true;
    }

    public boolean isMoving() {
        return isMoving;
    }
    
    // actor is moving but is also blocked by some element, ex. Block
    public boolean isBlocked() {
	PlayN.log().debug(String.valueOf((int)Math.abs((model().positionBefore().x-model().position().x)*100)));
//	PlayN.log().debug(String.valueOf(((model().positionBefore().x-model().position().x)*100)==0));
//        return (isMoving() && ((int)Math.abs((model().positionBefore().x-model().position().x)*100))==0);
	return false;
    }

    public void makeStep() {
        model().body().setLinearVelocity(new Vec2(model().speed(), model().body().getLinearVelocity().y));
    }

    public void turnLeft() {
        model().turnLeft();
    }

    public void turnRight() {
        model().turnRight();
    }

    public void destroy() {
        view().destroy();
        model().destroy();
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        view().layer().setVisible(isVisible);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public abstract void paint(float alpha);

    public void update(float delta) {
        model().update(delta);
    }

    public abstract DynamicModel model();

    public abstract DynamicView view();
}
