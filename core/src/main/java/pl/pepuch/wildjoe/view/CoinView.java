package pl.pepuch.wildjoe.view;

import static playn.core.PlayN.graphics;
import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.CoinModel;
import playn.core.Image;

public class CoinView extends DynamicView {
	
	public CoinView(CoinModel block) {
		super(block);
		Image image = AssetsFactory.getImage("images/coin.png");
		addLayer(graphics().createImageLayer(image));
	}
	
}
