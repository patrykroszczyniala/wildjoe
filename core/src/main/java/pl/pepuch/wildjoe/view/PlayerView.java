package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.PlayerModel;
import playn.core.Image;

public class PlayerView extends DynamicView {
	
	// public tylko tymczasowo, do testow
	public Animation animationIdle;
	public Animation animationRun;
	public Animation animationJump;
	
	public PlayerView(PlayerModel model) {
		super(model);
		
		Image imageIdle = AssetsFactory.getImage("images/idle.png");
		Image imageRun = AssetsFactory.getImage("images/run.png");
		Image imageJump = AssetsFactory.getImage("images/idle.png");
//		Image imageJump = AssetsFactory.getImage("images/jump.png");

		int width = 40;
		int height = 64;
		int size = 64;
		animationIdle = new Animation(imageIdle, size, size, 1, 66);
		animationRun = new Animation(imageRun, width, height, 13, 66);
		animationJump = new Animation(imageJump, size, size, 1, 66);
		
		animationIdle.setVisible(false);
		animationRun.setVisible(false);
		animationJump.setVisible(false);

		addLayerAt(animationIdle.getLayer(), 0, size/-2);
		addLayerAt(animationRun.getLayer(), 0, size/-2);
		addLayerAt(animationJump.getLayer(), 0, size/-2);
	}
	
	public void paint(float alpha) {
		if (animationRun.isVisible()) {
			animationRun.paint(alpha);
		}
		if (animationIdle.isVisible()) {
			animationIdle.paint(alpha);
		}
		if (animationJump.isVisible()) {
			animationJump.paint(alpha);
		}
		super.paint(alpha);
	}
	
	public void update(float delta) {
		if (animationRun.isVisible()) {
			animationRun.update(delta);
		}
		if (animationIdle.isVisible()) {
			animationIdle.update(delta);
		}
		if (animationJump.isVisible()) {
			animationJump.update(delta);
		}
		super.update(delta);
	}
	
	
	public void turnLeft() {
		if (model().isTurnedRight()) {
			animationIdle.mirrorHorizontally();
			animationRun.mirrorHorizontally();
			animationJump.mirrorHorizontally();
		}
	}
	
	public void turnRight() {
		if (model().isTurnedLeft()) {
			animationIdle.mirrorHorizontally();
			animationRun.mirrorHorizontally();
			animationJump.mirrorHorizontally();
		}
	}
	
	public void showIdle() {
		animationIdle.setVisible(true);
		animationRun.setVisible(false);
		animationJump.setVisible(false);
	}
	
	public void showMoving() {
		animationIdle.setVisible(false);
		animationRun.setVisible(true);
		animationJump.setVisible(false);
	}
	
	public void showJumping() {
		animationIdle.setVisible(false);
		animationRun.setVisible(false);
		animationJump.setVisible(true);
	}
}
