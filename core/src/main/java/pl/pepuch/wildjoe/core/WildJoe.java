package pl.pepuch.wildjoe.core;

import pl.pepuch.wildjoe.controller.Loader;
import pl.pepuch.wildjoe.controller.Menu;
import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.Game;
import playn.core.PlayN;
import playn.core.ResourceCallback;

public class WildJoe implements Game {

	public static float physUnitPerScreenUnit = 1/32f;
	public static boolean debug = false;
	private GameWorld gameWorld;
	private Menu menu;
	
	@Override
	/**
	 * ekran powitalny z menu wywolywany po wlaczeniu gry albo po gameover
	 */
	public void init() {
		PlayN.graphics().setSize(800, 600);
		menu = new Menu(this);
		menu.show();
	}
	
	public void loadLevel(final int level) {
		gameWorld.clear();
		final Loader loader = new Loader();
		loader.start();

		PlayN.invokeLater(new Runnable() {

			@Override
			public void run() {
				pl.pepuch.wildjoe.core.world.Map.load(gameWorld, level,
					new ResourceCallback<GameWorld>() {
						@Override
						public void error(Throwable err) {
//							if (err instanceof FileNotFoundException) {
								gameWorld.gameOver();
//							}
							loader.stop();
						}
			
						@Override
						public void done(GameWorld resource) {
							gameWorld.init();
							loader.stop();
						}
					}
				);
			}
			
		});

	}
	
	public void nextLevel() {
		gameWorld().scoreboard().setLevel(gameWorld().scoreboard().level() + 1);
		loadLevel(gameWorld().scoreboard().level());
	}
	
	public void restartLevel() {
		gameWorld().scoreboard().setLives(gameWorld().scoreboard().lives()-1);
		loadLevel(gameWorld().scoreboard().level());
	}

	@Override
	public void paint(float alpha) {
		if (gameWorld!=null) {
			if (gameWorld.gameOver!=null) {
				gameWorld.gameOver.paint(alpha);
			}
			else {
				gameWorld.paint(alpha);
			}
		}
		else {
			menu.paint(alpha);
		}
	}

	@Override
	public void update(float delta) {			
		if (gameWorld!=null) {
			if (gameWorld!=null && gameWorld.gameOver!=null) {
				gameWorld.gameOver.update(delta);
			}
			else {
				gameWorld.update(delta);
			}
		}
		else {
			menu.update(delta);
		}
	}

	@Override
	public int updateRate() {
		return 25;
	}
	
	public Menu menu() {
		return menu;
	}
	
	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}
	
	public GameWorld gameWorld() {
		return gameWorld;
	}
		
}
