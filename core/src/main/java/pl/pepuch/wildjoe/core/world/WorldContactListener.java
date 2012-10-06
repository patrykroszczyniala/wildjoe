package pl.pepuch.wildjoe.core.world;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

import pl.pepuch.wildjoe.controller.Block;
import pl.pepuch.wildjoe.controller.Cartridge;
import pl.pepuch.wildjoe.controller.Coin;
import pl.pepuch.wildjoe.controller.Diamond;
import pl.pepuch.wildjoe.controller.DynamicActor;
import pl.pepuch.wildjoe.controller.FinishFlag;
import pl.pepuch.wildjoe.controller.Mummy;
import pl.pepuch.wildjoe.controller.Player;
import pl.pepuch.wildjoe.controller.Spider;
import pl.pepuch.wildjoe.helpers.AssetsFactory;
import playn.core.PlayN;
import playn.core.Sound;

public class WorldContactListener implements ContactListener {
	
	private GameWorld gameWorld;
	
	public WorldContactListener(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	@Override
	public void beginContact(Contact contact) {
		DynamicActor body1 = null;
		DynamicActor body2 = null;
		if (contact.getFixtureA().getBody().getUserData()!=null && contact.getFixtureA().getBody().getUserData() instanceof DynamicActor)
			body1 = (DynamicActor)contact.getFixtureA().getBody().getUserData();
		if (contact.getFixtureB().getBody().getUserData()!=null && contact.getFixtureB().getBody().getUserData() instanceof DynamicActor)
			body2 = (DynamicActor)contact.getFixtureB().getBody().getUserData();
		if ((body1 instanceof Player && body2 instanceof Coin) || (body1 instanceof Coin && body2 instanceof Player)) {
			// punkt!
			Coin coin = (body1 instanceof Coin) ? (Coin)body1 : (Coin)body2;
			Sound sound = AssetsFactory.getSound("sounds/point");
			sound.play();
			gameWorld.remove(coin);
			gameWorld.scoreboard().setPoints(gameWorld.scoreboard().model().points()+10);
		}
		if ((body1 instanceof Player && body2 instanceof Diamond) || (body1 instanceof Diamond && body2 instanceof Player)) {
			// punkt!
			Diamond diamond = (body1 instanceof Diamond) ? (Diamond)body1 : (Diamond)body2;
			Sound sound = AssetsFactory.getSound("sounds/point");
			sound.play();
			gameWorld.remove(diamond);
			gameWorld.scoreboard().setPoints(gameWorld.scoreboard().model().points()+50);
		}
		// MYMMY AI ;)
		if ((body1 instanceof Mummy && body2 instanceof Block) || (body1 instanceof Block && body2 instanceof Mummy)) {
			Mummy mummy =  (body1 instanceof Mummy) ? (Mummy)body1 : (Mummy)body2;
			Block block =  (body1 instanceof Block) ? (Block)body1 : (Block)body2;
			if (block.model().position().y>(mummy.model().position().y) && mummy.block()==null) {
				mummy.setBlock(block);
			}
		}

		// kill player if he touch enemy
		if (body1 instanceof Player || body2 instanceof Player) {
			if (body1 instanceof Mummy || body2 instanceof Mummy || body1 instanceof Spider || body2 instanceof Spider) {
				if (gameWorld.scoreboard().lives()<=1) {
					gameWorld.gameOver();
				}
				else {
					PlayN.invokeLater(new Runnable() {
						@Override
						public void run() {
							gameWorld.game.restartLevel();
						}
					});
				}
			}
		}
		// game finished or next level
		if ((body1 instanceof FinishFlag && body2 instanceof Player) || (body1 instanceof Player && body2 instanceof FinishFlag)) {
			PlayN.invokeLater(new Runnable() {
				@Override
				public void run() {
					gameWorld.game.nextLevel();
				}
			});
		}
		// destroy cartridge
		if (body1 instanceof Cartridge || body2 instanceof Cartridge) {
			Cartridge cartridge =  (body1 instanceof Cartridge) ? (Cartridge)body1 : (Cartridge)body2;
			if (!(body1 instanceof Player) && !(body2 instanceof Player)) {
				cartridge.destroy();
			}
			if (body1 instanceof Mummy || body2 instanceof Mummy) {
				Mummy mummy =  (body1 instanceof Mummy) ? (Mummy)body1 : (Mummy)body2;
				mummy.destroy();
			}
			if (body1 instanceof Spider || body2 instanceof Spider) {
				Spider spider =  (body1 instanceof Spider) ? (Spider)body1 : (Spider)body2;
				spider.destroy();
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	
}
