package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import pl.pepuch.wildjoe.model.WallModel;
import pl.pepuch.wildjoe.view.WallView;


public class Wall extends DynamicActor {
	
	public Wall(World world, Vec2 position) {
		model = new WallModel(world);
		view = new WallView((WallModel)model);
		model.setPosition(position);
	}

	public void paint(float alpha) {
		view.paint(alpha);
	}
	
	public void update(float delta) {
		view.update(delta);
	}
		
}
