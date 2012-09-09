package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.MummyModel;
import playn.core.Image;

public class MummyView extends DynamicView {
	
	private Animation animationLeft;
	private Animation animationRight;
	private Animation animationIdle;
	
	public MummyView(MummyModel mummy) {
		super(mummy);
		
		Image imageRunLeft = AssetsFactory.getImage("images/runLeft.png");
		Image imageRunRight = AssetsFactory.getImage("images/runRight.png");
		Image imageIdle = AssetsFactory.getImage("images/idle.png");
		
		int size = 64;
		animationRight = new Animation(imageRunRight, size, size, 10, 120);
		animationLeft = new Animation(imageRunLeft, size, size, 10, 120);
		animationIdle = new Animation(imageIdle, size, size, 1, 120);
		
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
			layer().setVisible(isVisible);
			setAnimationIdleVisible(false);
			setAnimationLeftVisible(false);
		}
	}

	public boolean isAnimationRightVisible() {
		return animationRight.isVisible();
	}

	public void setAnimationLeftVisible(boolean isVisible) {
		animationLeft.setVisible(isVisible);
		if (isVisible) {
			layer().setVisible(isVisible);
			setAnimationRightVisible(false);
			setAnimationIdleVisible(false);
		}
	}
	
	public boolean isAnimationLeftVisible() {
		return animationLeft.isVisible();
	}
	
	public void setAnimationIdleVisible(boolean isVisible) {
		animationIdle.setVisible(isVisible);
		if (isVisible) {
			layer().setVisible(isVisible);
			setAnimationRightVisible(false);
			setAnimationLeftVisible(false);
		}
	}
	
	public boolean isAnimationIdleVisible() {
		return animationIdle.isVisible();
	}

}
