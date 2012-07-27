package pl.pepuch.wildjoe.core.world;

import static playn.core.PlayN.graphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import pl.pepuch.wildjoe.controller.Background;
import pl.pepuch.wildjoe.controller.DynamicActor;
import pl.pepuch.wildjoe.controller.Player;
import pl.pepuch.wildjoe.core.WildJoe;
import playn.core.CanvasImage;
import playn.core.DebugDrawBox2D;
import playn.core.PlayN;

public class GameWorld {
	
	// pozwala debugowac Box2d
	private DebugDrawBox2D debugDraw;
	// swiat Box2d
	public World world;
	// lista dodanych cial
	List<DynamicActor> gameBodyList;
	public List<DynamicActor> gameBodyListToRemove;
	// obiekt zawodnika
	public Player player;
	
	public PointCounter pointCounter;
	public Background background;
	private float worldWidth;
	
	public GameWorld() {
		gameBodyList = new ArrayList<DynamicActor>();
		gameBodyListToRemove = new ArrayList<DynamicActor>();
		Vec2 gravity = new Vec2(0.0f, 10.0f);
		boolean doSleep = true;
	    world = new World(gravity, doSleep);
		if (WildJoe.debug) {
	    	this.enableDebug();
	    }

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
		for (Iterator<DynamicActor> iterator = gameBodyList.iterator(); iterator.hasNext();) {
			DynamicActor body = (DynamicActor)iterator.next();
		    body.paint(alpha);
		}
		
		pointCounter.getIface().paint(alpha);
		player.paint(alpha);
	}
	
	public void update(float delta) {
		float leftEnd = getScreenWidth()*0.33f;
		float rightEnd = getScreenWidth()*0.66f;
		
		if (player.getModel().getPosition().x<leftEnd && player.isMovingLeft()) {
			background.moveLeft();
			for (Iterator<DynamicActor> actor = gameBodyList.iterator(); actor.hasNext();) {
				DynamicActor body = actor.next();
				body.update(delta);
				body.moveRight();
			}
		}
		else if (player.getModel().getPosition().x>rightEnd && player.isMovingRight()) {
			background.moveRight();
			for (Iterator<DynamicActor> actor = gameBodyList.iterator(); actor.hasNext();) {
				DynamicActor body = actor.next();
				body.update(delta);
				body.moveLeft();
			}
		}
		else {
			if (player.isMovingLeft()) {
				player.moveLeft();
			}
			if (player.isMovingRight()) {
				player.moveRight();
			}
		}
	
		player.update(delta);
		pointCounter.getIface().update(delta);
		world.step(0.033f, 10, 10);
		// remove bodies
		for (Iterator<DynamicActor> iterator = gameBodyListToRemove.iterator(); iterator.hasNext();) {
			DynamicActor body = (DynamicActor)iterator.next();
			world.destroyBody(body.getModel().getBody());
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
	
	public void add(DynamicActor gameBody) {
		gameBodyList.add(gameBody);
		PlayN.graphics().rootLayer().add(gameBody.getView().getLayer());
		gameBody.getModel().getBody().setUserData(gameBody);
	}
	
	
	public float getScreenWidth() {
		return graphics().width()*physUnitPerScreenUnit();
	}
	
	public float getScreenHeight() {
		return graphics().height()*physUnitPerScreenUnit();
	}
	
	public float getWorldHeight() {
		return getScreenHeight();
	}
	
	public float getWorldWidth() {
		if (worldWidth==0.0f) {
			for (Iterator<DynamicActor> iterator = gameBodyList.iterator(); iterator.hasNext();) {
				DynamicActor body = (DynamicActor)iterator.next();
				if (worldWidth < body.getModel().getPosition().x)
					worldWidth = body.getModel().getPosition().x;
			}
			worldWidth++; //zwiekszam szerokosc o 1, tzn o szerokosc jednego klocka
		}
		return worldWidth;
	}

}
