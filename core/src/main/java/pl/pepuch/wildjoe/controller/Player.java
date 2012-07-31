package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.PlayerModel;
import pl.pepuch.wildjoe.view.PlayerView;

public class Player extends DynamicActor {
	
	public Player(GameWorld world, Vec2 position) {
		model = new PlayerModel(world, position);
		view = new PlayerView(model());
		model().getBody().setUserData(this);
	}
	
	public void paint(float alpha) {
		view().paint(alpha);
	}
	
	public void update(float delta) {
		view().update(delta);

		if (isMovingRight()) {
			view().setAnimationRightVisible(true);
		}
		else if (isMovingLeft()) {
			view().setAnimationLeftVisible(true);
		}
		else {
			view().setAnimationIdleVisible(true);
		}
	}

	public void die() {
		destroy();
	}

	public void shoot() {
		Cartridge cartridge = new Cartridge(model().getGameWorld(), model().getBody().getWorldCenter());
		float impulse = cartridge.model().getBody().getMass() * 20;
		if (isMovingLeft()) {
			cartridge.model().getBody().applyLinearImpulse(new Vec2(impulse/-1, 0), model().getBody().getWorldCenter());
		}
		if (isMovingRight()) {
			cartridge.model().getBody().applyLinearImpulse(new Vec2(impulse, 0), model().getBody().getWorldCenter());
		}
		model().getGameWorld().add((DynamicActor)cartridge);
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
