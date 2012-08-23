package pl.pepuch.wildjoe.core;

import java.io.FileNotFoundException;

import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.Game;
import playn.core.PlayN;
import playn.core.ResourceCallback;
import react.UnitSlot;

public class WildJoe implements Game {

	public static float physUnitPerScreenUnit = 1/32f;
	public static boolean debug = false;
	private GameWorld gameWorld;
	private Menu menu;
	int level = 1;
	public boolean gameStarted;
	
	public WildJoe() {
		menu = new Menu();
	}
	
	@Override
	/**
	 * ekran powitalny z menu wywolywany po wlaczeniu gry albo po gameover
     * POWINNO byc wywolywane tylko raz (tak jak jest napisane w klasie Game)
	 */
	public void init() {
		level = 1;
		gameWorld = new GameWorld(this);
		gameStarted = false;
		gameWorld.clear();
		menu.show();
		
		// menu buttons support
		menu.exit().clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				// TODO destroy!!
				System.exit(0);
			}
		});
		menu.start().clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				gameWorld.clear();
				loadLevel(level);
			}
		});
	}
	
	public void loadLevel(int level) {
		pl.pepuch.wildjoe.core.world.Map.load(gameWorld, level,
			new ResourceCallback<GameWorld>() {
				@Override
				public void error(Throwable err) {
//					err.printStackTrace();
					if (err instanceof FileNotFoundException) {
						gameWorld.gameOver();
					}
				}
	
				@Override
				public void done(GameWorld resource) {
					menu.hide();
					PlayN.keyboard().setListener(new WildJoeKeyboardListener(gameWorld));
					gameWorld.init();
					if (!gameStarted) {
						gameStarted = true;
					}
					gameWorld.loadNextLevel(false);
				}
			}
		);
	}
	
	public synchronized void nextLevel() {
		level++;
		PlayN.keyboard().setListener(null);
		gameWorld.clear();
		loadLevel(level);
	}

	@Override
	public void paint(float alpha) {
		if (gameStarted) {
			gameWorld.paint(alpha);
		}
		else {
			menu.paint(alpha);
		}
	}

	@Override
	public void update(float delta) {
		if (gameStarted) {
			gameWorld.update(delta);
		}
		else {
			menu.update(delta);
		}
	}

	@Override
	public int updateRate() {
		return 25;
	}
	
}
