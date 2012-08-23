package pl.pepuch.wildjoe.core.world;

import playn.core.Image;
import playn.core.Layer;
import playn.core.PlayN;
import tripleplay.ui.Interface;
import tripleplay.ui.Label;
import tripleplay.ui.Root;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.Stylesheet;
import tripleplay.ui.layout.AxisLayout;

public class PointCounter {
	
	private Root nameroot;
	private Interface iface;
	private Label label;
	private int points;
	
	public PointCounter() {
		Image icon = PlayN.assets().getImage("images/coinIcon.png");
		points = 0;
		label = new Label(String.valueOf(points), icon);
		iface = new Interface();
		Stylesheet rootSheet = Stylesheet.builder().create();
		nameroot = iface.createRoot(AxisLayout.horizontal().gap(300), rootSheet);
		nameroot.add(label);
		nameroot.setSize(100, 20);
		nameroot.setStyles(Styles.make(Style.HALIGN.left));
		nameroot.layer.setDepth(1f);
	}
	
	public Layer getLayer() {
		return nameroot.layer;
	}
	
	public Interface getIface() {
		return iface;
	}
	
	public void setPoints(Integer points) {
		this.points = points;
		label.text.update(String.valueOf(points));
	}
	
	public Integer getPoints() {
		return points;
	}
	
	public void destroy() {
		nameroot.destroyAll();
		iface.destroyRoot(nameroot);
	}
	
}
