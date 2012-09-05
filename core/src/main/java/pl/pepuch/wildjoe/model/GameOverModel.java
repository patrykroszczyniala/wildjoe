package pl.pepuch.wildjoe.model;

import pl.pepuch.wildjoe.core.world.PointCounter;


public class GameOverModel extends StaticModel {
	
	private PointCounter pointCounter;
	
	public GameOverModel(PointCounter pointCounter) {
		this.pointCounter = pointCounter;
	}
	
	public PointCounter pointCounter() {
		return pointCounter;
	}
	
	public void destroy() {
		super.destroy();
		pointCounter.destroy();
	}
	
}
