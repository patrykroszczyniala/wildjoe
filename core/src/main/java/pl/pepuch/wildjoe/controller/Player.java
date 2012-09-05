package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.PlayerModel;
import pl.pepuch.wildjoe.view.PlayerView;

public class Player extends DynamicActor {
	
	public Player(GameWorld world, Vec2 position) {
		model = new PlayerModel(world, position);
		view = new PlayerView(model());
		model().body().setUserData(this);
	}
	
	public void paint(float alpha) {
		view().paint(alpha);
	}
	
	public void update(float delta) {
		view().update(delta);
		
		if (isVisible()) {
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
	}

	public void die() {
		destroy();
	}

	public void shoot() {
		Cartridge cartridge = new Cartridge(model().gameWorld(), model().body().getWorldCenter());
		float impulse = cartridge.model().body().getMass() * 20;
		if (isMovingLeft()) {
			cartridge.model().body().applyLinearImpulse(new Vec2(impulse/-1, 0), model().body().getWorldCenter());
		}
		if (isMovingRight()) {
			cartridge.model().body().applyLinearImpulse(new Vec2(impulse, 0), model().body().getWorldCenter());
		}
		model().gameWorld().add((DynamicActor)cartridge);
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
