package pl.pepuch.wildjoe.core;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.PlayN;
import tripleplay.anim.Flipbook;
import tripleplay.game.AnimScreen;
import tripleplay.util.SimpleFrames;


public class Animation extends AnimScreen {

	private GroupLayer groupLayer;
	private Image image;
	private SimpleFrames frames;
	private boolean isVisible;
	
	public Animation(Image image, int frameWidth, int frameHeight, int framesCount, float secsPerFrame) {
		isVisible = true;
		groupLayer = PlayN.graphics().createGroupLayer();
		System.out.println(frameWidth);
		frames = new SimpleFrames(image, frameWidth, frameHeight, framesCount);
		anim.repeat(groupLayer).flipbook(groupLayer, new Flipbook(frames, secsPerFrame));
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public GroupLayer getLayer() {
		return groupLayer;
	}

	@Override
	protected float updateRate() {
		// TODO Auto-generated method stub
		return 0;
	}

}
