package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.PlayerModel;
import pl.pepuch.wildjoe.view.PlayerView;
import playn.core.Sound;

public class Player extends DynamicActor {
	
	Sound runningSound;
	
	public Player(GameWorld world, Vec2 position) {
		model = new PlayerModel(world, position);
		view = new PlayerView(model());
		model().body().setUserData(this);
		turnLeft();
	}
	
	public void paint(float alpha) {
		view().paint(alpha);
	}
	
	public void update(float delta) {
		view().update(delta);
		
		if (isJumping()) {
			view().showJumping();
		}
		else if (isMoving()) {
			view().showMoving();
		}
		else {
			view().showIdle();
		}
	}
	
	public void die() {
		destroy();
	}

	public void shoot() {
		Cartridge cartridge = new Cartridge(model().gameWorld(), model().position());
		float impulse = (cartridge.model().body().getMass()) / cartridge.model().speed() * 1f+Math.abs(model().positionBefore().x-model().position().x);
		if (model().isTurnedLeft()) {
			cartridge.model().setPosition(new Vec2(model().position().x-((model().width()+cartridge.model().width())/2), model().position().y));
			cartridge.model().body().applyLinearImpulse(new Vec2(impulse/-1, 0), model.position());
		}
		if (model().isTurnedRight()) {
			cartridge.model().setPosition(new Vec2(model().position().x+((model().width()+cartridge.model().width())/2), model().position().y));
			cartridge.model().body().applyLinearImpulse(new Vec2(impulse, 0), model.position());
		}
		model().gameWorld().add((DynamicActor)cartridge);
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
		return (PlayerModel)model;
	}

	@Override
	public PlayerView view() {
		return (PlayerView)view;
	}
	
}
