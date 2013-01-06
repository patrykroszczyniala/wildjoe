package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.core.WildJoe;
import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.CoinModel;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;

public class CoinView extends DynamicView {

    private CoinModel block;

    public CoinView(final CoinModel block) {
        super(block);
        this.block = block;
        final Image image = AssetsFactory.getImage("images/coin.png");
        block.setRadius((image.width() / 2) * WildJoe.physUnitPerScreenUnit);
        ImageLayer layer = PlayN.graphics().createImageLayer(image);
        addLayer(layer);
    }

    public void paint(float alpha) {
        float radius = block.radius();
        float x = (((model.position().x - radius) * alpha) + ((model.position().x - radius) * (1f - alpha))) / WildJoe.physUnitPerScreenUnit;
        float y = (((model.position().y - radius) * alpha) + ((model.position().y - radius) * (1f - alpha))) / WildJoe.physUnitPerScreenUnit;
        float a = (model.angle() * alpha) + (model.angle() * (1f - alpha));
        if (layer != null) {
            layer.setRotation(a);
            layer.setTranslation(x, y);
        }
    }
}
