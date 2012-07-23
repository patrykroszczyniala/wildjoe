package pl.pepuch.wildjoe.core.body;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;

public class Enemy1Player extends GameBody {
	
	public Enemy1Player(Vec2 position) {
		
//		image = assets().getImage("images/bug1.png");
		image = assets().getImage("images/santa.png");
		layer = graphics().createImageLayer(image);
		layer.setOrigin(image.width()/2.0f, image.height()/2.0f);
		
		bodyDef = new BodyDef();
	    bodyDef.type = BodyType.DYNAMIC;
	    CircleShape shape = new CircleShape();
	    shape.m_radius = 0.5f;
	    fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0f;
		
		setPosition(position);
		setLayer(layer);
		layer.setTranslation(position.x/GameWorld.physUnitPerScreenUnit(), position.y/GameWorld.physUnitPerScreenUnit());
	}
	
	public void moveLeft() {
		setPosition(new Vec2(body.getPosition().x-0.1f, body.getPosition().y));
	}
	
	public void isMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}
	
	public void moveRight() {
		setPosition(new Vec2(body.getPosition().x+0.1f, body.getPosition().y));
	}
	
	public void isMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}
	
	public void jump() {
		body.applyLinearImpulse(new Vec2(body.getPosition().x, body.getPosition().y+28.0f), body.getPosition());
	}
	
}
