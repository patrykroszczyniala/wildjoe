package pl.pepuch.wildjoe.core;

import playn.core.Font;
import playn.core.PlayN;
import tripleplay.game.UIAnimScreen;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Root;
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
		root.add(new Group(AxisLayout.vertical(), Style.HALIGN.center, Style.BACKGROUND.is(Background.blank())).add(
                start = new Button(PlayN.assets().getImage("images/btnStart.png")),
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
