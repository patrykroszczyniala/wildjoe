package pl.pepuch.wildjoe.core.world;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import pl.pepuch.wildjoe.core.body.GameBody;
import pl.pepuch.wildjoe.core.body.NormalPlayer;
import pl.pepuch.wildjoe.core.prize.Coin;

public class WorldContactListener implements ContactListener {
	
	private GameWorld world;
	
	public WorldContactListener(GameWorld world) {
		this.world = world;
	}

	@Override
	public void beginContact(Contact contact) {
		GameBody body1 = null;
		GameBody body2 = null; // NormalPlayer
		if (contact.getFixtureA().getBody().getUserData()!=null)
			body1 = (GameBody)contact.getFixtureA().getBody().getUserData();
		if (contact.getFixtureB().getBody().getUserData()!=null)
			body2 = (GameBody)contact.getFixtureB().getBody().getUserData();
		if (body1 instanceof NormalPlayer && body2 instanceof Coin) {
			// punkt!
			world.gameBodyListToRemove.add(body2);
			world.pointCounter.setPoints(world.pointCounter.getPoints()+10);
		}
//		System.out.println(body2);
	}

	@Override
	public void endContact(Contact contact) {
		GameBody body1 = null;
		GameBody body2 = null;
		if (contact.getFixtureA().getBody().getUserData()!=null)
			body1 = (GameBody)contact.getFixtureA().getBody().getUserData();
		if (contact.getFixtureB().getBody().getUserData()!=null)
			body2 = (GameBody)contact.getFixtureA().getBody().getUserData();
		if (body1 instanceof NormalPlayer && body2 instanceof Coin) {
			System.out.println("MONETA STOP!");
		}
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
