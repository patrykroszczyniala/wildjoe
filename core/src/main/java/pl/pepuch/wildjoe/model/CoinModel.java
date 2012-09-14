package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;

public class CoinModel extends DynamicModel {
	
	CircleShape shape;
	
	public CoinModel(GameWorld world, Vec2 position) {
		super(world, position);
		position.set(new Vec2(position.x+0.5f, position.y+0.5f));
	}
	
	@Override
	protected Body createBody(GameWorld world) {
		float radius = 0.2f;
		bodyDef = new BodyDef();
	    bodyDef.type = BodyType.KINEMATIC;
	    shape = new CircleShape();
	    shape.m_radius = radius;

	    fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0.0f;
		fixtureDef.density = 0.0f;
		
		body = world.world().createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setUserData(this);
		
		return body;
	}
	
	public void setRadius(float radius) {
		if (body!=null) {
			body.getFixtureList().m_shape.m_radius = radius;
		}
	}
	
	public float radius() {
		return body.getFixtureList().m_shape.m_radius;
	}
	
}
