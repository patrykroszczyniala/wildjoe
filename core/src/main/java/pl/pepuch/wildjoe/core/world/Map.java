package pl.pepuch.wildjoe.core.world;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.controller.ActorFactory;
import pl.pepuch.wildjoe.controller.Block;
import pl.pepuch.wildjoe.controller.Coin;
import pl.pepuch.wildjoe.controller.Diamond;
import pl.pepuch.wildjoe.controller.DynamicActor;
import pl.pepuch.wildjoe.controller.FinishFlag;
import pl.pepuch.wildjoe.controller.Mummy;
import pl.pepuch.wildjoe.controller.Wall;
import playn.core.Json;
import playn.core.PlayN;
import playn.core.ResourceCallback;

public class Map {
	
	public static void load(final GameWorld gameWorld, int level, final ResourceCallback<GameWorld> callback) {
		
		PlayN.assets().getText("maps/level"+level+".json", new ResourceCallback<String>() {
			
			@Override
			public void error(Throwable err) {
				callback.error(err);
			}
			
			@Override
			public void done(String resource) {
				Json.Object json = PlayN.json().parse(resource);
				Json.Array entities = json.getArray("Entities");
				
				for (int i=0; i<entities.length(); i++) {
					Json.Object jsonEntity = entities.getObject(i);
					String type = jsonEntity.getString("type");
					
					DynamicActor block = null;
					float x = jsonEntity.getNumber("x");
					float y = jsonEntity.getNumber("y");
					float width = jsonEntity.getNumber("width")==0 ? 1:jsonEntity.getNumber("width");
					float height = jsonEntity.getNumber("height")==0 ? 1:jsonEntity.getNumber("height");
					if (type.equalsIgnoreCase(ActorFactory.BLOCK)) {
						block = new Block(gameWorld, new Vec2(x, gameWorld.getWorldHeight()-y), width, height);
					}
					else {
						if (type.equalsIgnoreCase(ActorFactory.COIN)) {
							block = new Coin(gameWorld, new Vec2(x, gameWorld.getWorldHeight()-1-y));
						}
						else if (type.equalsIgnoreCase(ActorFactory.DIAMOND)) {
							block = new Diamond(gameWorld, new Vec2(x, gameWorld.getWorldHeight()-1-y));
						}
						else if (type.equalsIgnoreCase(ActorFactory.FINISH_FLAG)) {
							block = new FinishFlag(gameWorld, new Vec2(x, gameWorld.getWorldHeight()-1-y));
						}
						else if (type.equalsIgnoreCase(ActorFactory.MUMMY)) {
							block = new Mummy(gameWorld, new Vec2(x, gameWorld.getWorldHeight()-1-y));
						}
						else if (type.equalsIgnoreCase(ActorFactory.PLAYER)) {
							gameWorld.player().model().setPosition(new Vec2(x, y));
						}
					}
					if (block!=null) {
						gameWorld.add(block);
					}
				}
				
				// left and right wall
				Wall wallFirst = new Wall(gameWorld, new Vec2(0.0f, 0.0f));
				gameWorld.add(wallFirst);
				// set world start position
				gameWorld.setArenaPosition(wallFirst.model().position());
				gameWorld.add(new Wall(gameWorld, new Vec2(gameWorld.getWorldWidth()-2f, 0.0f)));
				
				callback.done(gameWorld);
			}
		});
	}
	
}
