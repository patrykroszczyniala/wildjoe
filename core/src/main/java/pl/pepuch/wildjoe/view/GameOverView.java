package pl.pepuch.wildjoe.view;

import static playn.core.PlayN.assets;
import pl.pepuch.wildjoe.model.GameOverModel;
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
	
	public GameOverView(GameOverModel model) {
		super();
		float xPos = 0.0f;
		float yPos = 0.0f;
		Image image = null;
		// bg
		image = assets().getImage("images/bg.png");
		addLayerAt(image, xPos, yPos).setDepth(-4);
		// layout
		Root root = iface.createRoot(AxisLayout.vertical(), SimpleStyles.newSheet());
        root.addStyles(Style.VALIGN.center);
        root.setSize(PlayN.graphics().width(), PlayN.graphics().height());
        Label labelGameOver = new Label(PlayN.assets().getImage("images/gameOver.png"));
        btnContinue = new Button(PlayN.assets().getImage("images/btnContinue.png"));
		root.add(new Group(AxisLayout.vertical(), Style.HALIGN.center, Style.BACKGROUND.is(Background.blank())).add(
				labelGameOver,
				model.pointCounter().root(),
				btnContinue));
		btnContinue.setStyles(Style.BACKGROUND.is(Background.blank()));
		addLayer(root.layer);
	}
	
	public Button btnContinue() {
		return btnContinue;
	}

}
