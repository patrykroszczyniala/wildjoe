package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.GameOverModel;
import playn.core.AssetWatcher;
import playn.core.Image;
import playn.core.PlayN;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.Root;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;

public class GameOverView extends StaticView {
	
	private Button btnContinue;
	private Label bonusLabel;
	
	public GameOverView(final GameOverModel model) {
		super();
		
		btnContinue = new Button();
		bonusLabel = new Label();
		final Image bgImage = AssetsFactory.getImage("images/bg.png");
		final Image gameOverImage = AssetsFactory.getImage("images/gameOver.png");
		final Image btnContinueImage = AssetsFactory.getImage("images/btnContinue.png");
		final Image bonusIconImage = AssetsFactory.getImage("images/bonusIcon.png");
		
		AssetWatcher assetWatcher = new AssetWatcher(new AssetWatcher.Listener() {
			
			@Override
			public void error(Throwable e) {
				e.printStackTrace();
			}
			
			@Override
			public void done() {
				float xPos = 0.0f;
				float yPos = 0.0f;
				// bg
				addLayerAt(bgImage, xPos, yPos).setDepth(-4);
				// layout
				Root root = iface.createRoot(AxisLayout.vertical(), SimpleStyles.newSheet());
		        root.addStyles(Style.VALIGN.center);
		        root.setSize(PlayN.graphics().width(), PlayN.graphics().height());
		        Label labelGameOver = new Label(gameOverImage);
		        bonusLabel.icon.update(bonusIconImage);
		        btnContinue.icon.update(btnContinueImage);
				root.add(new Group(AxisLayout.vertical(), Style.HALIGN.center, Style.BACKGROUND.is(Background.blank())).add(
						labelGameOver,
						model.scoreboard().view().points(),
						bonusLabel,
						btnContinue));
				btnContinue.setStyles(Style.BACKGROUND.is(Background.blank()));
				addLayer(root.layer);
			}
		});
		assetWatcher.add(bgImage);
		assetWatcher.add(gameOverImage);
		assetWatcher.add(btnContinueImage);
		assetWatcher.add(bonusIconImage);
		assetWatcher.start();
	}
	
	public Button btnContinue() {
		return btnContinue;
	}

	public Label bonusLabel() {
		return bonusLabel;
	}

}
