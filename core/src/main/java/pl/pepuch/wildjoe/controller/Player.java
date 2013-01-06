package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;
import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.PlayerModel;
import pl.pepuch.wildjoe.view.PlayerView;
import playn.core.Sound;

public class Player extends DynamicActor {

    Sound runningSound;
    private boolean isJumping;

    public Player(GameWorld world, Vec2 position) {
        model = new PlayerModel(world, position);
        view = new PlayerView(model());
        model().body().setUserData(this);
        turnRight();
    }

    public void paint(float alpha) {
        view().paint(alpha);
    }

    public void update(float delta) {
        view().update(delta);
        
        if (isMoving()) {
            view().showMoving();
            makeStep();
        }
        if (isJumping()) {
            view().showJumping();
        }
        if (!isMoving() && !isJumping()) {
            view().showIdle();
        }
        super.update(delta);
    }

    public void die() {
        destroy();
    }

    public void shoot() {
        Cartridge cartridge = new Cartridge(model().gameWorld(), model().position());
        float impulse = (cartridge.model().body().getMass()) / cartridge.model().speed() * 1f + Math.abs(model().positionBefore().x - model().position().x);
        if (model().isTurnedLeft()) {
            cartridge.model().setPosition(new Vec2(model().position().x - ((model().width() + cartridge.model().width()) / 2), model().position().y));
            cartridge.model().body().applyLinearImpulse(new Vec2(impulse / -1, 0), model.position());
        }
        if (model().isTurnedRight()) {
            cartridge.model().setPosition(new Vec2(model().position().x + ((model().width() + cartridge.model().width()) / 2), model().position().y));
            cartridge.model().body().applyLinearImpulse(new Vec2(impulse, 0), model.position());
        }
        model().gameWorld().add((DynamicActor) cartridge);
    }
    
        public void jump() {
        if (!isJumping()) {
            float impulse = model().body().getMass() * 140;
            model().body().applyLinearImpulse(new Vec2(model().body().getWorldCenter().x, impulse), model().body().getWorldCenter());
        }
    }
    
//    public void setIsJumping(boolean isJumping) {
//	this.isJumping = isJumping;
//    }

    public boolean isJumping() {
	if ((int)(model().body().getLinearVelocity().y)>0) {
	    return true;
	}
	return false;
//	return isJumping;
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

    @Override
    public PlayerModel model() {
        return (PlayerModel) model;
    }

    @Override
    public PlayerView view() {
        return (PlayerView) view;
    }
}
