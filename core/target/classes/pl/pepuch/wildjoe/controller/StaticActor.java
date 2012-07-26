package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.model.StaticModel;
import pl.pepuch.wildjoe.view.StaticView;

public abstract class StaticActor {
	
	protected StaticView view;
	protected StaticModel model;
	
	public StaticView getView() {
		return view;
	}
	
	public StaticModel getModel() {
		return model;
	}
	
}
