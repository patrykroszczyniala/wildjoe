package pl.pepuch.wildjoe.controller;

import java.util.Iterator;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import pl.pepuch.wildjoe.model.BackgroundModel;
import pl.pepuch.wildjoe.view.BackgroundView;
import playn.core.Layer;


public class Background extends StaticActor {
	
	public Background(World world, Vec2 position) {
		model = new BackgroundModel();
		view = new BackgroundView((BackgroundModel)model);
		view.getLayer().setDepth(-1);
	}

	public void paint(float alpha) {
	}
	
	public void update(float delta) {
	}
	
	public void moveRight() {
		for (Iterator<Layer> layer = view.getLayers().iterator(); layer.hasNext();) {
			Layer l = (Layer)layer.next();
			if (!((BackgroundView)view).isBackground(l)) {
				l.setOrigin(l.originX() - (1f/l.depth()), l.originY());
			}
		}
	}
	
	public void moveLeft() {
		for (Iterator<Layer> layer = view.getLayers().iterator(); layer.hasNext();) {
			Layer l = (Layer)layer.next();
			if (!((BackgroundView)view).isBackground(l)) {
				l.setOrigin(l.originX() + (1f/l.depth()), l.originY());
			}
		}
	}
		
}
