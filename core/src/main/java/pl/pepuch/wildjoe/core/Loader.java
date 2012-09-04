package pl.pepuch.wildjoe.core;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import tripleplay.anim.Animator;

public class Loader {
	
	private boolean isRunning;
	private ImageLayer layer;
	private Image image;
	private float time;
	private Animator anim;
	
	public void start() {
		isRunning = true;
		image = PlayN.assets().getImage("images/loading.png");
		layer = PlayN.graphics().createImageLayer(image);
		layer.setTranslation((PlayN.graphics().width()/2)-image.width()/2, (PlayN.graphics().height()/2)-image.height()/2);
		PlayN.graphics().rootLayer().add(layer);
		anim = Animator.create();
		anim.tweenRotation(layer).to(1000000).in(10000);
	}
	
	public void stop() {
		isRunning = false;
		anim.destroy(layer);
		layer.destroy();
	}
	
	public boolean isRunning() {
		return isRunning;
	}

	public void update(float delta) {
		time += delta;
		anim.update(time);
	}

}
