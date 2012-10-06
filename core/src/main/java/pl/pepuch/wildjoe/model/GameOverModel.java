package pl.pepuch.wildjoe.model;

import pl.pepuch.wildjoe.controller.Scoreboard;
import pl.pepuch.wildjoe.helpers.Math;

public class GameOverModel extends StaticModel {
	
	private Scoreboard scoreboard;
	private int bonus;
	
	public GameOverModel(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
		this.bonus = (int) ((Math.factorial(scoreboard.lives()) * 100) + (scoreboard.level()*10));
	}
	
	public Scoreboard scoreboard() {
		return scoreboard;
	}
	
	public void destroy() {
		super.destroy();
		scoreboard.destroy();
	}

	public int bonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
}
