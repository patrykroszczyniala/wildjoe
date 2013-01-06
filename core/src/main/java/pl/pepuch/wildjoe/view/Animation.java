package pl.pepuch.wildjoe.view;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.PlayN;
import tripleplay.anim.Flipbook;
import tripleplay.game.AnimScreen;
import tripleplay.util.SimpleFrames;

public class Animation extends AnimScreen {

    private GroupLayer groupLayer;
    private SimpleFrames frames;
    private boolean isVisible;
    private boolean isMirrored;

    public Animation(Image image, int frameWidth, int frameHeight, int framesCount, float secsPerFrame, boolean repeat) {
        isVisible = true;
        isMirrored = false;
        groupLayer = PlayN.graphics().createGroupLayer();
        groupLayer.setOrigin(frameWidth / 2, 0);
        frames = new SimpleFrames(image, frameWidth, frameHeight, framesCount);
        if (repeat) {
            anim.repeat(groupLayer).flipbook(groupLayer, new Flipbook(frames, secsPerFrame));
        } else {
            anim.flipbook(groupLayer, new Flipbook(frames, secsPerFrame));
        }
    }

    public Animation mirrorHorizontally() {
        groupLayer.setScale(-1, 1);
        isMirrored = !isMirrored;
        return this;
    }

    public boolean isMirrored() {
        return isMirrored;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        groupLayer.setVisible(isVisible);
        this.isVisible = isVisible;
    }

    public GroupLayer getLayer() {
        return groupLayer;
    }

    @Override
    protected float updateRate() {
        return 0;
    }
}
