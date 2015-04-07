package com.PromethiaRP.Draeke.Asteroids.Component;

import org.newdawn.slick.geom.Vector2f;

import com.PromethiaRP.Draeke.Asteroids.GameWorld;
import com.PromethiaRP.Draeke.Asteroids.Exceptions.IllegalMessageException;
import com.PromethiaRP.Draeke.Asteroids.Messages.ActionMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.CountdownMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

// Weapon should handle the firing process,

// TODO: Create some form of spawner component 
// Should Spawner store the mountPoint? Should that be with Structure?
// If Structure contains the mountPoint, Spawner would dispatch a message saying to create one
// TODO: Consider having Weapon be an abstract class, where the subclasses build the appropriate projectile
public class Weapon extends Component{

	protected int weaponCooldown;
	protected int numberOfShots;
	protected Vector2f launchVelocity;
	protected int bulletLife;
	protected Physics phys;// struct;
	// TODO: Move mountPoint out
	protected Vector2f mountPoint;
	
	protected boolean onCooldown;
	
	public Weapon(Physics phys, Vector2f mountPoint, int cooldown, int numShots, Vector2f velocity, int bulletLife) {
	//public Weapon(Structure struct, Vector2f mountPoint, int cooldown, int numShots, float[] shotAngles, int bulletLife) {
		weaponCooldown = cooldown;
		numberOfShots = numShots;
		this.launchVelocity = velocity;
		this.bulletLife = bulletLife;
		//this.struct = struct;
		this.phys = phys;
		this.mountPoint = mountPoint;
		this.onCooldown = false;
		
//		this.messageTypes.add(MessageType.UPDATE_DELTA);
//		this.messageTypes.add(MessageType.ACTION_SHOOT);
		this.addAllTypes(
				MessageType.ACTION_SHOOT,
				MessageType.COUNTDOWN_FINISH);
	}

	
	public void startCooldown() {
		onCooldown = true;
		CountdownMessage cdm = new CountdownMessage(this, weaponCooldown, onCooldown);
		entity.dispatchMessage(MessageType.COUNTDOWN_BEGIN, cdm);
	}
	
	public void handleMessage(GameWorld gameWorld, MessageType type, Message msg) {
		switch (type) {
		case ACTION_SHOOT:
			if (msg instanceof ActionMessage) {
				ActionMessage am = (ActionMessage) msg;
				if (am.perform && !this.onCooldown) {
					gameWorld.constructBullet(phys.struct.getPosition().add(phys.struct.transformVector(mountPoint)), phys.struct.getRotation(), phys.getVelocity().add(phys.struct.transformVector(launchVelocity)), 0, bulletLife);
					startCooldown();
				}
			} else {
				throw new IllegalMessageException("Weapon did not receive ActionMessage with the corresponding type ACTION_SHOOT.");
			}
			break;
		case COUNTDOWN_FINISH:
			if (msg instanceof CountdownMessage) {
				CountdownMessage cm = (CountdownMessage)msg;
				this.onCooldown = false;
			} else {
				throw new IllegalMessageException("Weapon did not receive CountdownMessage with the corresponding type COUNTDOWN_FINISH.");
			}
			break;
		default:
			
			break;
		}
	}
}
