package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.DiamondModel;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;

public class DiamondView extends DynamicView {

    public DiamondView(final DiamondModel block) {
        super(block);
        Image image = AssetsFactory.getImage("images/diamond.png");
        ImageLayer layer = PlayN.graphics().createImageLayer(image);
        addLayerAt(layer, image.width() / -2, image.height() / -2);
    }
}
