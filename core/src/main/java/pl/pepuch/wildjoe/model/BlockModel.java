package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;

public class BlockModel extends DynamicModel {
	
	public BlockModel(GameWorld world, Vec2 position, float width, float height) {
		super(world);
		setWidth(width);
		setHeight(height);
		setSpeed(0.1f);
		body = createBody(world);
		setPosition(position);
		origin = body.getPosition().clone();
	}
	
	@Override
	protected Body createBody(GameWorld world) {
		BodyDef bodyDef = new BodyDef();
	    bodyDef.type = BodyType.STATIC;
	    PolygonShape shape = new PolygonShape();
	    shape.setAsBox(width/2, height/2, new Vec2(width/2, -height/2), 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = 1.0f;
		fixtureDef.restitution = 0.01f;
		
		body = world.world().createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setSleepingAllowed(false);

		return body;
	}
	
}
