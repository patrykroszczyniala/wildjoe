package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.FinishFlagModel;
import pl.pepuch.wildjoe.view.FinishFlagView;

public class FinishFlag extends DynamicActor {

    public FinishFlag(GameWorld world, Vec2 position) {
        model = new FinishFlagModel(world, position);
        view = new FinishFlagView(model());
        model().setPosition(position);
        model().body().setUserData(this);
    }

    public void paint(float alpha) {
        view().paint(alpha);
    }

    public void update(float delta) {
        view().update(delta);
    }

    @Override
    public FinishFlagModel model() {
        return (FinishFlagModel) model;
    }

    @Override
    public FinishFlagView view() {
        return (FinishFlagView) view;
    }
}
