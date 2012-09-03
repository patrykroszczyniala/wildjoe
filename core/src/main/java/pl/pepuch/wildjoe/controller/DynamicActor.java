package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.model.DynamicModel;
import pl.pepuch.wildjoe.view.DynamicView;

public abstract class DynamicActor {
	
	protected DynamicView view;
	protected DynamicModel model;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private boolean isVisible;
	
	public void moveLeft() {
		float x = model().getPosition().x-model().getSpeed();
		float y = model().getPosition().y;
		model().setPosition(new Vec2(x, y));
	}
	
	public void moveRight() {
		float x = model().getPosition().x+model().getSpeed();
		float y = model().getPosition().y;
		model().setPosition(new Vec2(x, y));
	}
	
	public void jump() {
		if (!isJumping()) {
			float impulse = model().getBody().getMass() * 80;
			model().getBody().applyLinearImpulse(new Vec2(0, impulse), model().getBody().getWorldCenter());
		}
	}
	
	public boolean isMovingLeft() {
		return isMovingLeft;
	}
	
	public void isMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
		if (isMovingLeft) {
			this.isMovingRight = !isMovingLeft;
		}
	}
	
	public boolean isMovingRight() {
		return isMovingRight;
	}
	
	public void isMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
		if (isMovingRight) {
			this.isMovingLeft = !isMovingRight;
		}
	}
	
	public boolean isJumping() {
		return (int)model().getBody().getLinearVelocity().y!=0 ? true : false;
	}
	
	public void destroy() {
		view().destroy();
		model().destroy();
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
		view().getLayer().setVisible(isVisible);
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public abstract void paint(float alpha);
	public abstract void update(float delta);
	public abstract DynamicModel model();
	public abstract DynamicView view();
}
