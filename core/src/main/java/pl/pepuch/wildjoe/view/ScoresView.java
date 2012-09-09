package pl.pepuch.wildjoe.view;

import static tripleplay.ui.layout.TableLayout.COL;
import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.ScoresModel;
import playn.core.AssetWatcher;
import playn.core.Image;
import playn.core.Json.Array;
import playn.core.PlayN;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.Root;
import tripleplay.ui.Shim;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.Style;
import tripleplay.ui.Style.TextEffect;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.TableLayout;

public class ScoresView extends StaticView {
	
	private Root root;
	private Button back;
	private ScoresModel model;
	private Group scoresList;
	
	public ScoresView(final ScoresModel model) {
		super();
		this.model = model;
		// TODO zrobic fabryke assetow do pobierania obrazkow (dzieki temu beda tylko raz ladowane), np. AssetFactory
		final Image bgImage = AssetsFactory.getImage("images/bg.png");
		final Image btnBackImage = AssetsFactory.getImage("images/btnBack.png");
		
		back = new Button();
		
		AssetWatcher assetWatcher = new AssetWatcher(new AssetWatcher.Listener() {

			@Override
			public void done() {
				float xPos = 0.0f;
				float yPos = 0.0f;
				// bg
				addLayerAt(bgImage, xPos, yPos).setDepth(-4);
				// user list
				scoresList = new Group(new TableLayout(COL, COL, COL).gaps(10, 7), Style.BACKGROUND.is(Background.roundRect(0xFFAACCCC, 10).inset(15)));
				root = iface.createRoot(AxisLayout.vertical(), SimpleStyles.newSheet());
				root.addStyles(Style.VALIGN.center);
				root.setSize(PlayN.graphics().width(), PlayN.graphics().height());
				back.icon.update(btnBackImage);
				root.add(new Group(AxisLayout.vertical(), Style.HALIGN.center, Style.BACKGROUND.is(Background.blank())).add(
						scoresList,
		                new Shim(10, 20),
		                back));
				back.setStyles(Style.BACKGROUND.is(Background.blank()));
				
				root.layer.setDepth(0);
				addLayer(root.layer);
			}

			@Override
			public void error(Throwable e) {
				e.printStackTrace();
			}
			
		});
		assetWatcher.add(bgImage);
		assetWatcher.add(btnBackImage);
		assetWatcher.start();
	}

	public Button back() {
		return back;
	}
	
	public void destroy() {
		super.destroy();
		root.destroyAll();
		back = null;
	}
	
	public void updateScores() {
		scoresList.removeAll();
		if (model.jsonArray()!=null) {
			scoresList.add(
					new Label("Position").setStyles(Style.TEXT_EFFECT.is(TextEffect.PIXEL_OUTLINE)), 
					new Label("Player").setStyles(Style.TEXT_EFFECT.is(TextEffect.PIXEL_OUTLINE)), 
					new Label("Score").setStyles(Style.TEXT_EFFECT.is(TextEffect.PIXEL_OUTLINE)));
			for (int i=0; i<model.jsonArray().length(); i++) {
				Array userData = model.jsonArray().getArray(i);
				if (userData!=null) {
					String name = userData.getString(0);
					String score = userData.getString(1);
					scoresList.add(new Label(String.valueOf(i+1)), new Label(name), new Label(score));
				}
			}
		}
		else {
			scoresList.add(new Label(), new Label("Unable to get scores").setStyles(Style.TEXT_EFFECT.is(TextEffect.PIXEL_OUTLINE)), new Label());
		}
	}

}