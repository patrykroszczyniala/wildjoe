package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.core.WildJoe;
import pl.pepuch.wildjoe.model.DynamicModel;
import playn.core.GroupLayer;
import playn.core.Layer;
import playn.core.PlayN;

public abstract class DynamicView {

	protected GroupLayer layer;
	protected DynamicModel model;

	public DynamicView(DynamicModel model) {
		layer = PlayN.graphics().createGroupLayer();
		this.model = model;
	}
	
	public DynamicModel getModel() {
		return model;
	}
	
	public void addLayer(Layer layer) {
		this.layer.add(layer);
	}
	
	public void addLayerAt(Layer layer, float x, float y) {
		this.layer.addAt(layer, x, y);
	}
	
	public Layer getLayer() {
		return layer;
	}
	
	public void paint(float alpha) {
		float x = ((model.getPosition().x * alpha) + (model.getPosition().x * (1f - alpha)))/WildJoe.physUnitPerScreenUnit;
	    float y = ((model.getPosition().y * alpha) + (model.getPosition().y * (1f - alpha)))/WildJoe.physUnitPerScreenUnit;
	    float a = (model.getAngle() * alpha) + (model.getAngle() * (1f - alpha));
	    if (layer!=null) {
		    layer.setRotation(a);
		    layer.setTranslation(x, y);
	    }
	}
	
	public void update(float delta) {
	}
	
	public void destroy() {
		layer.destroy();
	}
}
