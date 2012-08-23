package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.MummyModel;
import pl.pepuch.wildjoe.view.MummyView;

public class Mummy extends DynamicActor {
	
//	private Weapon weapon;

	public Mummy(GameWorld world, Vec2 position) {
		model = new MummyModel(world, position);
		view = new MummyView(model());
		model().getBody().setUserData(this);
		isMovingRight(true);
//		weapon = new Weapon(model().getGameWorld(), model().getPosition());
//		weapon.model().setFrequency(1/2f); // one cartridge per one second (1/1 = 1 ;))
//		model().getGameWorld().add(weapon);
	}
	
	public void paint(float alpha) {
		view().paint(alpha);
//		weapon.view().paint(alpha);
		model().drawDebugData();
	}
	
	public void update(float delta) {
		view().update(delta);
//		weapon.update(delta);
		
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
			
//			float weaponX = model().getPosition().x+model().getWidth()+0.1f;
//			float weaponY = model().getPosition().y+(model().getHeight()*0.5f);
//			Vec2 pos = new Vec2(weaponX, weaponY);
//			weapon.model().setPosition(pos);
//
//			Vec2 p1 = new Vec2(model().getBody().getPosition().x+model().getWidth(), model().getPosition().y+(model().getHeight()*0.33f));
//			Vec2 p2 = new Vec2(model().getPosition().x+5.0f, model().getPosition().y+(model().getHeight()*0.66f));
//			model().queryAABB(p1, p2);
		}
		else if (isMovingLeft()) {
			view().setAnimationLeftVisible(true);
			moveLeft();
			
//			float weaponX = model().getPosition().x-weapon.model().getWidth()-0.1f;
//			float weaponY = model().getPosition().y+(model().getHeight()*0.5f);
//			Vec2 pos = new Vec2(weaponX, weaponY);
//			weapon.model().setPosition(pos);
//			
//			Vec2 p1 = new Vec2(model().getBody().getPosition().x-5.0f+model().getWidth(), model().getPosition().y+(model().getHeight()*0.33f));
//			Vec2 p2 = new Vec2(model().getPosition().x, model().getPosition().y+(model().getHeight()*0.66f));
//			model().queryAABB(p1, p2);
		}
		else {
			view().setAnimationIdleVisible(true);
		}
	}
	
//	public void shoot() {
//		float impulse = model().getBody().getMass() * 200;
//		if (isMovingLeft()) {
//			weapon.shoot(new Vec2(impulse/-1, 0));
//		}
//		if (isMovingRight()) {
//			weapon.shoot(new Vec2(impulse, 0));
//		}
//	}

	@Override
	public MummyModel model() {
		return (MummyModel)model;
	}

	@Override
	public MummyView view() {
		return (MummyView)view;
	}
	
}
