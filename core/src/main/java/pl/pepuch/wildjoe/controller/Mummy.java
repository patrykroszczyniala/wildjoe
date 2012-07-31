package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.MummyModel;
import pl.pepuch.wildjoe.view.MummyView;

public class Mummy extends DynamicActor {

	public Mummy(GameWorld world, Vec2 position) {
		model = new MummyModel(world, position);
		view = new MummyView(model());
		model().getBody().setUserData(this);
		isMovingRight(true);
	}
	
	public void paint(float alpha) {
		view().paint(alpha);
		model().drawDebugData();
	}
	
	public void update(float delta) {
		view().update(delta);
		float diff = model().getPosition().x-model().getOrigin().x;
		
		if (diff<=-3 || (diff>=0.0f && diff<3.0f && isMovingRight())) {
			isMovingRight(true);
		}
		if (diff>=3.0f || (diff<0.0f && diff>-3 && isMovingLeft())) {
			isMovingLeft(true);
		}	
		
		if (isMovingRight()) {
			view().setAnimationRightVisible(true);
			moveRight();

			Vec2 p1 = new Vec2(model().getBody().getPosition().x+model().getWidth(), model().getPosition().y);
			Vec2 p2 = new Vec2(model().getPosition().x+5.0f, model().getPosition().y+model().getHeight()+0.1f);
			model().queryAABB(p1, p2);
		}
		else if (isMovingLeft()) {
			view().setAnimationLeftVisible(true);
			moveLeft();
			
			Vec2 p1 = new Vec2(model().getBody().getPosition().x-5.0f+model().getWidth(), model().getPosition().y);
			Vec2 p2 = new Vec2(model().getPosition().x, model().getPosition().y+model().getHeight()+0.1f);
			model().queryAABB(p1, p2);
		}
		else {
			view().setAnimationIdleVisible(true);
		}
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
	public MummyModel model() {
		return (MummyModel)model;
	}

	@Override
	public MummyView view() {
		return (MummyView)view;
	}
	
}
