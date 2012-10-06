package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.core.world.GameWorld;

public class ActorFactory {

	public static final String BLOCK = "Block";
	public static final String MOVING_BLOCK = "MovingBlock";
	public static final String PLAYER = "Player";
	public static final String FINISH_FLAG = "FinishFlag";
	public static final String MUMMY = "Mummy";
	public static final String COIN = "Coin";
	public static final String DIAMOND = "Diamond";
	
	public DynamicActor getActor(String actorId, GameWorld gameWorld) throws Exception {
		
		if (actorId.equalsIgnoreCase(BLOCK)) {
			
		}
		else if (actorId.equalsIgnoreCase(PLAYER)) {
			
		}
		else if (actorId.equalsIgnoreCase(FINISH_FLAG)) {
			
		}
		else if (actorId.equalsIgnoreCase(MUMMY)) {
			
		}
		else if (actorId.equalsIgnoreCase(COIN)) {
			
		}
		else {
			throw new Exception("Undefined actor");
		}
		
		return null;
	}
	
}
