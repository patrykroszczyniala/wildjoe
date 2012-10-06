package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;

import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.MummyModel;
import pl.pepuch.wildjoe.view.MummyView;

public class Mummy extends DynamicActor {
	
	// block on which mummy is moving; updated by WirldContactListener
	private Block block;
	private SpiderWeapon weapon;

	public Mummy(GameWorld world, Vec2 position) {
		model = new MummyModel(world, position);
		view = new MummyView(model());
		go();
		turnRight();
		model().body().setUserData(this);
		weapon = new SpiderWeapon(model().gameWorld(), model().position());
		weapon.model().setFrequency(1/5f); // one cartridge per one second (1/1 = 1 ;))
		model().gameWorld().add(weapon);
	}
	
	public void paint(float alpha) {
		view().paint(alpha);
		weapon.view().paint(alpha);
		model().drawDebugData();
	}
	
	public void update(float delta) {
		view().update(delta);
		weapon.update(delta);
		weapon.model().setPosition(model().position());
		
		if ((block!=null && model().isTurnedRight() && model().position().x>=(block.model().position().x+block.model().width()-1f))) {
			turnLeft();;
		}
		if ((block!=null && model().isTurnedLeft() && model().position().x<=block.model().position().x)) {
			turnRight();
		}

		if (model().gameWorldPosition().x-model().gameWorldPositionBefore().x==0) {
			if (model().isTurnedRight()) {
				turnLeft();
			}
			else if (model().isTurnedLeft()) {
				turnRight();
			}
		}

		if (model().isTurnedRight()) {
			makeStep();

			Vec2 p1 = new Vec2(model().body().getPosition().x+5.0f, model().position().y);
			Vec2 p2 = new Vec2(model().position().x, model().position().y-(model().height()/2));
			model().queryAABB(p1, p2);
		}
		else if (model().isTurnedLeft()) {
			makeStep();
			
			Vec2 p1 = new Vec2(model().body().getPosition().x-5.0f, model().position().y);
			Vec2 p2 = new Vec2(model().position().x, model().position().y-(model().height()/2));
			model().queryAABB(p1, p2);
		}
	}
	
	public void shoot() {
		float impulse = 2;
		if (model().isTurnedLeft()) {
			weapon.shoot(new Vec2(impulse/-1, -2));
		}
		if (model().isTurnedRight()) {
			weapon.shoot(new Vec2(impulse, -2));
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		weapon.destroy();
	}

	@Override
	public MummyModel model() {
		return (MummyModel)model;
	}

	@Override
	public MummyView view() {
		return (MummyView)view;
	}
	
	public void setBlock(Block block) {
		this.block = block;
	}
	
	public Block block() {
		return block;
	}
	
	@Override
	public void turnLeft() {
		view().turnLeft();
		super.turnLeft();
	}
	
	@Override
	public void turnRight() {
		view().turnRight();
		super.turnRight();
	}
	
}
