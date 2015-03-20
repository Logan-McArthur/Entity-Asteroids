package com.PromethiaRP.Draeke.Asteroids.Component;

import com.PromethiaRP.Draeke.Asteroids.Entity;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;


public class Health extends Component{

	private int lifePoints;
	public Health(Entity en, int life) {
		super(en);
		lifePoints = life;
	}
	@Override
	public void update(int delta) {
		
	}

	public boolean isAlive() {
		return lifePoints == 0;
	}
	
	/**
	 * 
	 * @param damage The amount of damage to inflict
	 * @return True if the entity has died as a result of the injury
	 */
	public boolean hurt(int damage) {
		lifePoints = Math.max(0, lifePoints - damage);
		return isAlive();
	}
	
	public void setHealth(int health) {
		lifePoints = health;
	}
	@Override
	public void handleMessage(MessageType type, Message msg) {
		// TODO Auto-generated method stub
		
	}
}
