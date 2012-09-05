package pl.pepuch.wildjoe.model;

import org.jbox2d.common.Vec2;

public abstract class StaticModel {
	
	protected Vec2 position;
	
	public StaticModel() {
	}
	
	public Vec2 position() {
		// TODO StaticModel::getPosition()
		return null;
	}
	
	public float angle() {
		// TODO StaticModel::getAngle()
		return 0.0f;
	}
	
	public void setAngle(float angle) {
		// TODO StaticModel::setPosition
	}
	
	public void setPosition(Vec2 position) {
		this.position = position;
	}
	
	public void destroy() {
		position = null;
	}
	
}
