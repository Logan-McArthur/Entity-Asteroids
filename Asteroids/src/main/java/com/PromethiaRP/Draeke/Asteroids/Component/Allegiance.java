package com.PromethiaRP.Draeke.Asteroids.Component;

import com.PromethiaRP.Draeke.Asteroids.GameWorld;
import com.PromethiaRP.Draeke.Asteroids.Exceptions.IllegalMessageException;
import com.PromethiaRP.Draeke.Asteroids.Messages.CollisionMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.InteractionHurtMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

public class Allegiance extends Component{

	public Allegiance() {
		
		this.addAllTypes(
				MessageType.ENTITY_COLLIDE);
	}
	
	public boolean isHostile(Allegiance other) {
		return true;
	}
	
	public void handleMessage(GameWorld gameWorld, MessageType type, Message msg) {
		switch (type) {
		case ENTITY_COLLIDE:
			if (msg instanceof CollisionMessage) {
				CollisionMessage cm = (CollisionMessage) msg;
				// TODO: Change the collision reasoning in Allegiance, checking that the entity is alive might not need to happen here
				//if (cm.other.isAlive()) {
					entity.dispatchMessage(MessageType.HURT_INTERACTION, new InteractionHurtMessage(cm.other, 1));
				//}
			} else {
				throw new IllegalMessageException("Allegiance did not receive CollisionMessage with the corresponding type ENTITY_COLLIDE.");
			}
			break;
		default:
			
			break;
		}
	}


}
