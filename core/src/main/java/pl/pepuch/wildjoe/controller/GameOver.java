package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.GameOverModel;
import pl.pepuch.wildjoe.view.GameOverView;
import playn.core.PlayN;
import react.UnitSlot;


public class GameOver extends StaticActor {
	
	private int points;
	private int pointsCounter;
	private int bonusCounter;
	private boolean bonusAdded;
	
	public GameOver(final GameWorld gameWorld) {
		bonusAdded = false;
		model = new GameOverModel(gameWorld.scoreboard().clone());
		view = new GameOverView(model());
		view().layer().setDepth(5);
		PlayN.graphics().rootLayer().add(view().layer());
		hide();
		view().btnContinue().clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				hide();
				gameWorld.game().menu().show();
			}
		});
		points = model().scoreboard().model().points();
		pointsCounter = 0;
		bonusCounter = 0;
	}
	
	public void show() {
		view().layer().setVisible(true);
	}
	
	public void hide() {
		view().layer().setVisible(false);
	}

	@Override
	public GameOverView view() {
		return (GameOverView)view;
	}

	@Override
	public GameOverModel model() {
		return (GameOverModel)model;
	}
	
	public void destroy() {
		view().destroy();
		model().destroy();
	}
	
	public void paint(float alpha) {
		super.paint(alpha);
		model().scoreboard().paint(alpha);
	}
	
	public void update(float delta) {
		super.update(delta);
		if (pointsCounter<points) {
			if (points-pointsCounter>100) {
				pointsCounter += 100;
			}
			else if (points-pointsCounter>10) {
				pointsCounter += 10;
			}
			else {
				pointsCounter++;
			}
		}
		else if (bonusCounter<model().bonus()) {
			if (!view().bonusLabel().isVisible()) {
				view().bonusLabel().setVisible(true);
			}
			if (model().bonus()-bonusCounter>100) {
				bonusCounter += 100;
			}
			else if (model().bonus()-bonusCounter>10) {
				bonusCounter += 10;
			}
			else {
				bonusCounter++;
			}
			bonusAdded = true;
		}
		else if(bonusAdded) {
			points += model().bonus();
			bonusAdded = false;
		}
		
		model().scoreboard().update(delta);
		model().scoreboard().setPoints(pointsCounter);
		view().bonusLabel().text.update(String.valueOf(bonusCounter));
	}
		
}
