package pl.pepuch.wildjoe.core.world;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.body.Enemy1Player;
import pl.pepuch.wildjoe.core.body.GameBody;
import pl.pepuch.wildjoe.core.body.NormalBlock;
import pl.pepuch.wildjoe.core.body.NormalPlayer;
import pl.pepuch.wildjoe.core.body.WallBlock;
import pl.pepuch.wildjoe.core.body.WaterBlock;
import pl.pepuch.wildjoe.core.prize.Coin;
import playn.core.AssetWatcher;
import playn.core.Json;
import playn.core.PlayN;
import playn.core.ResourceCallback;

public class Map {
	
	public static final String BLOCK_NORMAL = "NormalBlock";
	public static final String BLOCK_WATER = "WaterBlock";
	public static final String PLAYER_NORMAL = "NormalPlayer";
	public static final String ENEMY_1 = "Enemy1Player";
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
					float a = jsonEntity.getNumber("a");
					GameBody block = null;
					if (type.equalsIgnoreCase(Map.BLOCK_NORMAL)) {
						block = new NormalBlock(new Vec2(x, y));
					}
					else if (type.equalsIgnoreCase(Map.BLOCK_WATER)) {
						block = new WaterBlock(new Vec2(x, y));
					}
					else if (type.equalsIgnoreCase(Map.PRIZE_COIN)) {
						block = new Coin(new Vec2(x, y));
					}
					else if (type.equalsIgnoreCase(Map.PLAYER_NORMAL)) {
						if (x==0.0f)
							x = 0.01f;
						gameWorld.player = new NormalPlayer(new Vec2(x, y));
						gameWorld.player.setBody(gameWorld.world.createBody(gameWorld.player.getBodyDef()));
						gameWorld.player.getBody().createFixture(gameWorld.player.getFixtureDef());
						gameWorld.player.getBody().setTransform(gameWorld.player.getPosition(), 0);
						gameWorld.player.getBody().setLinearDamping(0.2f);
						gameWorld.player.getBody().setUserData(gameWorld.player);
					}
					else if (type.equalsIgnoreCase(Map.ENEMY_1)) {
						block = new Enemy1Player(new Vec2(x, y));
					}
					if (block!=null)
						gameWorld.add(block);
				}
				// left and right wall
				gameWorld.add(new WallBlock(0.001f, PlayN.graphics().height()*GameWorld.physUnitPerScreenUnit(), new Vec2(0.0f, 0.0f)));
				gameWorld.add(new WallBlock(0.001f, PlayN.graphics().height()*GameWorld.physUnitPerScreenUnit(), new Vec2(gameWorld.getWorldWidth(), 0.0f)));
			
				assetWatcher.start();
			}
		});
	}
	
}
