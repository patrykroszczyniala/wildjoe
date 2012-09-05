package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.WeaponModel;
import pl.pepuch.wildjoe.view.WeaponView;


public class Weapon extends DynamicActor {
	
	private long frequencyTime;
	private boolean isShootPossible;
	private boolean shooted;
	
	public Weapon(GameWorld world, Vec2 position) {
		model = new WeaponModel(world, position);
		view = new WeaponView(model());
		model().setPosition(position);
		frequencyTime = 0;
		isShootPossible = false;
		shooted = false;
		model().body().setUserData(this);
	}
	
	
	// TODO *1.2 ?? TO POWINNO BYC PRZELICZANE PORZADNIE !!
	public void shoot(Vec2 impulse) {
		if (isShootPossible) {
			float x = 0;
			float y = model().body().getWorldCenter().y+model().height()/2;
			Cartridge cartridge = new Cartridge(model().gameWorld(), new Vec2(x, y));
			
			// actor is moving left
			if (impulse.x<0) {
				x = model().body().getPosition().x-cartridge.model().width()-0.1f;
			}
			// actor is moving right
			else {
				x = model().body().getPosition().x+model().width()+0.1f;
			}
			
			cartridge.model().setPosition(new Vec2(x, y));
			cartridge.model().setSpeed(5f);
			cartridge.model().setRange(2f);
			model().gameWorld().add(cartridge);
			cartridge.model().body().applyLinearImpulse(impulse, model().body().getWorldCenter());
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
	public WeaponModel model() {
		return (WeaponModel)model;
	}

	@Override
	public WeaponView view() {
		return (WeaponView)view;
	}
		
}
