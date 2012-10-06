package pl.pepuch.wildjoe.model.callbacks;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.dynamics.Fixture;

import pl.pepuch.wildjoe.controller.DynamicActor;
import pl.pepuch.wildjoe.controller.Player;
import pl.pepuch.wildjoe.controller.Spider;

public class SpiderQueryCallback implements QueryCallback {
	
	private Spider spider;

	@Override
	public boolean reportFixture(Fixture fixture) {
		if (fixture.getBody().getUserData()!=null && fixture.getBody().getUserData() instanceof Spider) {
			this.spider = (Spider)fixture.getBody().getUserData();
		}
		if (fixture.getBody().getUserData()!=null && fixture.getBody().getUserData() instanceof DynamicActor) {
			DynamicActor player = (DynamicActor)fixture.getBody().getUserData();
			if (player instanceof Player && spider!=null) {
				if (player.model().position().x<spider.model().position().x) {
					spider.turnLeft();
				}
				if (player.model().position().x>spider.model().position().x) {
					spider.turnRight();
				}
			}
		}
		
		return true;
	}

}
