package pl.pepuch.wildjoe.core.body;

import static playn.core.PlayN.graphics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;

import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.Image;
import playn.core.Layer;

public abstract class GameBody {
	
	// rozmiar ciala w metrach
	private Vec2 size;
	//pozycja gornego lewego punktu ciala w metrach
	protected Vec2 position;
	protected Vec2 initialPosition;
	private Vec2 prevPosition;
	// kat nachylenia ciala
	private float angle;
	protected Image image;
	protected Body body;
	protected Layer layer;
	protected BodyDef bodyDef;
	protected FixtureDef fixtureDef;
	protected boolean isMovingLeft;
	protected boolean isMovingRight;
	
	public GameBody() {
		isMovingLeft = false;
		isMovingRight = false;
	}
	
	/**
	 * Rysowanie przebiega juz na ekranie wiec metry trzeba przerobic na piksele
	 * @param alpha
	 */
	public void paint(float alpha) {
		float x = ((getPosition().x * alpha) + (getPosition().x * (1f - alpha)))/GameWorld.physUnitPerScreenUnit();
	    float y = ((getPosition().y * alpha) + (getPosition().y * (1f - alpha)))/GameWorld.physUnitPerScreenUnit();
	    float a = (getAngle() * alpha) + (getAngle() * (1f - alpha));
	    if (layer!=null) {
		    layer.setRotation(a);
		    layer.setTranslation(x, y);
	    }
	}
	
	public void update(float delta) {
		setPosition(body.getPosition());
		setAngle(body.getAngle());
	}
	
	public void setPosition(Vec2 position) {
		this.position = position;
		if (body!=null) {
			body.setTransform(position, body.getAngle());
		}
	}
	
	public void setPrevPosition(Vec2 position) {
		this.prevPosition = position;
	}
	
	public Vec2 getPosition() {
		if (initialPosition==null) {
			initialPosition = position;
		}
		if (position==null) {
			position = new Vec2(0.0f, 0.0f);
		}
		
		return position;
	}
	
	public Vec2 getInitialPosition() {
		if (initialPosition==null)
			return new Vec2(0.0f, 0.0f);
		return initialPosition;
	}
	
	public Vec2 getPrevPosition() {
		if (prevPosition==null)
			prevPosition = new Vec2(0.0f, 0.0f);
		return prevPosition;
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public float getAngle() {
		return angle;
	}
	
	public BodyDef getBodyDef() {
		return bodyDef;
	}
	
	public FixtureDef getFixtureDef() {
		return fixtureDef;
	}
	
	public void setBody(Body body) {
		this.body = body;
		setPosition(getPosition());
	}
	
	public Body getBody() {
		return body;
	}
	
	public void setLayer(Layer layer) {
		this.layer = layer;
		graphics().rootLayer().add(this.layer);
	}
	
	public boolean isMovingLeft() {
		if (getPrevPosition().x-getPosition().x==0)
			return false;
		return isMovingLeft;
	}
	
	public boolean isMovingRight() {
		if (getPrevPosition().x-getPosition().x==0)
			return false;
		return isMovingRight;
	}
	
	public void moveLeft() {
		setPosition(new Vec2(body.getPosition().x-0.1f, body.getPosition().y));
	}
	
	public void moveRight() {
		setPosition(new Vec2(body.getPosition().x+0.1f, body.getPosition().y));
	}
	
	public void destroy() {
		layer.destroy();
	}

}
