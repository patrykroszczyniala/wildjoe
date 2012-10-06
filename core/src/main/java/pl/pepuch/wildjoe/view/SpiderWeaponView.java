package pl.pepuch.wildjoe.view;

import static playn.core.PlayN.graphics;
import pl.pepuch.wildjoe.model.SpiderWeaponModel;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;

public class SpiderWeaponView extends DynamicView {

	public SpiderWeaponView(SpiderWeaponModel block) {
		super(block);
		//Image image = assets().getImage("images/weapon.png");
		Image image = PlayN.graphics().createImage(2, 2);
		ImageLayer layer = graphics().createImageLayer(image);
		addLayer(layer);
	}

}
