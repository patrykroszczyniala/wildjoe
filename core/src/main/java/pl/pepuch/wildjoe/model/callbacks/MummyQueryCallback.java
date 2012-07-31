package pl.pepuch.wildjoe.model.callbacks;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.dynamics.Fixture;

import pl.pepuch.wildjoe.controller.DynamicActor;
import pl.pepuch.wildjoe.controller.Mummy;
import pl.pepuch.wildjoe.controller.Player;

public class MummyQueryCallback implements QueryCallback {
	
	private Mummy mummy;

	@Override
	public boolean reportFixture(Fixture fixture) {
		if (fixture.getBody().getUserData()!=null && fixture.getBody().getUserData() instanceof Mummy) {
			this.mummy = (Mummy)fixture.getBody().getUserData();
		}
		if (fixture.getBody().getUserData()!=null && fixture.getBody().getUserData() instanceof DynamicActor) {
			DynamicActor body = (DynamicActor)fixture.getBody().getUserData();
			if (body instanceof Player && mummy!=null) {
				mummy.shoot();
			}
		}
		
		return true;
	}

}
