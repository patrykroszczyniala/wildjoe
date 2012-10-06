package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;

public class DiamondModel extends DynamicModel {
	
	public DiamondModel(GameWorld world, Vec2 position) {
		super(world);
		setSpeed(0.1f);
		setWidth(0.5f);
		setHeight(0.5f);
		body = createBody(world);
		setPosition(position);
		origin = body.getPosition().clone();
	}
	
	@Override
	protected Body createBody(GameWorld world) {
		BodyDef bodyDef = new BodyDef();
	    bodyDef.type = BodyType.KINEMATIC;

	    PolygonShape shape = new PolygonShape();
	    shape.setAsBox(width/2, height/2, new Vec2(0, 0), 0);
	    
	    FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0.0f;
		fixtureDef.density = 0.0f;
		
		body = world.world().createBody(bodyDef);
		body.createFixture(fixtureDef);
		
		return body;
	}
	
}
