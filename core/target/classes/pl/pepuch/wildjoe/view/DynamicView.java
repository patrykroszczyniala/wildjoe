package pl.pepuch.wildjoe.view;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.WildJoe;
import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.DynamicModel;
import playn.core.GroupLayer;
import playn.core.Layer;
import playn.core.PlayN;


public abstract class DynamicView {

	protected GroupLayer layer;
	protected DynamicModel model;
	protected Vec2 position; // position in pixels!
	
	public DynamicView(DynamicModel model) {
		layer = PlayN.graphics().createGroupLayer();
		this.model = model;
		position = new Vec2(model.getPosition().x/WildJoe.physUnitPerScreenUnit, model.getPosition().y/WildJoe.physUnitPerScreenUnit);
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
		float x = ((model.getPosition().x * alpha) + (model.getPosition().x * (1f - alpha)))/GameWorld.physUnitPerScreenUnit();
	    float y = ((model.getPosition().y * alpha) + (model.getPosition().y * (1f - alpha)))/GameWorld.physUnitPerScreenUnit();
	    float a = (model.getAngle() * alpha) + (model.getAngle() * (1f - alpha));
	    if (layer!=null) {
		    layer.setRotation(a);
		    layer.setTranslation(x, y);
	    }
	}
	
	public void update(float delta) {
		updateViewPosition();
	}
	
	public void updateViewPosition() {
		position = new Vec2(model.getPosition().x/WildJoe.physUnitPerScreenUnit, model.getPosition().y/WildJoe.physUnitPerScreenUnit);
	}
	
}
