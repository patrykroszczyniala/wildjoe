package pl.pepuch.wildjoe.view;

import static playn.core.PlayN.graphics;
import pl.pepuch.wildjoe.model.BlockModel;
import playn.core.Image;

public class BlockView extends DynamicView {
	
	public BlockView(BlockModel block, Image image) {
		super(block);
//		Image image = assets().getImage("images/block.png");
		addLayer(graphics().createImageLayer(image));
	}
	
}
