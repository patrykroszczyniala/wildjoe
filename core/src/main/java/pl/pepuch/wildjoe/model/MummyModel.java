package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.callbacks.MummyQueryCallback;

public class MummyModel extends DynamicModel {
	
	private AABB aabb;
	private MummyQueryCallback queryCallback;
	private Vec2 aabbP1;
	private Vec2 aabbP2;
	
	public MummyModel(GameWorld world, Vec2 position) {
		super(world, position);
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

		body = world.world().createBody(bodyDef);
		body.createFixture(fixtureDef);
		// body will be active all the time
		body.setSleepingAllowed(false);
		body.setAwake(false);
		// wake up body
		body.applyForce(body.getLocalCenter(), body.getLocalCenter());
		// player will not change his angle !
		body.setFixedRotation(true);
		
		aabb = new AABB();
		queryCallback = new MummyQueryCallback();
		
		setSpeed(0.02f);
		
		return body;
	}
	
	public void queryAABB(Vec2 p1, Vec2 p2) {
		aabbP1 = p1;
		aabbP2 = p2;
		aabb.lowerBound.set(aabbP1);
		aabb.upperBound.set(aabbP2);
		getBody().getWorld().queryAABB(queryCallback, aabb);
		
	}
	
	public void drawDebugData() {
		if (world.debugDraw!=null && aabbP1!=null && aabbP2!=null) {
			Vec2[] vertices = new Vec2[4];
			vertices[0] = new Vec2(aabbP1.x, aabbP1.y);
			vertices[1] = new Vec2(aabbP2.x, aabbP1.y);
			vertices[2] = new Vec2(aabbP2.x, aabbP2.y);
			vertices[3] = new Vec2(aabbP1.x, aabbP2.y);
			world.debugDraw.drawPolygon(vertices, 4, Color3f.RED);
		}
	}
	
}
