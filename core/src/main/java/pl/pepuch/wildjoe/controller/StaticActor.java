package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.model.StaticModel;
import pl.pepuch.wildjoe.view.StaticView;

public abstract class StaticActor {
	
	protected StaticView view;
	protected StaticModel model;
	private boolean isVisible;
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
		view().getLayer().setVisible(isVisible);
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void destroy() {
		view().destroy();
	}
	
	protected abstract StaticView view();
	protected abstract StaticModel model();
	
}
