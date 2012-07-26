package pl.pepuch.wildjoe.view;

import java.util.ArrayList;
import java.util.List;

import pl.pepuch.wildjoe.model.DynamicModel;
import pl.pepuch.wildjoe.model.StaticModel;
import playn.core.GroupLayer;
import playn.core.Layer;
import playn.core.PlayN;


public abstract class StaticView {

	private ArrayList<Layer> layers;
	protected GroupLayer layer;
	protected DynamicModel model;
	
	public StaticView(StaticModel model) {
		layers = new ArrayList<Layer>();
		layer = PlayN.graphics().createGroupLayer();
	}
	
	public DynamicModel getModel() {
		return model;
	}
	
	public void addLayer(Layer layer) {
		this.layer.add(layer);
		layers.add(layer);
	}
	
	public void addLayerAt(Layer layer, float x, float y) {
		this.layer.addAt(layer, x, y);
	}
	
	public Layer getLayer() {
		return layer;
	}
	
	public ArrayList<Layer> getLayers() {
		return layers;
	}
	
}
