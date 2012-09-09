package pl.pepuch.wildjoe.view;

import static playn.core.PlayN.graphics;
import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.BlockModel;
import playn.core.Image;

public class BlockView extends DynamicView {
	
	public BlockView(BlockModel block) {
		super(block);
		Image image = AssetsFactory.getImage("images/block.png");
		addLayer(graphics().createImageLayer(image));
	}
	
}
