package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import pl.pepuch.wildjoe.model.CoinModel;
import pl.pepuch.wildjoe.view.CoinView;


public class Coin extends DynamicActor {
	
	public Coin(World world, Vec2 position) {
		model = new CoinModel(world);
		view = new CoinView((CoinModel)model);
		model.setPosition(position);
	}

	public void paint(float alpha) {
		view.paint(alpha);
	}
	
	public void update(float delta) {
		view.update(delta);
	}
		
}
