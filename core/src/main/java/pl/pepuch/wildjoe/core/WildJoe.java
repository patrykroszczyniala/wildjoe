package pl.pepuch.wildjoe.core;

import java.io.FileNotFoundException;

import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.ResourceCallback;
import react.UnitSlot;

public class WildJoe implements Game {

	public static float physUnitPerScreenUnit = 1/32f;
	public static boolean debug = false;
	private GameWorld gameWorld;
	private Menu menu;
	public int level = 1;
	public boolean gameStarted = false;
//	Loader loader;
	
	@Override
	/**
	 * ekran powitalny z menu wywolywany po wlaczeniu gry albo po gameover
     * POWINNO byc wywolywane tylko raz (tak jak jest napisane w klasie Game)
	 */
	public void init() {
//		loader = new Loader();
		menu = new Menu();
		gameStarted = false;
		menu.show();
		
		// menu buttons support
		menu.exit().clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				// TODO destroy!!
//				System.exit(0); // doesn't work for GWT
			}
		});
		final WildJoe game = this;
		menu.start().clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				menu.hide();
				gameWorld = new GameWorld(game);
				loadLevel(level);
			}
		});
	}
	
	public void loadLevel(final int level) {
		gameWorld.clear();
//		loader.start();
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
				PlayN.invokeLater(new Runnable() {

					@Override
					public void run() {
						pl.pepuch.wildjoe.core.world.Map.load(gameWorld, level,
							new ResourceCallback<GameWorld>() {
								@Override
								public void error(Throwable err) {
//									if (err instanceof FileNotFoundException) {
										gameWorld.gameOver();
//									}
//									loader.stop();
								}
					
								@Override
								public void done(GameWorld resource) {
									menu.hide();
									PlayN.keyboard().setListener(new WildJoeKeyboardListener(gameWorld));
									gameWorld.init();
									if (!gameStarted) {
										gameStarted = true;
									}
//									loader.stop();
								}
							}
						);
					}
					
				});
//			}
//		}).start();
	}
	
	public void nextLevel() {
		level++;
		PlayN.keyboard().setListener(null);
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
//		if (loader!=null && loader.isRunning()) {
//			System.out.println("LOADER_UPDATE");
//			loader.update(delta);
//		}
			
		if (gameStarted) {
			gameWorld.update(delta);
		}
//		else if (loader.isRunning()) {
//			System.out.println("LOADER_UPDATE");
//			loader.update(delta);
//		}
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
	
}
