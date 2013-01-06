package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.MummyModel;
import pl.pepuch.wildjoe.view.MummyView;
import playn.core.PlayN;

public class Mummy extends DynamicActor {

    // block on which mummy is moving; updated by WirldContactListener
    private Block block;

    public Mummy(GameWorld world, Vec2 position) {
        model = new MummyModel(world, position);
        view = new MummyView(model());
        go();
        turnLeft();
        model().body().setUserData(this);
    }

    public void paint(float alpha) {
        view().paint(alpha);
    }

    public void update(float delta) {
        view().update(delta);

        if ((block != null && model().isTurnedRight() && model().position().x >= (block.model().position().x + block.model().width() - 1f))) {
            turnLeft();
        }
        if ((block != null && model().isTurnedLeft() && model().position().x <= block.model().position().x)) {
            turnRight();
        }

        if (isBlocked()) {
	    PlayN.log().debug("TRUE");
            if (model().isTurnedRight()) {
                turnLeft();
            } else if (model().isTurnedLeft()) {
                turnRight();
            }
        }
	
        super.update(delta);
    }

    @Override
    public MummyModel model() {
        return (MummyModel) model;
    }

    @Override
    public MummyView view() {
        return (MummyView) view;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Block block() {
        return block;
    }

    @Override
    public void turnLeft() {
        view().turnLeft();
        super.turnLeft();
    }

    @Override
    public void turnRight() {
        view().turnRight();
        super.turnRight();
    }
}
