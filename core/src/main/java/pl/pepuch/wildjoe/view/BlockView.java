package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.core.WildJoe;
import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.BlockModel;
import playn.core.PlayN;

public class BlockView extends DynamicView {
	
	public BlockView(BlockModel model) {
		super(model);
		String imagePath = "";
		for(int xPos=0; xPos<model.width(); xPos++) {
			for(int yPos=0; yPos<model.height(); yPos++) {
				float x = xPos/WildJoe.physUnitPerScreenUnit;
				float y = (yPos-model.height())/WildJoe.physUnitPerScreenUnit;
				
				imagePath = "images/block_1_1_0_0.png";
				if (model.height()==1 && model.width()>1) {
					if (xPos==0) {
						imagePath = "images/block_0_1_0_0.png";
					}
					else if (xPos==model.width()-1) {
						imagePath = "images/block_1_0_0_0.png";
					}
				}
				else if (model.height()==1 && model.width()==1) {
					imagePath = "images/block_0_0_0_0.png";
				}
				
				addLayerAt(PlayN.graphics().createImageLayer(AssetsFactory.getImage(imagePath)), x, y);
			}
		}
	}
		
}
