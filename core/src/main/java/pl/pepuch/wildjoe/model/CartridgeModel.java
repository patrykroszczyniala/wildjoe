package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;

public class CartridgeModel extends DynamicModel {
	
	private float range;
	
	public CartridgeModel(GameWorld world, Vec2 position) {
		super(world, position);
	}
	
	@Override
	protected Body createBody(GameWorld world) {
		width = 0.05f;
		height = 0.05f;
		
		bodyDef = new BodyDef();
	    bodyDef.type = BodyType.DYNAMIC;
	    CircleShape shape = new CircleShape();
	    shape.m_radius = width;
	    
	    fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = 1.0f;
		fixtureDef.restitution = 0.01f;
		
		body = world.world().createBody(bodyDef);
		body.createFixture(fixtureDef);
	
		return body;
	}
	
	public void setRange(float range) {
		this.range = range;
	}
	
	public float range() {
		return range;
	}
	
}
