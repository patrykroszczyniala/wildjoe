package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import pl.pepuch.wildjoe.model.DynamicModel;
import pl.pepuch.wildjoe.view.DynamicView;

public abstract class DynamicActor {
	
	protected DynamicView view;
	protected DynamicModel model;
	
	public void moveLeft() {
		float x = model.getPosition().x-0.1f;
		float y = model.getPosition().y;
		model.setPosition(new Vec2(x, y));
	}
	
	public void moveRight() {
		float x = model.getPosition().x+0.1f;
		float y = model.getPosition().y;
		model.setPosition(new Vec2(x, y));
	}
	
	public DynamicView getView() {
		return view;
	}
	
	public DynamicModel getModel() {
		return model;
	}
	
	public void destroy() {
		// TODO usunac tec body
		view.getLayer().destroy();
	}
	
	public abstract void paint(float alpha);
	public abstract void update(float delta);
}
