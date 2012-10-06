package pl.pepuch.wildjoe.controller;

import java.math.BigDecimal;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.model.DynamicModel;
import pl.pepuch.wildjoe.view.DynamicView;

public abstract class DynamicActor {
	
	protected DynamicView view;
	protected DynamicModel model;
	private boolean isMoving;
	private boolean isVisible;
	
	public void stop() {
		isMoving = false;
	}
	
	public void go() {
		isMoving = true;
	}
	
	public boolean isMoving() {
		return isMoving;
	}
	
	public void makeStep() {
		if (isMoving) {
			float x = model().position().x-model().speed();
			if (model.isTurnedRight()) {
				x = model().position().x+model().speed();
			}
			float y = model().position().y;
			model().setPosition(new Vec2(x, y));
		}
	}
	
	public void jump() {
		if (!isJumping()) {
			float impulse = model().body().getMass() * 80;
			model().body().applyLinearImpulse(new Vec2(model().body().getPosition().x, impulse), model().body().getWorldCenter());
		}
	}
	
	public void turnLeft() {
		model().turnLeft();
	}
	
	public void turnRight() {
		model().turnRight();
	}
	
	public boolean isJumping() {
		float y = BigDecimal.valueOf(model().body().getLinearVelocity().y).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		return y!=0 ? true : false;
	}
	
	public void destroy() {
		view().destroy();
		model().destroy();
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
		view().layer().setVisible(isVisible);
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public abstract void paint(float alpha);
	public abstract void update(float delta);
	public abstract DynamicModel model();
	public abstract DynamicView view();
}
