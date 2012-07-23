package pl.pepuch.wildjoe.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import pl.pepuch.wildjoe.core.WildJoe;

public class WildJoeJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("pl/pepuch/wildjoe/resources");
    PlayN.run(new WildJoe());
  }
}
