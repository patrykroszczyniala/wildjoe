package pl.pepuch.wildjoe.view;

import static playn.core.PlayN.assets;
import pl.pepuch.wildjoe.model.BackgroundModel;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.Layer;
import playn.core.PlayN;

public class BackgroundView extends StaticView {
	
	public GroupLayer bgDynamicLayer;
	public GroupLayer bgDynamicLayer2;
	public GroupLayer bgDynamicLayer3;
	private Layer backgroundLayer;
	
	public BackgroundView(BackgroundModel model) {
		super();
		float xPos = 0.0f;
		float yPos = 0.0f;
		Image image = null;
		// layer -3 - static image
		image = assets().getImage("images/bg.png");
		backgroundLayer = addLayerAt(image, xPos, yPos);
		backgroundLayer.setDepth(-4);
		// bg layer -2 - dynamic image (tree)
		image = assets().getImage("images/cactus2.png");
		xPos = 100.0f;
		yPos = PlayN.graphics().height()-image.height()-150.0f;
		addLayerAt(image, xPos, yPos).setDepth(-3);
		// small stone
		image = assets().getImage("images/stoneSmall.png");
		xPos = 150.0f;
		yPos = PlayN.graphics().height()-image.height()-140.0f;
		addLayerAt(image, xPos, yPos).setDepth(-3);
		// bg layer -1 - dynamic image (tree)
		image = assets().getImage("images/cactus3.png");
		xPos = 400.0f;
		yPos = PlayN.graphics().height()-image.height()-100.0f;
		addLayerAt(image, xPos, yPos).setDepth(-2);
		// bg layer -1 - dynamic image (tree)
		image = assets().getImage("images/cactus1.png");
		xPos = 800.0f;
		yPos = PlayN.graphics().height()-image.height()-20.0f;
		addLayerAt(image, xPos, yPos).setDepth(-1);
		image = assets().getImage("images/stone.png");
		xPos = 200.0f;
		yPos = PlayN.graphics().height()-image.height()-20.0f;
		addLayerAt(image, xPos, yPos).setDepth(-1);
	}
	
	public boolean isBackground(Layer layer) {
		return (layer == backgroundLayer) ? true : false;
	}
	
}
