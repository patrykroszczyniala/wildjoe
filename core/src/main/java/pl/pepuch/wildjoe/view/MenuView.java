package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.helpers.AssetsFactory;
import playn.core.AssetWatcher;
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
	private Button scores;
	
	public MenuView() {
		super();
		// TODO zrobic fabryke assetow do pobierania obrazkow (dzieki temu beda tylko raz ladowane), np. AssetFactory
		final Image bgImage = AssetsFactory.getImage("images/bg.png");
		final Image menuBgImage = AssetsFactory.getImage("images/menuBg.png");
		final Image stoneImage = AssetsFactory.getImage("images/stone.png");
		final Image btnStartImage = AssetsFactory.getImage("images/btnStart.png");
		final Image btnScoresImage = AssetsFactory.getImage("images/btnScores.png");
		
		start = new Button();
		scores = new Button();
		
		AssetWatcher assetWatcher = new AssetWatcher(new AssetWatcher.Listener() {

			@Override
			public void done() {
				float xPos = 0.0f;
				float yPos = 0.0f;
				// bg
				addLayerAt(bgImage, xPos, yPos).setDepth(-4);
				// sign
		        xPos = (PlayN.graphics().width()-menuBgImage.width())/2;
				yPos = (PlayN.graphics().height()-menuBgImage.height())/2+70;
				addLayerAt(menuBgImage, xPos, yPos).setDepth(-3);
				// stone
				xPos = (PlayN.graphics().width()-stoneImage.width())/2-10;
				yPos = (PlayN.graphics().height()-stoneImage.height())/2+190;
				addLayerAt(stoneImage, xPos, yPos).setDepth(-2);
				
				root = iface.createRoot(AxisLayout.vertical(), SimpleStyles.newSheet());
				root.addStyles(Style.VALIGN.center);
				root.setSize(PlayN.graphics().width(), PlayN.graphics().height());
				start.icon.update(btnStartImage);
				scores.icon.update(btnScoresImage);
				root.add(new Group(AxisLayout.vertical(), Style.HALIGN.center, Style.BACKGROUND.is(Background.blank())).add(
		                start,
		                new Shim(10, 20),
		                scores));
				start.setStyles(Style.BACKGROUND.is(Background.blank()));
				scores.setStyles(Style.BACKGROUND.is(Background.blank()));
				
				root.layer.setDepth(0);
				addLayer(root.layer);
			}

			@Override
			public void error(Throwable e) {
				e.printStackTrace();
			}
			
		});
		assetWatcher.add(bgImage);
		assetWatcher.add(menuBgImage);
		assetWatcher.add(stoneImage);
		assetWatcher.add(btnStartImage);
		assetWatcher.add(btnScoresImage);
		assetWatcher.start();
	}

	public Button start() {
		return start;
	}
	
	public Button scores() {
		return scores;
	}
	
	public void destroy() {
		super.destroy();
		root.destroyAll();
		start = null;
		scores = null;
	}

}