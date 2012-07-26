package pl.pepuch.wildjoe.controller;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import pl.pepuch.wildjoe.model.PlayerModel;
import pl.pepuch.wildjoe.view.PlayerView;
import playn.core.Key;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.Listener;
import playn.core.Keyboard.TypedEvent;
import playn.core.PlayN;

public class Player extends DynamicActor implements Listener {
	
	private boolean isMovingLeft;
	private boolean isMovingRight;

	public Player(World world) {
		model = new PlayerModel(world);
		view = new PlayerView((PlayerModel)model);
		PlayN.keyboard().setListener(this);
		model.getBody().setUserData(this);
	}
	
	public void paint(float alpha) {
		view.paint(alpha);
	}
	
	public void update(float delta) {
		view.update(delta);
		if (isMovingRight()) {
			((PlayerView)view).setAnimationIdleVisible(false);
			((PlayerView)view).setAnimationLeftVisible(false);
			((PlayerView)view).setAnimationRightVisible(true);
		}
		else if (isMovingLeft()) {
			((PlayerView)view).setAnimationIdleVisible(false);
			((PlayerView)view).setAnimationRightVisible(false);
			((PlayerView)view).setAnimationLeftVisible(true);
		}
		else {
			((PlayerView)view).setAnimationRightVisible(false);
			((PlayerView)view).setAnimationLeftVisible(false);
			((PlayerView)view).setAnimationIdleVisible(true);
		}
	}

	public void moveLeft() {
		float x = model.getPosition().x-0.1f;
		float y = model.getPosition().y;
		setPosition(new Vec2(x, y));
	}
	
	public void moveRight() {
		float x = model.getPosition().x+0.1f;
		float y = model.getPosition().y;
		setPosition(new Vec2(x, y));
	}
	
	public void jump() {
		if (!isJumping()) {
			float impulse = model.getBody().getMass() * 40;
			model.getBody().applyLinearImpulse(new Vec2(0, impulse), model.getBody().getWorldCenter());
		}
	}
	
	public boolean isMovingLeft() {
		return isMovingLeft;
	}
	
	public boolean isMovingRight() {
		return isMovingRight;
	}
	
	public boolean isJumping() {
		return (int)model.getBody().getLinearVelocity().y!=0 ? true : false;
	}
	
	public void setPosition(Vec2 position) {
		model.setPosition(position);
	}
	
	public Listener getListener() {
		return this;
	}

	@Override
	public void onKeyDown(Event event) {
		if (event.key().name().equalsIgnoreCase(Key.RIGHT.name())) {
			isMovingRight = true;
		}
		else if (event.key().name().equalsIgnoreCase(Key.LEFT.name())) {
			isMovingLeft = true;
		}
		else if (event.key().name().equalsIgnoreCase(Key.SPACE.name())) {
			jump();
		}
	}

	@Override
	public void onKeyTyped(TypedEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onKeyUp(Event event) {
		if (event.key().name().equalsIgnoreCase(Key.RIGHT.name())) {
			isMovingRight = false;
		}
		else if (event.key().name().equalsIgnoreCase(Key.LEFT.name())) {
			isMovingLeft = false;
		}
	}
	
}
