package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.CoinModel;
import pl.pepuch.wildjoe.view.CoinView;


public class Coin extends DynamicActor {
	
	public Coin(GameWorld world, Vec2 position) {
		model = new CoinModel(world, position);
		view = new CoinView(model());
		model().setPosition(position);
	}

	public void paint(float alpha) {
		view().paint(alpha);
	}
	
	public void update(float delta) {
		view().update(delta);
	}

	@Override
	public CoinModel model() {
		return (CoinModel)model;
	}

	@Override
	public CoinView view() {
		return (CoinView)view;
	}
		
}
