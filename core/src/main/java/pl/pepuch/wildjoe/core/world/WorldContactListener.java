package pl.pepuch.wildjoe.core.world;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

import pl.pepuch.wildjoe.controller.Block;
import pl.pepuch.wildjoe.controller.Cartridge;
import pl.pepuch.wildjoe.controller.Coin;
import pl.pepuch.wildjoe.controller.DynamicActor;
import pl.pepuch.wildjoe.controller.Mummy;
import pl.pepuch.wildjoe.controller.Player;
import pl.pepuch.wildjoe.model.BlockModel;

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
		if (body1 instanceof Player && body2 instanceof Coin) {
			// punkt!
			gameWorld.gameBodyListToRemove.add(body2);
			gameWorld.pointCounter.setPoints(gameWorld.pointCounter.getPoints()+10);
		}
		// --------------------------------
		// KILL PLAYER AND DESTROY CARTRIDGE
		if (body1 instanceof Player && body2 instanceof Cartridge) {
			gameWorld.gameBodyListToRemove.add(body1);
			gameWorld.gameBodyListToRemove.add(body2);
		}
		else if (body1 instanceof Cartridge && body2 instanceof Player) {
			gameWorld.gameBodyListToRemove.add(body2);
			gameWorld.gameBodyListToRemove.add(body1);
		}
		// DESTROY CARTRIDGES
		if (body1 instanceof Cartridge && (!(body2 instanceof Mummy)  && !(body2 instanceof Cartridge))) {
			gameWorld.gameBodyListToRemove.add(body1);
		}
		if (body2 instanceof Cartridge && (!(body1 instanceof Mummy) && !(body1 instanceof Cartridge))) {
			gameWorld.gameBodyListToRemove.add(body2);
		}
		// MYMMY AI ;)
		if (body1 instanceof Mummy && body2 instanceof Block) {
			BlockModel blockModel = (BlockModel)body2.model();
			Mummy mummy = (Mummy)body1;
			if (blockModel.isBoundary()) {
				if (mummy.isMovingLeft()) {
					mummy.isMovingRight(true);
				}
				else if (mummy.isMovingRight()) {
					mummy.isMovingLeft(true);
				}
			}
		}
		if (body1 instanceof Block && body2 instanceof Mummy) {
			BlockModel blockModel = (BlockModel)body1.model();
			Mummy mummy = (Mummy)body2;
			if (blockModel.isBoundary()) {
				if (mummy.isMovingLeft()) {
					mummy.isMovingRight(true);
				}
				else if (mummy.isMovingRight()) {
					mummy.isMovingLeft(true);
				}
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
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
