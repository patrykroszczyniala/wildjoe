package pl.pepuch.wildjoe.core;

import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.Game;
import playn.core.PlayN;
import playn.core.ResourceCallback;

public class WildJoe implements Game {

	public static float physUnitPerScreenUnit = 1/32f;
	public static boolean debug = false;
	private GameWorld world;
	
	@Override
	public void init() {
		pl.pepuch.wildjoe.core.world.Map.load(1,
				new ResourceCallback<GameWorld>() {
					@Override
					public void error(Throwable err) {
						err.printStackTrace();
					}
	
					@Override
					public void done(GameWorld resource) {
						world = resource;
						PlayN.keyboard().setListener(new WildJoeKeyboardListener(world));
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
