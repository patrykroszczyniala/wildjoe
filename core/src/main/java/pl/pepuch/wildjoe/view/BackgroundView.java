package pl.pepuch.wildjoe.view;

import pl.pepuch.wildjoe.helpers.AssetsFactory;
import pl.pepuch.wildjoe.model.BackgroundModel;
import playn.core.AssetWatcher;
import playn.core.Image;
import playn.core.Layer;
import playn.core.PlayN;

public class BackgroundView extends StaticView {

    private Layer backgroundLayer;

    public BackgroundView(BackgroundModel model) {
        super();

        final Image bgImage = AssetsFactory.getImage("images/bg.png");
        final Image cactus2Image = AssetsFactory.getImage("images/cactus2.png");
        final Image stoneSmallImage = AssetsFactory.getImage("images/stoneSmall.png");
        final Image cactus3Image = AssetsFactory.getImage("images/cactus3.png");
        final Image cactus1Image = AssetsFactory.getImage("images/cactus1.png");
        final Image stoneImage = AssetsFactory.getImage("images/stone.png");

        AssetWatcher assetWatcher = new AssetWatcher(new AssetWatcher.Listener() {
            @Override
            public void error(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void done() {
                float xPos = 0.0f;
                float yPos = 0.0f;
                // layer -3 - static image;
                backgroundLayer = addLayerAt(bgImage, xPos, yPos);
                backgroundLayer.setDepth(-6);
                // bg layer -2 - dynamic image (tree)
                xPos = 100.0f;
                yPos = PlayN.graphics().height() - cactus2Image.height() - 150.0f;
                addLayerAt(cactus2Image, xPos, yPos).setDepth(-5);
                // small stone
                xPos = 150.0f;
                yPos = PlayN.graphics().height() - stoneSmallImage.height() - 140.0f;
                addLayerAt(stoneSmallImage, xPos, yPos).setDepth(-4);
                // bg layer -1 - dynamic image (tree)
                xPos = 400.0f;
                yPos = PlayN.graphics().height() - cactus3Image.height() - 100.0f;
                addLayerAt(cactus3Image, xPos, yPos).setDepth(-3);
                // bg layer -1 - dynamic image (tree)
                xPos = 800.0f;
                yPos = PlayN.graphics().height() - cactus1Image.height() - 20.0f;
                addLayerAt(cactus1Image, xPos, yPos).setDepth(-2);
                xPos = 200.0f;
                yPos = PlayN.graphics().height() - stoneImage.height() - 20.0f;
                addLayerAt(stoneImage, xPos, yPos).setDepth(-1);
            }
        });
        assetWatcher.add(bgImage);
        assetWatcher.add(cactus2Image);
        assetWatcher.add(stoneSmallImage);
        assetWatcher.add(cactus3Image);
        assetWatcher.add(cactus1Image);
        assetWatcher.add(stoneImage);
        assetWatcher.start();
    }

    public boolean isBackground(Layer layer) {
        return (layer == backgroundLayer) ? true : false;
    }
}
