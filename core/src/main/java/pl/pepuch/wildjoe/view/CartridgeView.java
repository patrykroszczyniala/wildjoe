package pl.pepuch.wildjoe.view;

import static playn.core.PlayN.graphics;
import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.CartridgeModel;
import playn.core.Image;
import playn.core.ImageLayer;

public class CartridgeView extends DynamicView {
	
	public CartridgeView(CartridgeModel cartridge) {
		super(cartridge);
		Image image = AssetsFactory.getImage("images/cartridge.png");
		ImageLayer layer = graphics().createImageLayer(image);
		layer.setOrigin(image.width()/2.0f, image.height()/2.0f);
		addLayer(layer);
		
	}
	
}
