package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.helpers.AssetsFactory;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;

public class Loader {
	
	private ImageLayer layer;
	private Image image;
	
	public void start() {
		image = AssetsFactory.getImage("images/loading.png");
		layer = PlayN.graphics().createImageLayer(image);
		layer.setTranslation((PlayN.graphics().width()/2)-image.width()/2, (PlayN.graphics().height()/2)-image.height()/2);
		PlayN.graphics().rootLayer().add(layer);
	}
	
	public void stop() {
		PlayN.invokeLater(new Runnable() {
			@Override
			public void run() {
				layer.destroy();
				image = null;
				layer = null;
			}
		});
	}

}
