package pl.pepuch.wildjoe.core.world;

import java.util.Iterator;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.controller.Background;
import pl.pepuch.wildjoe.controller.Block;
import pl.pepuch.wildjoe.controller.Coin;
import pl.pepuch.wildjoe.controller.DynamicActor;
import pl.pepuch.wildjoe.controller.Mummy;
import pl.pepuch.wildjoe.controller.Player;
import pl.pepuch.wildjoe.controller.Wall;
import playn.core.AssetWatcher;
import playn.core.Json;
import playn.core.PlayN;
import playn.core.ResourceCallback;

public class Map {
	
	public static final String BLOCK_NORMAL = "NormalBlock";
	public static final String PLAYER_NORMAL = "NormalPlayer";
	public static final String MUMMY = "Mummy";
	public static final String PRIZE_COIN = "PrizeCoin";
	
	public static void load(int level, final ResourceCallback<GameWorld> callback) {
		
		final GameWorld gameWorld = new GameWorld();

		PlayN.assets().getText("maps/level"+level+".json", new ResourceCallback<String>() {
			
			@Override
			public void error(Throwable err) {
				callback.error(err);				
			}
			
			@Override
			public void done(String resource) {
				AssetWatcher assetWatcher = new AssetWatcher(new AssetWatcher.Listener() {
					
					@Override
					public void error(Throwable e) {
						callback.error(e);
					}
					
					@Override
					public void done() {
						callback.done(gameWorld);
					}
				});
			
				Json.Object json = PlayN.json().parse(resource);
				Json.Array entities = json.getArray("Entities");
				for (int i=0; i<entities.length(); i++) {
					Json.Object jsonEntity = entities.getObject(i);
					String type = jsonEntity.getString("type");
					float x = jsonEntity.getNumber("x");
					float y = jsonEntity.getNumber("y");

					DynamicActor block = null;
					if (type.equalsIgnoreCase(Map.BLOCK_NORMAL)) {
						block = new Block(gameWorld, new Vec2(x, y));
					}
					else if (type.equalsIgnoreCase(Map.PRIZE_COIN)) {
						block = new Coin(gameWorld, new Vec2(x, y));
					}
					else if (type.equalsIgnoreCase(Map.MUMMY)) {
						block = new Mummy(gameWorld, new Vec2(x, y));
					}
					else if (type.equalsIgnoreCase(Map.PLAYER_NORMAL)) {
						Player player = new Player(gameWorld, new Vec2(1.0f, 0.0f));
						gameWorld.setPlayer(player);
						PlayN.graphics().rootLayer().add(player.view().getLayer());
					}
					if (block!=null) {
						gameWorld.add(block);
					}
				}
				// add background
				gameWorld.background = new Background(gameWorld, new Vec2(0.0f, 0.0f)); 
				PlayN.graphics().rootLayer().add(gameWorld.background.view().getLayer());
				// left and right wall
				Wall wallFirst = new Wall(gameWorld, new Vec2(-2.0f, -2.0f));
				gameWorld.add(wallFirst);
				// set world start position
				gameWorld.setArenaPosition(wallFirst.model().getPosition());
				gameWorld.add(new Wall(gameWorld, new Vec2(gameWorld.getWorldWidth(), -2.0f)));
				// add blocks before start wall
				for (int i=1; i<10; i++) {
					gameWorld.add(new Block(gameWorld, new Vec2(i*(-1), gameWorld.getWorldHeight()-1)));
				}
				// add blocks after end wall
				for (int i=0; i<10; i++) {
					gameWorld.add(new Block(gameWorld, new Vec2(gameWorld.getWorldWidth()+i, gameWorld.getWorldHeight()-1)));
				}
				
				// find boundary blocks
				for (Iterator<DynamicActor> iterator = gameWorld.getGameBodyList().iterator(); iterator.hasNext();) {
					DynamicActor body = (DynamicActor)iterator.next();
					if (body instanceof Block) {
						Block block = (Block)body;
						Vec2 blockPos = block.model().getOrigin();
						// find block neighbors
						boolean hasLeftNeighbor = false;
						boolean hasRightNeighbor = false;
						for (Iterator<DynamicActor> subIterator = gameWorld.getGameBodyList().iterator(); subIterator.hasNext();) {
							DynamicActor bodyToCheck = (DynamicActor)subIterator.next();
							if (bodyToCheck instanceof Block) {
								Block blockToCheck = (Block)bodyToCheck;
								Vec2 blockToCheckPos = blockToCheck.model().getOrigin();
								if (blockPos.y==blockToCheckPos.y) {
									if ((blockPos.x+1)==blockToCheckPos.x) {
										hasRightNeighbor = true;
									}
									else if ((blockPos.x-1)==blockToCheckPos.x) {
										hasLeftNeighbor = true;
									}
								}
							}
						}
						
						if (!hasLeftNeighbor || !hasRightNeighbor) {
							block.model().setBoundary(true);
						}
					}
				}
				assetWatcher.start();
			}
		});
	}
	
}
