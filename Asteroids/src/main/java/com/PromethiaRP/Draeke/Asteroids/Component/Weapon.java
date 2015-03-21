package com.PromethiaRP.Draeke.Asteroids.Component;

import com.PromethiaRP.Draeke.Asteroids.Exceptions.IllegalMessageException;
import com.PromethiaRP.Draeke.Asteroids.Messages.ActionMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

public class Weapon extends Component{

	protected int weaponCooldown;
	protected int numberOfShots;
	protected float[] shotAngles;
	protected int bulletLife;
	protected Structure struct;
	
	public Weapon(Structure struct, int cooldown, int numShots, float[] shotAngles, int bulletLife) {
		weaponCooldown = cooldown;
		numberOfShots = numShots;
		this.shotAngles = shotAngles;
		this.bulletLife = bulletLife;
		this.struct = struct;
		
		this.messageTypes.add(MessageType.UPDATE_DELTA);
		this.messageTypes.add(MessageType.ACTION_SHOOT);
	}

	
	public void handleMessage(MessageType type, Message msg) {
		switch (type) {
		case ACTION_SHOOT:
			if (msg instanceof ActionMessage) {
				ActionMessage am = (ActionMessage) msg;
				
			} else {
				throw new IllegalMessageException("Weapon did not receive ActionMessage with the corresponding type ACTION_SHOOT.");
			}
			break;
		case UPDATE_DELTA:
			
			break;
		default:
			
			break;
		}
	}
}
