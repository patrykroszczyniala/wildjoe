package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PlayerModel extends DynamicModel {
	
	public PlayerModel(World world) {
		super(world);
	}
	
	public Body createBody(World world) {
		width = 1.0f;
		height = 2.0f;
		
		bodyDef = new BodyDef();
	    bodyDef.type = BodyType.DYNAMIC;
	    PolygonShape shape = new PolygonShape();
	    Vec2[] polygon = new Vec2[4];
	    polygon[0] = new Vec2(0, 0);
	    polygon[1] = new Vec2(width, 0);
		polygon[2] = new Vec2(width, height);
		polygon[3] = new Vec2(0, height);
		shape.set(polygon, polygon.length);
		
	    fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = 1.0f;
		fixtureDef.restitution = 0.0f;
		fixtureDef.density = 30.0f;

		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		
		return body;
	}
	
}
