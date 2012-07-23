package pl.pepuch.wildjoe.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Key;
import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.TypedEvent;
import playn.core.PlayN;
import playn.core.ResourceCallback;

public class WildJoe implements Game {

	public static boolean debug = false;
	private GameWorld world;
	
	@Override
	public void init() {
		// create and add background image layer
		// Layer -2
		Image bgImage = assets().getImage("images/bg.png");
		ImageLayer bgLayer = graphics().createImageLayer(bgImage);
		graphics().rootLayer().add(bgLayer);
		
		pl.pepuch.wildjoe.core.world.Map.load(1,
				new ResourceCallback<GameWorld>() {
					@Override
					public void error(Throwable err) {
						err.printStackTrace();
					}

					@Override
					public void done(GameWorld resource) {
						world = resource;
					}
				});
		PlayN.keyboard().setListener(new Keyboard.Listener() {
			
			@Override
			public void onKeyUp(Event event) {
				if (event.key().name().equalsIgnoreCase(Key.RIGHT.name())) {
					world.player.isMovingRight(false);
				}
				else if (event.key().name().equalsIgnoreCase(Key.LEFT.name())) {
					world.player.isMovingLeft(false);
				}
				else if (event.key().name().equalsIgnoreCase(Key.UP.name())) {
				}
			}
			
			@Override
			public void onKeyTyped(TypedEvent event) {
			}
			
			@Override
			public void onKeyDown(Event event) {
				if (event.key().name().equalsIgnoreCase(Key.RIGHT.name())) {
					world.player.isMovingRight(true);
				}
				else if (event.key().name().equalsIgnoreCase(Key.LEFT.name())) {
					world.player.isMovingLeft(true);
				}
				else if (event.key().name().equalsIgnoreCase(Key.SPACE.name())) {
					world.player.jump();
				}
			}
		});
	}

	@Override
	public void paint(float alpha) {
		 world.paint(alpha);
	}

	@Override
	public void update(float delta) {
		 world.update(delta);
	}

	@Override
	public int updateRate() {
		return 25;
	}
}
