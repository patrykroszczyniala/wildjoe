package pl.pepuch.wildjoe.core;

import pl.pepuch.wildjoe.controller.Player;
import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.Key;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.Listener;
import playn.core.Keyboard.TypedEvent;

public class WildJoeKeyboardListener implements Listener {
	
	private GameWorld gameWorld;
	private Player player;
	
	public WildJoeKeyboardListener(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		player = this.gameWorld.player();
	}
	
	@Override
	public void onKeyDown(Event event) {
		if (event.key().name().equalsIgnoreCase(Key.RIGHT.name())) {
			player.isMovingRight(true);
		}
		else if (event.key().name().equalsIgnoreCase(Key.LEFT.name())) {
			player.isMovingLeft(true);
		}
		else if (event.key().name().equalsIgnoreCase(Key.UP.name())) {
			player.jump();
		}
		else if (event.key().name().equalsIgnoreCase(Key.SPACE.name())) {
			player.shoot();
		}
	}

	@Override
	public void onKeyTyped(TypedEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onKeyUp(Event event) {
		if (event.key().name().equalsIgnoreCase(Key.RIGHT.name())) {
			player.isMovingRight(false);
		}
		else if (event.key().name().equalsIgnoreCase(Key.LEFT.name())) {
			player.isMovingLeft(false);
		}
	}

}
