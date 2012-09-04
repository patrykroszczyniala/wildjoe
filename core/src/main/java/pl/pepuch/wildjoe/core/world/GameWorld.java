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
import playn.core.ImageLayer;
import playn.core.PlayN;

public class GameWorld {
	
	// pozwala debugowac Box2d
	public DebugDrawBox2D debugDraw;
	// swiat Box2d
	private World world;
	// lista dodanych cial
	List<DynamicActor> gameBodyList;
	
	// pozycja areny
	private Vec2 arenaPosition;
	
	private Background background;
	private float worldWidth;
	public WildJoe game;
	private Player player;
	private PointCounter pointCounter;

	public GameWorld(WildJoe game) {
		this.game = game;
		gameBodyList = new ArrayList<DynamicActor>();
		Vec2 gravity = new Vec2(0.0f, 10.0f);
		boolean doSleep = true;
	    world = new World(gravity, doSleep);
		if (WildJoe.debug) {
	    	this.enableDebug();
	    }

		// contact listener
		world.setContactListener(null);
		// tablica wynikow
		pointCounter = new PointCounter();
		PlayN.graphics().rootLayer().addAt(pointCounter.getLayer(), 10, 10);
		pointCounter.setVisible(false);
		// zawodnik
		player = new Player(this, new Vec2(1.0f, 0.0f));
		PlayN.graphics().rootLayer().add(player.view().getLayer());
		player.setVisible(false);
		// background
        background = new Background(this, new Vec2(0.0f, 0.0f));
        PlayN.graphics().rootLayer().add(background.view().getLayer());
        background.setVisible(false);
		// arena initial position
		setArenaPosition(new Vec2(0.0f, 0.0f));
	}
	
	public void init() {
		player.setVisible(true);
		background.setVisible(true);
		pointCounter.setVisible(true);
		// set world contact listener
		world.setContactListener(new WorldContactListener(this));
		// reset world width
		worldWidth = 0.0f;
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
		world.step(0.033f, 10, 10);
		
		float leftEnd = getScreenWidth()*0.33f-player.model().getWidth();
		float rightEnd = getScreenWidth()*0.66f;
		if (player.model().getPosition().x<leftEnd && player.isMovingLeft()) {
			background.moveLeft();
			for (Iterator<DynamicActor> actor = gameBodyList.iterator(); actor.hasNext();) {
				DynamicActor body = actor.next();
				float x = body.model().getPosition().x+player.model().getSpeed();
				float y = body.model().getPosition().y;
				body.model().setPosition(new Vec2(x, y));
			}
		}
		else if (player.model().getPosition().x>rightEnd && player.isMovingRight()) {
			background.moveRight();
			for (Iterator<DynamicActor> actor = gameBodyList.iterator(); actor.hasNext();) {
				DynamicActor body = actor.next();
				float x = body.model().getPosition().x-player.model().getSpeed();
				float y = body.model().getPosition().y;
				body.model().setPosition(new Vec2(x, y));
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
		
		// update bodies
		for (Iterator<DynamicActor> actor = gameBodyList.iterator(); actor.hasNext();) {
			DynamicActor body = actor.next();
			body.update(delta);
		}
		player.update(delta);
		pointCounter.getIface().update(delta);
		
		
	}
	
	private void enableDebug() {
		CanvasImage image = graphics().createImage((int) (getScreenWidth() / WildJoe.physUnitPerScreenUnit), 
				(int) (getScreenHeight() / WildJoe.physUnitPerScreenUnit));
		ImageLayer layer = graphics().createImageLayer(image);
		layer.setDepth(10);
		PlayN.graphics().rootLayer().add(layer);
		debugDraw = new DebugDrawBox2D();
		debugDraw.setCanvas(image);
		debugDraw.setFlipY(false);
		debugDraw.setStrokeAlpha(150);
		debugDraw.setFillAlpha(75);
		debugDraw.setStrokeWidth(2.0f);
		debugDraw.setFlags(DebugDraw.e_shapeBit | DebugDraw.e_jointBit | DebugDraw.e_aabbBit);
		debugDraw.setCamera(0, 0, 1f / WildJoe.physUnitPerScreenUnit);
		world.setDebugDraw(debugDraw);
	}
	
	public void add(final DynamicActor gameBody) {
//		PlayN.invokeLater(new Runnable() {
//			@Override
//			public void run() {
				gameBodyList.add(gameBody);
				PlayN.graphics().rootLayer().add(gameBody.view().getLayer());
				gameBody.model().getBody().setUserData(gameBody);
//			}
//		});
	}
	
	public void remove(final DynamicActor gameBody) {
		PlayN.invokeLater(new Runnable() {
			@Override
			public void run() {
				gameBodyList.remove(gameBody);
				gameBody.destroy();
			}
		});
	}
	
	public void setArenaPosition(Vec2 arenaPosition) {
		this.arenaPosition = arenaPosition;
	}
	
	public float getArenaPositionX() {
		return arenaPosition.x+2.0f; // TODO +2.0f powinno byc z automatu a nie z reki wpisane!
	}
	
	public float getScreenWidth() {
		return graphics().width()*WildJoe.physUnitPerScreenUnit;
	}
	
	public float getScreenHeight() {
		return graphics().height()*WildJoe.physUnitPerScreenUnit;
	}
	
	public float getWorldHeight() {
		return getScreenHeight();
	}
	
	public float getWorldWidth() {
		if (worldWidth==0.0f) {
			for (Iterator<DynamicActor> iterator = gameBodyList.iterator(); iterator.hasNext();) {
				DynamicActor body = (DynamicActor)iterator.next();
				if (worldWidth < body.model().getPosition().x)
					worldWidth = body.model().getPosition().x;
			}
			worldWidth++; //zwiekszam szerokosc o 1, tzn o szerokosc jednego klocka
		}
		return worldWidth;
	}
	
	public List<DynamicActor> getGameBodyList() {
		return gameBodyList;
	}
	
	public void gameOver() {
		player.die();
		pointCounter.destroy();
		background.destroy();
		for (Iterator<DynamicActor> actor = gameBodyList.iterator(); actor.hasNext();) {
			DynamicActor body = actor.next();
			body.destroy();
		}
		gameBodyList.clear();
		world.setContactListener(null);
		game.menu().show();
		game.gameStarted = false;
		game.level = 1;
	}
	
	public void clear() {
		player.setVisible(false);
		background.setVisible(false);
		pointCounter.setVisible(false);
		for (Iterator<DynamicActor> actor = gameBodyList.iterator(); actor.hasNext();) {
			DynamicActor body = actor.next();
			body.destroy();
		}
		gameBodyList.clear();
		world.setContactListener(null);
	}
	
	public Player player() {
		return player;
	}
	
	public PointCounter pointCounter() {
		return pointCounter;
	}
	
	public World world() {
	    return world;
	}
	
}
