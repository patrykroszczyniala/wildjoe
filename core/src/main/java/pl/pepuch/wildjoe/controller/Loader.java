package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.helpers.AssetsFactory;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;

public class Loader {
	
	private GroupLayer layer;
	private Image loadingImage;
	private Image bgImage;
	
	public void start() {
		bgImage = AssetsFactory.getImage("images/bg.png");
		loadingImage = AssetsFactory.getImage("images/loading.png");
		layer = PlayN.graphics().createGroupLayer();
		layer.add(PlayN.graphics().createImageLayer(bgImage));
		ImageLayer loadingLayer = PlayN.graphics().createImageLayer(loadingImage);
		layer.add(loadingLayer);
		loadingLayer.setTranslation((PlayN.graphics().width()/2)-loadingImage.width()/2, (PlayN.graphics().height()/2)-loadingImage.height()/2);
		PlayN.graphics().rootLayer().add(layer);
	}
	
	public void stop() {
		PlayN.invokeLater(new Runnable() {
			@Override
			public void run() {
				layer.destroy();
				loadingImage = null;
				bgImage = null;
				layer = null;
			}
		});
	}

}
