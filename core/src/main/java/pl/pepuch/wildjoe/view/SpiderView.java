package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.SpiderModel;
import playn.core.Image;

public class SpiderView extends DynamicView {
	
	// public tylko tymczasowo, do testow
	public Animation animationRun;
	
	public SpiderView(SpiderModel model) {
		super(model);
		
		Image imageRun = AssetsFactory.getImage("images/bug.png");

		int size = 32;
		animationRun = new Animation(imageRun, size, size, 2, 100);
		
		animationRun.setVisible(true);

		addLayerAt(animationRun.getLayer(), 0, size/-2);
	}
	

	public void paint(float alpha) {
		animationRun.paint(alpha);
		super.paint(alpha);
	}
	
	public void update(float delta) {
		animationRun.update(delta);
		super.update(delta);
	}
	
	
	public void turnLeft() {
		if (model().isTurnedRight()) {
			animationRun.mirrorHorizontally();
		}
	}
	
	public void turnRight() {
		if (model().isTurnedLeft()) {
			animationRun.mirrorHorizontally();
		}
	}
	
}
