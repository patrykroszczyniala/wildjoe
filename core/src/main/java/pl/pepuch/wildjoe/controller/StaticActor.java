package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.model.StaticModel;
import pl.pepuch.wildjoe.view.StaticView;

public abstract class StaticActor {
	
	protected StaticView view;
	protected StaticModel model;
	
	protected abstract StaticView view();
	protected abstract StaticModel model();
	
}
