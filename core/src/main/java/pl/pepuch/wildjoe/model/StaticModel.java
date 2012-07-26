package pl.pepuch.wildjoe.model;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public abstract class StaticModel {
	
	protected Vec2 position;
	
	public StaticModel() {
	}
	
	public Vec2 getPosition() {
		// TODO StaticModel::getPosition()
		return null;
	}
	
	public float getAngle() {
		// TODO StaticModel::getAngle()
		return 0.0f;
	}
	
	public void setAngle(float angle) {
		// TODO StaticModel::setPosition
	}
	
	public void setPosition(Vec2 position) {
		this.position = position;
	}
	
}
