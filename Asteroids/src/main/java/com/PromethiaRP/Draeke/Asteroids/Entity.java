package com.PromethiaRP.Draeke.Asteroids;

import java.util.HashSet;
import java.util.Set;

import com.PromethiaRP.Draeke.Asteroids.Component.Component;
import com.PromethiaRP.Draeke.Asteroids.Messages.EntityDieMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

public class Entity {
	
	protected GameWorld gameWorld;
	
	protected boolean alive = true;
	
	protected Set<MessageType> messageTypes = new HashSet<MessageType>();
	
	protected Set<Component> components = new HashSet<Component>();
	
	public final String name;
	
	public Entity(GameWorld gameWorld, String name) {
		this.name = name;
		this.gameWorld = gameWorld;
	}
//	public Entity(GameWorld gameWorld) {
//		this.gameWorld = gameWorld;
//	}
	
	/**
	 * Sets alive to false, does nothing else
	 */
	public void kill() {
		EntityDieMessage edm = new EntityDieMessage();
		dispatchMessage(MessageType.ENTITY_DIE, edm);
		alive = false;
	}
	
	
	
	public void addComponent(Component cp) {
		components.add(cp);
		cp.setEntityContainer(this);
	}
	
	public void addComponents(Component... cps) {
		for (Component cp : cps) {
			addComponent(cp);
		}
	}
	
	public void addListening(Set<MessageType> messages) {
		messageTypes.addAll(messages);
	}
	
	public boolean isVisible() {
		return isAlive();
	}
	
	public void update(int delta){
		
	}
	
	/**
	 * Called by the game to tell an Entity that it has collided with
	 * Should not interfere with Entity other
	 * Entity other
	 * @param other
	 */
	public void collide(Entity other) {
		
	}
	
	public boolean isAlive() {
		// Alive should be set by the health component through the message system?
		return alive;
	}
	
	public void dispatchMessage(MessageType msgType, Message msg) {
		// Dispatches a message to all components that will receive it.
		for (Component cp : components) {
			if (cp.receives(msgType)) {
				cp.handleMessage(gameWorld, msgType, msg);
			}
		}
	}
	
}
