package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.core.WildJoe;
import pl.pepuch.wildjoe.model.DynamicModel;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.Layer;
import playn.core.PlayN;

public abstract class DynamicView {

	protected GroupLayer layer;
	protected DynamicModel model;

	public DynamicView(DynamicModel model) {
		layer = PlayN.graphics().createGroupLayer();
		this.model = model;
	}
	
	public DynamicModel model() {
		return model;
	}
	
	public Layer layer() {
		return layer;
	}
	
	public void addImage(Image image) {
		layer.add(PlayN.graphics().createImageLayer(image));
	}
	
	public void addLayer(Layer layer) {
		this.layer.add(layer);
	}
	
	public void removeLayer(Layer layer) {
		this.layer.remove(layer);
	}
	
	public void addLayerAt(Layer layer, float x, float y) {
		this.layer.addAt(layer, x, y);
	}
	
	public void paint(float alpha) {
		float x = ((model.position().x * alpha) + (model.position().x * (1f - alpha)))/WildJoe.physUnitPerScreenUnit;
	    float y = ((model.position().y * alpha) + (model.position().y * (1f - alpha)))/WildJoe.physUnitPerScreenUnit;
//	    float a = (model.angle() * alpha) + (model.angle() * (1f - alpha));
	    if (layer!=null) {
//		    layer.setRotation(a);
		    layer.setTranslation(x, y);
	    }
	}
	
	public void update(float delta) {
	}
	
	public void destroy() {
		layer.destroy();
	}
}
