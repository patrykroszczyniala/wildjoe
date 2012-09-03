package pl.pepuch.wildjoe.core;

import playn.core.Font;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import tripleplay.game.UIAnimScreen;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Root;
import tripleplay.ui.Shim;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;

public class Menu extends UIAnimScreen {
	
    public static final Font TITLE_FONT = PlayN.graphics().createFont("Helvetica", Font.Style.PLAIN, 24);
	private Root root;
	private Button start;
	private Button  exit;
	
	public Menu() {
		root = iface.createRoot(AxisLayout.vertical(), SimpleStyles.newSheet());
        Background bg = Background.image(PlayN.assets().getImage("images/bg.png"));
        root.addStyles(Style.VALIGN.center);
        root.addStyles(Style.BACKGROUND.is(bg));
        root.setSize(width(), height());
        
        // sign
        Image menuBgImage = PlayN.assets().getImage("images/menuBg.png");
		ImageLayer menuBgLayer = PlayN.graphics().createImageLayer(menuBgImage);
		float x = (PlayN.graphics().width()-menuBgImage.width())/2;
		float y = (PlayN.graphics().height()-menuBgImage.height())/2+70;
		root.layer.addAt(menuBgLayer, x, y);
		// stone
		Image stoneBgImage = PlayN.assets().getImage("images/stone.png");
		ImageLayer stoneBgLayer = PlayN.graphics().createImageLayer(stoneBgImage);
		x = (PlayN.graphics().width()-stoneBgImage.width())/2-10;
		y = (PlayN.graphics().height()-stoneBgImage.height())/2+190;
		root.layer.addAt(stoneBgLayer, x, y);
		
		root.add(new Group(AxisLayout.vertical(), Style.HALIGN.center, Style.BACKGROUND.is(Background.blank())).add(
                start = new Button(PlayN.assets().getImage("images/btnStart.png")),
                new Shim(10, 20),
                exit = new Button(PlayN.assets().getImage("images/btnExit.png"))));
		start.setStyles(Style.BACKGROUND.is(Background.blank()));
		exit.setStyles(Style.BACKGROUND.is(Background.blank()));
		
		PlayN.graphics().rootLayer().add(root.layer);
		hide();
	}
	
	public void show() {
		root.layer.setVisible(true);
	}
	
	public void hide() {
		root.layer.setVisible(false);
	}
	
	public void destroy() {
		PlayN.graphics().rootLayer().remove(root.layer);
		root.destroyAll();
		root = null;
	}

	@Override
	protected float updateRate() {
		return 0;
	}
	
	public Button start() {
		return start;
	}
	
	public Button exit() {
		return exit;
	}

}
