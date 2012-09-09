package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.model.ScoreboardModel;
import pl.pepuch.wildjoe.view.ScoreboardView;

public class Scoreboard extends StaticActor implements Cloneable {
	
	public Scoreboard() {
		model = new ScoreboardModel();
		view = new ScoreboardView(model());
	}
	
	public Scoreboard clone() {
		Scoreboard scoreboard = new Scoreboard();
		scoreboard.setPoints(model().points());
		scoreboard.setLevel(model().level());
		scoreboard.setLives(model().lives());
		
		return scoreboard;
	}
	
	public void setPoints(int points) {
		model().setPoints(points);
		view().points().text.update(String.valueOf(model().points()));
	}
	
	public void setLives(int lives) {
		model().setLives(lives);
		view().lives().text.update(String.valueOf(model().lives()));
	}
	
	public void setLevel(int level) {
		model().setLevel(level);
		view().level().text.update("Level "+String.valueOf(model().level()));
	}

	public int level() {
		return model().level();
	}
	
	public int lives() {
		return model().lives();
	}
	
	public int points() {
		return model().points();
	}
	
	@Override
	public ScoreboardView view() {
		return (ScoreboardView)view;
	}

	@Override
	public ScoreboardModel model() {
		return (ScoreboardModel)model;
	}
	
}
