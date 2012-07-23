package pl.pepuch.wildjoe.core.world;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import pl.pepuch.wildjoe.core.WildJoe;
import pl.pepuch.wildjoe.core.body.GameBody;
import pl.pepuch.wildjoe.core.body.NormalPlayer;
import playn.core.CanvasImage;
import playn.core.DebugDrawBox2D;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.PlayN;
import playn.core.Sound;

public class GameWorld {
	
	// stosunek pikseli do jednego metra
//	private float physUnitPerScreenUnit;
	// szerokosc swiada w metrach
	private static float width = 48.0f;
	// wysokosc swiata w metrach
	private static float height = 32.0f;
	// pozwala debugowac Box2d
	private DebugDrawBox2D debugDraw;
	// swiat Box2d
	public World world;
	// lista dodanych cial
	List<GameBody> gameBodyList;
	public List<GameBody> gameBodyListToRemove;
	// obiekt zawodnika
	public NormalPlayer player;
	
	private float xPrev = 0.0f;
	private float x = 0.0f;
	private float worldWidth;
	private float worldHeight;
	private GroupLayer bgLayer2;
	private float bgLayer2X;
	public PointCounter pointCounter;
	
	public GameWorld() {
		worldWidth = 0.0f;
		worldHeight = 0.0f;
		gameBodyList = new ArrayList<GameBody>();
		gameBodyListToRemove = new ArrayList<GameBody>();
		Vec2 gravity = new Vec2(0.0f, 10.0f);
//	    boolean doSleep = true;
		boolean doSleep = false;
	    world = new World(gravity, doSleep);
		if (WildJoe.debug) {
	    	this.enableDebug();
	    }
		// bg layer -1
		bgLayer2X = 100.0f;
		bgLayer2 = graphics().createGroupLayer();
		Image bgTreeImage = assets().getImage("images/bgtree.png");
		bgLayer2.addAt(graphics().createImageLayer(bgTreeImage), bgLayer2X, 320);
		graphics().rootLayer().add(bgLayer2);
		// contact listener
		world.setContactListener(new WorldContactListener(this));
		// tablica wynikow
		pointCounter = new PointCounter();
		graphics().rootLayer().addAt(pointCounter.getLayer(), 10, 10);
	}
	
	public static float physUnitPerScreenUnit() {
		return 1f/32f;
	}
	
	public void paint(float alpha) {
		if (WildJoe.debug) {
			debugDraw.getCanvas().clear();
			world.drawDebugData();
		}
		
		// paint blocks
		for (Iterator iterator = gameBodyList.iterator(); iterator.hasNext();) {
			GameBody body = (GameBody)iterator.next();
		    body.paint(alpha);
		}
		
		pointCounter.getIface().paint(alpha);
		player.paint(alpha);
	}
	
	public void update(float delta) {
		float leftEnd = getScreenWidth()*0.33f;
		float rightEnd = getScreenWidth()*0.66f;
		
		if (player.isMovingLeft()) {
			player.animationIdle.getLayer().setVisible(false);
			player.animationRight.getLayer().setVisible(false);
			player.animationLeft.getLayer().setVisible(true);
			player.setLayer(player.animationLeft.getLayer());
		}
		else if (player.isMovingRight()) {
			player.animationIdle.getLayer().setVisible(false);
			player.animationLeft.getLayer().setVisible(false);
			player.animationRight.getLayer().setVisible(true);
			player.setLayer(player.animationRight.getLayer());
		}
		else {
			player.animationIdle.getLayer().setVisible(true);
			player.animationLeft.getLayer().setVisible(false);
			player.animationRight.getLayer().setVisible(false);
			player.setLayer(player.animationIdle.getLayer());
		}
		
		if (player.getScreenPosition().x>=leftEnd && player.getScreenPosition().x<=rightEnd) {
			if (player.isMovingLeft()) {
				player.moveLeft();
			}
			if (player.isMovingRight()) {
				player.moveRight();
			}
		}
		else {
			if (player.getScreenPosition().x<leftEnd) {
				if (player.isMovingLeft()) {
					bgLayer2X += 0.5f;
					for (Iterator iterator = gameBodyList.iterator(); iterator.hasNext();) {
						GameBody body = (GameBody)iterator.next();
						body.update(delta);
						body.moveRight();
					}
				}
				if (player.isMovingRight()) {
					player.moveRight();
				}
			}
			if (player.getScreenPosition().x>rightEnd) {
				if (player.isMovingLeft()) {
					player.moveLeft();
				}
				if (player.isMovingRight()) {
					bgLayer2X -= 0.5f;
					for (Iterator iterator = gameBodyList.iterator(); iterator.hasNext();) {
						GameBody body = (GameBody)iterator.next();
						body.update(delta);
						body.moveLeft();
					}
				}
			}
		}
		player.update(delta);
		pointCounter.getIface().update(delta);
		bgLayer2.setTranslation(bgLayer2X, bgLayer2.originY());
		world.step(0.033f, 10, 10);
		// remove bodies
		for (Iterator iterator = gameBodyListToRemove.iterator(); iterator.hasNext();) {
			GameBody body = (GameBody)iterator.next();
			world.destroyBody(body.getBody());
			body.destroy();
		}
	}
	
	private void enableDebug() {
		CanvasImage image = graphics().createImage(1,1);
		graphics().rootLayer().add(graphics().createImageLayer(image));
		debugDraw = new DebugDrawBox2D();
		debugDraw.setCanvas(image);
		debugDraw.setFlipY(false);
		debugDraw.setStrokeAlpha(150);
		debugDraw.setFillAlpha(75);
		debugDraw.setStrokeWidth(2.0f);
		debugDraw.setFlags(DebugDraw.e_shapeBit | DebugDraw.e_jointBit | DebugDraw.e_aabbBit);
		debugDraw.setCamera(0, 0, 1f / GameWorld.physUnitPerScreenUnit());
		world.setDebugDraw(debugDraw);
	}
	
	public void add(GameBody gameBody) {
		gameBody.setBody(world.createBody(gameBody.getBodyDef()));
		gameBody.getBody().createFixture(gameBody.getFixtureDef());
		gameBody.getBody().setTransform(gameBody.getPosition(), 0);
		gameBody.getBody().setLinearDamping(0.2f);
		gameBodyList.add(gameBody);
		gameBody.getBody().setUserData(gameBody);
	}
	
	public GameBody getPlayer() {
		return gameBodyList.get(0);
	}
	
	public float getScreenWidth() {
		return graphics().width()*physUnitPerScreenUnit();
	}
	
	public float getScreenHeight() {
		return graphics().height()*physUnitPerScreenUnit();
	}
	
	public float getWorldWidth() {
		if (worldWidth==0.0f) {
			for (Iterator iterator = gameBodyList.iterator(); iterator.hasNext();) {
				GameBody body = (GameBody)iterator.next();
				if (worldWidth < body.getInitialPosition().x)
					worldWidth = body.getInitialPosition().x;
			}
			worldWidth++; //zwiekszam szerokosc o 1, tzn o szerokosc jednego klocka
		}
		return worldWidth;
	}
	
	public float getWorldHeight() {
		return getScreenHeight();
	}

}
