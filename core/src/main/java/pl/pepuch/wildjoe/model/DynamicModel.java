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
	protected final Vec2 origin;
	protected float speed;
	
	public DynamicModel(GameWorld world, Vec2 position) {
		// default values
		setSpeed(0.1f);
		this.world = world;
		body = createBody(world);
		setPosition(position);
		origin = body.getPosition().clone();
	}
	
	public GameWorld getGameWorld() {
		return world;
	}
	
	public Body getBody() {
		return body;
	}
	
	public Vec2 getOrigin() {
		float x = origin.x+world.getArenaPositionX();
		float y = origin.y;
		return new Vec2(x, y);
	}

	public Vec2 getPosition() {
		return body.getPosition();
	}
	
	public void destroy() {
		world.world().destroyBody(body);
	}
	
	public Vec2 getGameWorldPosition() {
		float x = body.getPosition().x+world.getArenaPositionX();
		float y = body.getPosition().y;
		return new Vec2(x, y);
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
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	protected abstract Body createBody(GameWorld world);
	
}
