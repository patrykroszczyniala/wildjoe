/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pepuch.wildjoe.helpers;

import java.util.HashMap;

import playn.core.AssetWatcher;
import playn.core.Image;
import playn.core.PlayN;
import playn.core.Sound;

/**
 *
 * @author proszczyniala
 */
public class AssetsFactory {
    
    private static HashMap<String, Image> images = new HashMap<String, Image>();
    private static HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	
    //TODO load images synchronously
    public static Image getImage(String path) {
		registerImage(path);
		return images.get(path);
    }
    
    public static Sound getSound(String path) {
    	registerSound(path);
    	return sounds.get(path);
    }
    
 // call on game start
    public static void init(AssetWatcher.Listener listener) {
    	AssetWatcher watcher = new AssetWatcher(listener);

    	watcher.add(getImage("images/bg.png"));
    	watcher.add(getImage("images/diamond.png"));
    	watcher.add(getImage("images/block.png"));
    	watcher.add(getImage("images/bonusIcon.png"));
    	watcher.add(getImage("images/btnBack.png"));
    	watcher.add(getImage("images/btnContinue.png"));
    	watcher.add(getImage("images/btnExit.png"));
    	watcher.add(getImage("images/btnScores.png"));
    	watcher.add(getImage("images/btnStart.png"));
    	watcher.add(getImage("images/bullockSkull.png"));
    	watcher.add(getImage("images/cactus1.png"));
    	watcher.add(getImage("images/cactus2.png"));
    	watcher.add(getImage("images/cactus3.png"));
    	watcher.add(getImage("images/cartridge.png"));
    	watcher.add(getImage("images/coin.png"));
    	watcher.add(getImage("images/coinIcon.png"));
    	watcher.add(getImage("images/finishFlag.png"));
    	watcher.add(getImage("images/gameOver.png"));
    	watcher.add(getImage("images/heartIcon.png"));
    	watcher.add(getImage("images/idle.png"));
    	watcher.add(getImage("images/loading.png"));
    	watcher.add(getImage("images/menuBg.png"));
    	watcher.add(getImage("images/run.png"));
    	watcher.add(getImage("images/jumpLeft.png"));
    	watcher.add(getImage("images/jumpRight.png"));
    	watcher.add(getImage("images/idleLeft.png"));
    	watcher.add(getImage("images/runLeft.png"));
    	watcher.add(getImage("images/runRight.png"));
    	watcher.add(getImage("images/idleRight.png"));
    	watcher.add(getImage("images/stone.png"));
    	watcher.add(getImage("images/stoneSmall.png"));
    	watcher.add(getImage("images/tree.png"));
    	watcher.add(getImage("images/weapon.png"));
    	// sounds
    	watcher.add(getSound("sounds/point"));
    	watcher.add(getSound("sounds/running"));
    	
    	watcher.start();
    }
    
    private static void registerImage(String path) {
    	if (!images.containsKey(path)) {
		    images.put(path, PlayN.assets().getImage(path));
    	}
    }
    
    private static void registerSound(String path) {
    	if (!sounds.containsKey(path)) {
    		sounds.put(path, PlayN.assets().getSound(path));
		}
    }
    
}
