package pl.pepuch.wildjoe.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import pl.pepuch.wildjoe.core.WildJoe;

public class WildJoeHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("wildjoe/");
    PlayN.run(new WildJoe());
  }
}
