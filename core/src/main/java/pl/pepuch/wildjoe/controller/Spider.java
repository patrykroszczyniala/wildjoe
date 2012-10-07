package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.SpiderModel;
import pl.pepuch.wildjoe.view.SpiderView;


public class Spider extends DynamicActor {
	
	SpiderWeapon weapon;
	
	public Spider(GameWorld world, Vec2 position, SpiderWeapon weapon) {
		this.weapon = weapon;
		weapon.spidersCounterIncrement();
		model = new SpiderModel(world, position);
		view = new SpiderView(model());
		model().body().setUserData(this);
		go();
	}

	public void paint(float alpha) {
		view().paint(alpha);
		model().drawDebugData();
	}
	
	public void update(float delta) {
		view().update(delta);

		// obszar, w ktorym pajak widzi zawodnika
		Vec2 p1 = new Vec2(model().body().getPosition().x-3, model().position().y-model().height()/2);
		Vec2 p2 = new Vec2(model().body().getPosition().x+3, model().position().y+model().height()/2);
		model().queryAABB(p1, p2);
		
		if (model().gameWorldPosition().x-model().gameWorldPositionBefore().x==0) {
			if (model().isTurnedRight()) {
				turnLeft();
			}
			else if (model().isTurnedLeft()) {
				turnRight();
			}
		}
		
		makeStep();
	}
	
	@Override
	public void destroy() {
		super.destroy();
		weapon.spidersCounterDecrement();
	}

	@Override
	public SpiderModel model() {
		return (SpiderModel)model;
	}

	@Override
	public SpiderView view() {
		return (SpiderView)view;
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
