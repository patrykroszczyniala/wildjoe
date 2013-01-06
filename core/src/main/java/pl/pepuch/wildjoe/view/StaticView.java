package pl.pepuch.wildjoe.view;

import java.util.ArrayList;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.Layer;
import playn.core.PlayN;
import tripleplay.game.UIAnimScreen;

public abstract class StaticView extends UIAnimScreen {

    private ArrayList<Layer> layers;
    protected GroupLayer layer;

    public StaticView() {
        layers = new ArrayList<Layer>();
        layer = PlayN.graphics().createGroupLayer();
    }

    public Layer addLayerAt(Image image, float x, float y) {
        GroupLayer layer = PlayN.graphics().createGroupLayer();
        layer.addAt(PlayN.graphics().createImageLayer(image), x, y);
        addLayer(layer);

        return layer;
    }

    public void addLayer(Layer layer) {
        this.layer.add(layer);
        layers.add(layer);
    }

    public void addLayerAt(Layer layer, float x, float y) {
        this.layer.addAt(layer, x, y);
        layers.add(layer);
    }

    public Layer layer() {
        return layer;
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public void destroy() {
        layer.destroy();
        layers.clear();
        layer = null;
        layers = null;
    }

    @Override
    protected float updateRate() {
        return 0;
    }
}
