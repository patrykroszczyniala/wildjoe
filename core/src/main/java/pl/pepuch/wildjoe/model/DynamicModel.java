package pl.pepuch.wildjoe.model;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;

public abstract class DynamicModel {
	
	protected BodyDef bodyDef;
	protected FixtureDef fixtureDef;
	protected Body body;
	protected float height;
	protected float width;
	protected GameWorld world;
	protected Vec2 origin;
	protected float speed;
	protected Vec2 positionBefore;
	
	public DynamicModel(GameWorld world, Vec2 position) {
		setSpeed(0.1f);
		this.world = world;
		body = createBody(world);
		setPosition(position);
		origin = body.getPosition().clone();
	}
	
	public GameWorld gameWorld() {
		return world;
	}
	
	public Body body() {
		return body;
	}
	
	public Vec2 origin() {
		float x = origin.x+world.getArenaPositionX();
		float y = origin.y;
		return new Vec2(x, y);
	}

	public Vec2 position() {
		return body.getPosition();
	}
	
	public Vec2 positionBefore() {
		return positionBefore;
	}
	
	public void destroy() {
		world.world().destroyBody(body);
		bodyDef = null;
		fixtureDef = null;
		body = null;
		origin = null;
	}
	
	public Vec2 gameWorldPosition() {
		float x = body.getPosition().x+world.getArenaPositionX();
		float y = body.getPosition().y;
		return new Vec2(x, y);
	}
	
	public float angle() {
		return body.getAngle();
	}
	
	public void setAngle(float angle) {
		body.setTransform(body.getPosition(), angle);
	}
	
	public void setPosition(Vec2 position) {
		positionBefore = position().clone();
		body.setTransform(position, body.getAngle());
	}
	
	public float height() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public float width() {
		return width;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float speed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	protected abstract Body createBody(GameWorld world);
	
}
