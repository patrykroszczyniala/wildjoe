package pl.pepuch.wildjoe.core.world;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

import pl.pepuch.wildjoe.controller.Coin;
import pl.pepuch.wildjoe.controller.DynamicActor;
import pl.pepuch.wildjoe.controller.Player;

public class WorldContactListener implements ContactListener {
	
	private GameWorld world;
	
	public WorldContactListener(GameWorld world) {
		this.world = world;
	}

	@Override
	public void beginContact(Contact contact) {
		DynamicActor body1 = null;
		DynamicActor body2 = null; // NormalPlayer
		if (contact.getFixtureA().getBody().getUserData()!=null && contact.getFixtureA().getBody().getUserData() instanceof DynamicActor)
			body1 = (DynamicActor)contact.getFixtureA().getBody().getUserData();
		if (contact.getFixtureB().getBody().getUserData()!=null && contact.getFixtureB().getBody().getUserData() instanceof DynamicActor)
			body2 = (DynamicActor)contact.getFixtureB().getBody().getUserData();
		if (body1 instanceof Player && body2 instanceof Coin) {
			// punkt!
			world.gameBodyListToRemove.add(body2);
			world.pointCounter.setPoints(world.pointCounter.getPoints()+10);
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
