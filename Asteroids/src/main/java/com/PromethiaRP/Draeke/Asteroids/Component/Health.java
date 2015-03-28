package com.PromethiaRP.Draeke.Asteroids.Component;

import com.PromethiaRP.Draeke.Asteroids.GameWorld;
import com.PromethiaRP.Draeke.Asteroids.Exceptions.IllegalMessageException;
import com.PromethiaRP.Draeke.Asteroids.Messages.InteractionHurtMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;


public class Health extends Component{

	private int lifePoints;
	public Health(int life) {
		lifePoints = life;
		this.messageTypes.add(MessageType.HURT_INTERACTION);
	}

	public boolean isAlive() {
		return lifePoints > 0;
	}
	
	/**
	 * 
	 * @param damage The amount of damage to inflict
	 * @return True if the entity has died as a result of the injury
	 */
	public boolean hurt(int damage) {
		lifePoints -= damage;
		return lifePoints <= 0;
	}
	
	public void setHealth(int health) {
		lifePoints = health;
	}
	@Override
	public void handleMessage(GameWorld gameWorld, MessageType type, Message msg) {
		switch (type) {
		case HURT_INTERACTION:
			if (msg instanceof InteractionHurtMessage) {
				InteractionHurtMessage ihg = (InteractionHurtMessage) msg;
				hurt(ihg.damage);
				if ( ! isAlive()) {
					entity.kill();
				}
			} else {
				throw new IllegalMessageException("Health did not receive InteractionHurtMessage with the corresponding type HURT_INTERACTION");
			}
			break;
		default:
			
			break;
		}
	}
}
