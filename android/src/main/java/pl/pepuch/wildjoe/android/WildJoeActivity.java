package pl.pepuch.wildjoe.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import pl.pepuch.wildjoe.core.WildJoe;

public class WildJoeActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("pl/pepuch/wildjoe/resources");
    PlayN.run(new WildJoe());
  }
}
