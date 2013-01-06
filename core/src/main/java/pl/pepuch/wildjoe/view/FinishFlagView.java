package pl.pepuch.wildjoe.view;

import static playn.core.PlayN.graphics;
import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.FinishFlagModel;
import playn.core.Image;

public class FinishFlagView extends DynamicView {

    public FinishFlagView(FinishFlagModel finishFlag) {
        super(finishFlag);
        Image image = AssetsFactory.getImage("images/finishFlag.png");
        addLayer(graphics().createImageLayer(image));
    }
}
