package pl.pepuch.wildjoe.view;

import static playn.core.PlayN.assets;
import playn.core.Image;
import playn.core.PlayN;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Root;
import tripleplay.ui.Shim;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;

public class MenuView extends StaticView {
	
	private Root root;
	private Button start;
	private Button  exit;
	
	public MenuView() {
		super();
		float xPos = 0.0f;
		float yPos = 0.0f;
		Image image = null;
		// bg
		image = assets().getImage("images/bg.png");
		addLayerAt(image, xPos, yPos).setDepth(-4);
		// sign
        image = PlayN.assets().getImage("images/menuBg.png");
		xPos = (PlayN.graphics().width()-image.width())/2;
		yPos = (PlayN.graphics().height()-image.height())/2+70;
		addLayerAt(image, xPos, yPos).setDepth(-3);
		// stone
		image = PlayN.assets().getImage("images/stone.png");
		xPos = (PlayN.graphics().width()-image.width())/2-10;
		yPos = (PlayN.graphics().height()-image.height())/2+190;
		addLayerAt(image, xPos, yPos).setDepth(-2);
		
		root = iface.createRoot(AxisLayout.vertical(), SimpleStyles.newSheet());
		root.addStyles(Style.VALIGN.center);
		root.setSize(PlayN.graphics().width(), PlayN.graphics().height());
		start = new Button(PlayN.assets().getImage("images/btnStart.png"));
		exit = new Button(PlayN.assets().getImage("images/btnExit.png"));
		root.add(new Group(AxisLayout.vertical(), Style.HALIGN.center, Style.BACKGROUND.is(Background.blank())).add(
                start,
                new Shim(10, 20),
                exit));
		start.setStyles(Style.BACKGROUND.is(Background.blank()));
		exit.setStyles(Style.BACKGROUND.is(Background.blank()));
		
		addLayer(root.layer);

	}

	public Button start() {
		return start;
	}
	
	public Button exit() {
		return exit;
	}
	
	public void destroy() {
		super.destroy();
		root.destroyAll();
		start = null;
		exit = null;
	}

}
