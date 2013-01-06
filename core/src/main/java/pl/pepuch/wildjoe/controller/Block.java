package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.BlockModel;
import pl.pepuch.wildjoe.view.BlockView;

public class Block extends DynamicActor {

    public Block(GameWorld world, Vec2 position, float width, float height) {
        model = new BlockModel(world, position, width, height);
        view = new BlockView(model());
        model().setPosition(position);
        model().body().setUserData(this);
    }

    public void paint(float alpha) {
        view().paint(alpha);
    }

    public void update(float delta) {
        view().update(delta);
        if (model().speed()>0) {
            makeStep();
        }
    }

    @Override
    public BlockModel model() {
        return (BlockModel) model;
    }

    @Override
    public BlockView view() {
        return (BlockView) view;
    }
}
