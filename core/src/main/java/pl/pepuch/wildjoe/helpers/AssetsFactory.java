/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pepuch.wildjoe.helpers;

import java.util.HashMap;

import playn.core.Image;
import playn.core.PlayN;

/**
 *
 * @author proszczyniala
 */
public class AssetsFactory {
    
    private static HashMap<String, Image> images = new HashMap<String, Image>();
	
    public static Image getImage(String path) {
	if (!images.containsKey(path)) {
	    images.put(path, PlayN.assets().getImage(path));
	}
	
	return images.get(path);
    }
    
}
