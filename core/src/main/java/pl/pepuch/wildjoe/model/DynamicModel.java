package pl.pepuch.wildjoe.model;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public abstract class DynamicModel {
	
	protected BodyDef bodyDef;
	protected FixtureDef fixtureDef;
	protected Body body;
	protected float height;
	protected float width;
	
	public DynamicModel(World world) {
		body = createBody(world);
	}
	
	public Body getBody() {
		return body;
	}

	public Vec2 getPosition() {
		return body.getPosition();
	}
	
	public float getAngle() {
		return body.getAngle();
	}
	
	public void setAngle(float angle) {
		body.setTransform(body.getPosition(), angle);
	}
	
	public void setPosition(Vec2 position) {
		body.setTransform(position, body.getAngle());
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	protected abstract Body createBody(World world);
	
}
