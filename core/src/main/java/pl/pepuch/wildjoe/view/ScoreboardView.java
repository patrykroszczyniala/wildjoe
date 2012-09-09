package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.ScoreboardModel;
import playn.core.PlayN;
import tripleplay.ui.Group;
import tripleplay.ui.Interface;
import tripleplay.ui.Label;
import tripleplay.ui.Root;
import tripleplay.ui.Shim;
import tripleplay.ui.Style;
import tripleplay.ui.Stylesheet;
import tripleplay.ui.layout.AxisLayout;

public class ScoreboardView extends StaticView implements Cloneable {
	
	private Root root;
	private Interface iface;
	private Label pointsLabel;
	private Label heartLabel;
	private Label levelLabel;
	
	public ScoreboardView(ScoreboardModel model) {
		iface = new Interface();
		
		levelLabel = new Label("Level "+String.valueOf(model.level()));
		levelLabel.setStyles(Style.TEXT_EFFECT.pixelOutline);
		pointsLabel = new Label(String.valueOf(model.points()), AssetsFactory.getImage("images/coinIcon.png"));
		pointsLabel.setStyles(Style.ICON_POS.left, Style.TEXT_EFFECT.pixelOutline);
		heartLabel = new Label(String.valueOf(model.lives()), AssetsFactory.getImage("images/heartIcon.png"));
		heartLabel.setStyles(Style.ICON_POS.left, Style.TEXT_EFFECT.pixelOutline);
		
		Group leftGroup = new Group(AxisLayout.horizontal()).add(new Shim(10, 0), levelLabel).setStyles(Style.HALIGN.left, Style.VALIGN.bottom);
		Group rightGroup = new Group(AxisLayout.horizontal()).add(heartLabel, pointsLabel).setStyles(Style.HALIGN.left, Style.VALIGN.bottom);
		
		root = iface.createRoot(AxisLayout.horizontal(), Stylesheet.builder().create());
		root.add(leftGroup);
		root.add(new Shim(PlayN.graphics().width()-200, 40));
		root.add(rightGroup);
		root.setStyles(Style.VALIGN.bottom, Style.HALIGN.left);
		root.setSize(40, 40);
		root.layer.setDepth(0);
		
		addLayer(root.layer);	
	}
	
	public Label points() {
		return pointsLabel;
	}
	
	public Label level() {
		return levelLabel;
	}
	
	public Label lives() {
		return heartLabel;
	}
	
	public Root root() {
		return root;
	}
	
	@Override
	public void update(float delta) {
		iface.update(delta);
		super.update(delta);
	}

	@Override
	public void paint(float alpha) {
		iface.paint(alpha);
		super.paint(alpha);
	}
	
	public void destroy() {
		root.destroyAll();
		root.destroyAll();
		iface.destroyRoot(root);
		iface.destroyRoot(root);
	}
	
}
