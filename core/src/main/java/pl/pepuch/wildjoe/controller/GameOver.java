package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.GameOverModel;
import pl.pepuch.wildjoe.view.GameOverView;
import playn.core.PlayN;
import react.UnitSlot;


public class GameOver extends StaticActor {
	
	private int points;
	private int counter;
	
	public GameOver(final GameWorld gameWorld) {
		model = new GameOverModel(gameWorld.pointCounter().clone());
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
		points = model().pointCounter().getPoints();
		counter = 0;
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
		model().pointCounter().getIface().paint(alpha);
	}
	
	public void update(float delta) {
		super.update(delta);
		if (counter<points) {
			if (counter<50 || (points-counter)<100) {
				counter++;
			}
			else if (counter>=50 && counter<500) {
				counter += 10;
			}
			else {
				counter += 100;
			}
		}
		
		model().pointCounter().getIface().update(delta);
		model().pointCounter().setPoints(counter);
	}
		
}
