package pl.pepuch.wildjoe.view;

import static playn.core.PlayN.graphics;
import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.SpiderModel;
import playn.core.Image;
import playn.core.ImageLayer;

public class SpiderView extends DynamicView {
	
	public SpiderView(SpiderModel cartridge) {
		super(cartridge);
		Image image = AssetsFactory.getImage("images/bug.png");
		ImageLayer layer = graphics().createImageLayer(image);
		layer.setOrigin(image.width()/2, image.height()/2);
		addLayer(layer);	
	}
	
}
