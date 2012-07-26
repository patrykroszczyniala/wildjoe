package pl.pepuch.wildjoe.view;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import pl.pepuch.wildjoe.model.BlockModel;
import pl.pepuch.wildjoe.model.PlayerModel;
import playn.core.Image;
import playn.core.PlayN;

public class BlockView extends DynamicView {
	
	public BlockView(BlockModel block) {
		super(block);
		Image image = assets().getImage("images/block.png");
		addLayer(graphics().createImageLayer(image));
	}
	
}
