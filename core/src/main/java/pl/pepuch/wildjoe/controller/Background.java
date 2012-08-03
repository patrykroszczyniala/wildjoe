package pl.pepuch.wildjoe.controller;

import java.util.Iterator;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.BackgroundModel;
import pl.pepuch.wildjoe.view.BackgroundView;
import playn.core.Layer;


public class Background extends StaticActor {
	
	public Background(GameWorld world, Vec2 position) {
		model = new BackgroundModel();
		view = new BackgroundView(model());
		view().getLayer().setDepth(-1);
	}
	
	public void moveRight() {
		for (Iterator<Layer> layer = view().getLayers().iterator(); layer.hasNext();) {
			Layer l = (Layer)layer.next();
			if (!(view().isBackground(l))) {
				l.setOrigin(l.originX() - (0.8f/l.depth()), l.originY());
			}
		}
	}
	
	public void moveLeft() {
		for (Iterator<Layer> layer = view().getLayers().iterator(); layer.hasNext();) {
			Layer l = (Layer)layer.next();
			if (!(view().isBackground(l))) {
				l.setOrigin(l.originX() + (0.8f/l.depth()), l.originY());
			}
		}
	}

	@Override
	public BackgroundView view() {
		return (BackgroundView)view;
	}

	@Override
	public BackgroundModel model() {
		return (BackgroundModel)model;
	}
		
}
