package pl.pepuch.wildjoe.core.body;

import static playn.core.PlayN.graphics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.Animation;
import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.Image;
import playn.core.Layer;
import playn.core.PlayN;

public class NormalPlayer extends GameBody {
	
	public Animation animationLeft;
	public Animation animationRight;
	public Animation animationIdle;
	
	public NormalPlayer(Vec2 position) {
		
		int height = 64;
		Image imageRunLeft = PlayN.assets().getImage("images/runLeft.png");
		Image imageRunRight = PlayN.assets().getImage("images/runRight.png");
		Image imageIdle = PlayN.assets().getImage("images/idle.png");
		animationRight = new Animation(imageRunRight, height, height, 10, 66);
		animationLeft = new Animation(imageRunLeft, height, height, 10, 66);
		animationIdle = new Animation(imageIdle, height, height, 1, 66);

		float playerHeight = GameWorld.physUnitPerScreenUnit()*height;
		float playerWidth = 1.0f;
		bodyDef = new BodyDef();
	    bodyDef.type = BodyType.DYNAMIC;
	    PolygonShape shape = new PolygonShape();
	    Vec2[] polygon = new Vec2[4];
	    polygon[0] = new Vec2(0, 0);
	    polygon[1] = new Vec2(playerWidth, 0);
		polygon[2] = new Vec2(playerWidth, playerHeight);
		polygon[3] = new Vec2(0, playerHeight);
		shape.set(polygon, polygon.length);
		
	    fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0.0f;
		fixtureDef.density = 30.0f;
		
		setPosition(position);
		animationLeft.getLayer().setVisible(false);
		setLayer(animationLeft.getLayer());
		animationRight.getLayer().setVisible(false);
		setLayer(animationRight.getLayer());
		animationIdle.getLayer().setVisible(true);
		setLayer(animationIdle.getLayer());
	}
	
	public void paint(float alpha) {
		float x = ((getPosition().x * alpha) + (getPosition().x * (1f - alpha)))/GameWorld.physUnitPerScreenUnit();
	    float y = ((getPosition().y * alpha) + (getPosition().y * (1f - alpha)))/GameWorld.physUnitPerScreenUnit();
	    float a = (getAngle() * alpha) + (getAngle() * (1f - alpha));
	    if (layer!=null) {
		    layer.setRotation(a);
		    layer.setTranslation(x, y);
	    }

    	animationLeft.paint(alpha);
    	animationRight.paint(alpha);
    	animationIdle.paint(alpha);
	}
	
	public void update(float delta) {
		setPosition(body.getPosition());
		setAngle(body.getAngle());
		animationLeft.update(delta);
		animationRight.update(delta);
		animationIdle.update(delta);
	}
	
	public void isMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}
	
	public void isMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}
	
	public void jump() {
		if (!isJumping()) {
			float impulse = body.getMass() * 42;
			body.applyLinearImpulse(new Vec2(0, impulse), body.getWorldCenter());
		}
	}
	
	public boolean isJumping() {
		return body.getLinearVelocity().y!=0 ? true : false;
	}
	
	public Vec2 getScreenPosition() {
		return new Vec2(body.getPosition().x, body.getPosition().y);
	}
	
	public Vec2 getWorldPosition() {
		return new Vec2(0.0f, 0.0f);
	}
	
	public void moveLeft() {
		setPosition(new Vec2(body.getPosition().x-0.1f, body.getPosition().y));
	}
	
	public void moveRight() {
		setPosition(new Vec2(body.getPosition().x+0.1f, body.getPosition().y));
	}
	
	public void setLayer(Layer layer) {
		this.layer = layer;
		layer.setOrigin(16, 0);
		graphics().rootLayer().add(this.layer);
	}
	
	public void setBody(Body body) {
		super.setBody(body);
		this.body.setFixedRotation(true);
	}

}