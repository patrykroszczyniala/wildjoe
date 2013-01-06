package pl.pepuch.wildjoe.core;

import pl.pepuch.wildjoe.controller.Player;
import pl.pepuch.wildjoe.core.world.GameWorld;
import playn.core.Key;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.Listener;
import playn.core.Keyboard.TypedEvent;
import playn.core.PlayN;

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
            player.turnRight();
            player.go();
        } else if (event.key().name().equalsIgnoreCase(Key.LEFT.name())) {
            player.turnLeft();
            player.go();
        }
        if (event.key().name().equalsIgnoreCase(Key.UP.name())) {
            player.jump();
        }
        if (event.key().name().equalsIgnoreCase(Key.SPACE.name())) {
            player.shoot();
        }
    }

    @Override
    public void onKeyTyped(TypedEvent event) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onKeyUp(Event event) {
        if (event.key().name().equalsIgnoreCase(Key.RIGHT.name()) || event.key().name().equalsIgnoreCase(Key.LEFT.name())) {
            player.stop();
        }
    }
}
