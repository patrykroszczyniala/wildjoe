package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.model.PlayerModel;
import playn.core.Image;
import playn.core.PlayN;

public class PlayerView extends DynamicView {
	
	private Animation animationLeft;
	private Animation animationRight;
	private Animation animationIdle;
	
	public PlayerView(PlayerModel player) {
		super(player);
		
		int height = 64;
		Image imageRunLeft = PlayN.assets().getImage("images/runLeft.png");
		Image imageRunRight = PlayN.assets().getImage("images/runRight.png");
		Image imageIdle = PlayN.assets().getImage("images/idle.png");
		animationRight = new Animation(imageRunRight, height, height, 10, 66);
		animationLeft = new Animation(imageRunLeft, height, height, 10, 66);
		animationIdle = new Animation(imageIdle, height, height, 1, 66);
		animationIdle.setVisible(true);
		animationLeft.setVisible(false);
		animationRight.setVisible(false);
		addLayer(animationIdle.getLayer());
		addLayer(animationLeft.getLayer());
		addLayer(animationRight.getLayer());
		animationIdle.getLayer().setOrigin(16, 0);
		animationLeft.getLayer().setOrigin(16, 0);
		animationRight.getLayer().setOrigin(16, 0);
	}
	
	public void paint(float alpha) {
		if (isAnimationRightVisible()) {
			animationRight.paint(alpha);
		}
		if (isAnimationLeftVisible()) {
			animationLeft.paint(alpha);
		}
		if (isAnimationIdleVisible()) {
			animationIdle.paint(alpha);
		}
		model.setAngle(0.0f);
		super.paint(alpha);
	}
	
	public void update(float delta) {
		if (isAnimationRightVisible()) {
			animationRight.update(delta);
		}
		if (isAnimationLeftVisible()) {
			animationLeft.update(delta);
		}
		if (isAnimationIdleVisible()) {
			animationIdle.update(delta);
		}
		super.update(delta);
	}
	
	public void setAnimationRightVisible(boolean isVisible) {
		animationRight.setVisible(isVisible);
		if (isVisible) {
			getLayer().setVisible(isVisible);
		}
	}

	public boolean isAnimationRightVisible() {
		return animationRight.isVisible();
	}

	public void setAnimationLeftVisible(boolean isVisible) {
		animationLeft.setVisible(isVisible);
		if (isVisible) {
			getLayer().setVisible(isVisible);
		}
	}
	
	public boolean isAnimationLeftVisible() {
		return animationLeft.isVisible();
	}
	
	public void setAnimationIdleVisible(boolean isVisible) {
		animationIdle.setVisible(isVisible);
		if (isVisible) {
			getLayer().setVisible(isVisible);
		}
	}
	
	public boolean isAnimationIdleVisible() {
		return animationIdle.isVisible();
	}

}
