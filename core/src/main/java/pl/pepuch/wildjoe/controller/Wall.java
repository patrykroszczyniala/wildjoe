package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.WallModel;
import pl.pepuch.wildjoe.view.WallView;


public class Wall extends DynamicActor {
	
	public Wall(GameWorld world, Vec2 position) {
		model = new WallModel(world, position);
		view = new WallView(model());
		model().setPosition(position);
		model().body().setUserData(this);
	}

	public void paint(float alpha) {
		view().paint(alpha);
	}
	
	public void update(float delta) {
		view().update(delta);
	}

	@Override
	public WallModel model() {
		return (WallModel)model;
	}

	@Override
	public WallView view() {
		return (WallView)view;
	}
		
}
