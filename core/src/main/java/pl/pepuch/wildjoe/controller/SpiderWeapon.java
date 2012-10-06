package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.SpiderWeaponModel;
import pl.pepuch.wildjoe.view.SpiderWeaponView;
import playn.core.PlayN;


public class SpiderWeapon extends DynamicActor {
	
	private long frequencyTime;
	private int maxNumberOfSpiders;
	private int spidersCounter;


	private boolean isShootPossible;
	private boolean shooted;
	
	public SpiderWeapon(GameWorld world, Vec2 position) {
		model = new SpiderWeaponModel(world, position);
		view = new SpiderWeaponView(model());
		model().setPosition(position);
		frequencyTime = 0;
		isShootPossible = false;
		shooted = false;
		maxNumberOfSpiders = 1;
		spidersCounter = 0;
		model().body().setUserData(this);
	}
	
	// TODO *1.2 ?? TO POWINNO BYC PRZELICZANE PORZADNIE !!
	public void shoot(final Vec2 impulse) {
		if (isShootPossible && spidersCounter<maxNumberOfSpiders) {
			final Spider spider = new Spider(model().gameWorld(), new Vec2(0, 0), this);
			
			PlayN.invokeLater(new Runnable() {
				@Override
				public void run() {
					float x = 0;
					float y = model().body().getWorldCenter().y+model().height()/2;
					// actor is moving left
					if (impulse.x<0) {
						x = model().body().getPosition().x-spider.model().width()-0.1f;
					}
					// actor is moving right
					else {
						x = model().body().getPosition().x+model().width()+0.1f;
					}
					
					spider.model().setPosition(new Vec2(x, y));
					spider.model().setSpeed(0.1f);
					model().gameWorld().add(spider);
					spider.model().body().applyLinearImpulse(impulse, model().body().getWorldCenter());
				}
			});
			shooted = true;
		}
	}

	public void paint(float alpha) {
		view().paint(alpha);
	}
	
	public void update(float delta) {
		// frequency time
		if (frequencyTime*(model().frequency()/1000)>=1) {
			frequencyTime = 0;
			isShootPossible = true;
		}
		else {
			frequencyTime += delta;
			if (shooted) {
				isShootPossible = false;
				shooted = false;
			}
		}
		view().update(delta);
	}

	@Override
	public SpiderWeaponModel model() {
		return (SpiderWeaponModel)model;
	}

	@Override
	public SpiderWeaponView view() {
		return (SpiderWeaponView)view;
	}


	public long maxNumberOfSpiders() {
		return maxNumberOfSpiders;
	}


	public void setMaxNumberOfSpiders(int maxNumberOfSpiders) {
		this.maxNumberOfSpiders = maxNumberOfSpiders;
	}
	
	public void spidersCounterIncrement() {
		this.spidersCounter++;
	}
	
	public void spidersCounterDecrement() {
		this.spidersCounter--;
	}

}
