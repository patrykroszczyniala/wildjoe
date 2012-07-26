package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import pl.pepuch.wildjoe.model.BlockModel;
import pl.pepuch.wildjoe.view.BlockView;


public class Block extends DynamicActor {
	
	public Block(World world, Vec2 position) {
		model = new BlockModel(world);
		view = new BlockView((BlockModel)model);
		model.setPosition(position);
	}

	public void paint(float alpha) {
		view.paint(alpha);
	}
	
	public void update(float delta) {
		view.update(delta);
	}
		
}
