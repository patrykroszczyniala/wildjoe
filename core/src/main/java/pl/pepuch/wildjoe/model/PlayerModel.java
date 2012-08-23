package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;

public class PlayerModel extends DynamicModel {
	
	public PlayerModel(GameWorld world, Vec2 position) {
		super(world, position);
		System.out.println("world:! "+world);
	}
	
	public Body createBody(GameWorld world) {
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
		fixtureDef.restitution = 0.1f;
		fixtureDef.density = 30.0f;

		body = world.world.createBody(bodyDef);
		System.out.println("body: "+body);
		System.out.println("world: "+world);
		System.out.println("world.world: "+world.world);
		body.createFixture(fixtureDef);
		// body will be active all the time
		body.setSleepingAllowed(false);
		body.setAwake(false);
		// wake up body
		body.applyForce(body.getLocalCenter(), body.getLocalCenter());
		// player will not change his angle !
		body.setFixedRotation(true);
		
		setSpeed(0.1f);
		
		return body;
	}
	
}
