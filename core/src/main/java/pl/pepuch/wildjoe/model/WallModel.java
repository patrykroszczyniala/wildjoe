package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import pl.pepuch.wildjoe.core.WildJoe;
import playn.core.PlayN;

public class WallModel extends DynamicModel {
	
	public WallModel(World world) {
		super(world);
	}
	
	@Override
	protected Body createBody(World world) {
		width = 0.00001f;
		height = PlayN.graphics().height()*WildJoe.physUnitPerScreenUnit;
		
		bodyDef = new BodyDef();
	    bodyDef.type = BodyType.STATIC;
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
		
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setUserData(this);
		
		return body;
	}

}
