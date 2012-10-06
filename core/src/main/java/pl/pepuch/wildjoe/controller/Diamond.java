package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.DiamondModel;
import pl.pepuch.wildjoe.view.DiamondView;


public class Diamond extends DynamicActor {
	
	public Diamond(GameWorld world, Vec2 position) {
		model = new DiamondModel(world, position);
		view = new DiamondView(model());
		model().setPosition(new Vec2(position.x+0.5f, position.y+0.5f));
		model().body().setUserData(this);
	}

	public void paint(float alpha) {
		view().paint(alpha);
	}
	
	public void update(float delta) {
		view().update(delta);
	}

	@Override
	public DiamondModel model() {
		return (DiamondModel)model;
	}

	@Override
	public DiamondView view() {
		return (DiamondView)view;
	}
		
}
