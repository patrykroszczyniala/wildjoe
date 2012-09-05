package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.MummyModel;
import pl.pepuch.wildjoe.view.MummyView;

public class Mummy extends DynamicActor {

	public Mummy(GameWorld world, Vec2 position) {
		model = new MummyModel(world, position);
		view = new MummyView(model());
		isMovingRight(true);
		model().body().setUserData(this);
	}
	
	public void paint(float alpha) {
		view().paint(alpha);
		model().drawDebugData();
	}
	
	public void update(float delta) {
		view().update(delta);
		
//		float diff = model().position().x-model().origin().x;
		
//		if (diff<=-3 || (diff>=0.0f && diff<3.0f && isMovingRight())) {
//			isMovingRight(true);
//		}
//		if (diff>=3.0f || (diff<0.0f && diff>-3 && isMovingLeft())) {
//			isMovingLeft(true);
//		}	
		if (model().position().x-model().positionBefore().x==0) {
			if (isMovingRight()) {
				isMovingLeft(true);
			}
			else if (isMovingLeft()) {
				isMovingRight(true);
			}
		}
		if (isMovingRight()) {
			view().setAnimationRightVisible(true);
			moveRight();
		}
		else if (isMovingLeft()) {
			view().setAnimationLeftVisible(true);
			moveLeft();
		}
		else {
			view().setAnimationIdleVisible(true);
		}
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
