package pl.pepuch.wildjoe.model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;

public class BlockModel extends DynamicModel {
	
	private boolean isBoundary;
	private boolean hasLeftNeighbor;
	private boolean hasRightNeighbor;
	
	public BlockModel(GameWorld world, Vec2 position) {
		super(world, position);
	}
	
	@Override
	protected Body createBody(GameWorld world) {
		width = 1.0f;
		height = 1.0f;
		
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
		fixtureDef.restitution = 0.01f;
		
		body = world.world().createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setUserData(this);
		
		setBoundary(false, false, false);
		
		return body;
	}
	
	public boolean isBoundary() {
		return isBoundary;
	}
	
	public void setBoundary(boolean isBoundary, boolean hasLeftNeighbor, boolean hasRightNeighbor) {
		this.isBoundary = isBoundary;
		this.hasLeftNeighbor = hasLeftNeighbor;
		this.hasRightNeighbor = hasRightNeighbor;
	}
	
	public boolean hasLeftNeighbor() {
		return hasLeftNeighbor;
	}
	
	public boolean hasRightNeighbor() {
		return hasRightNeighbor;
	}

}
