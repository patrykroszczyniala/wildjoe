package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.CartridgeModel;
import pl.pepuch.wildjoe.view.CartridgeView;


public class Cartridge extends DynamicActor {
	
	public Cartridge(GameWorld world, Vec2 position) {
		model = new CartridgeModel(world, position);
		view = new CartridgeView(model());
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
	public CartridgeModel model() {
		return (CartridgeModel)model;
	}

	@Override
	public CartridgeView view() {
		return (CartridgeView)view;
	}
		
}
