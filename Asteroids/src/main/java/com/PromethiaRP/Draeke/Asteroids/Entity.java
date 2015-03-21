package com.PromethiaRP.Draeke.Asteroids;

import java.util.HashSet;
import java.util.Set;

import com.PromethiaRP.Draeke.Asteroids.Component.Component;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

public abstract class Entity {

//	protected int entityID;
//	
	
	
//	protected Map<MessageType, Component> messageMap;
	
//	protected float accelerationX = 0.0f;
//	protected float accelerationY = 0.0f;
	
//	protected float accelerationDirection = 0.0f;
	
//	protected Body body;
//	protected Health hitPoints;// = new Health();
	
	protected boolean alive = true;
	
	protected Set<MessageType> messageTypes = new HashSet<MessageType>();
	
	protected Set<Component> components = new HashSet<Component>();
	
	/**
	 * Sets alive to false, does nothing else
	 */
	public void kill() {
		alive = false;
	}
	
//	public Entity(int ID) {
//		entityID = ID;
//	}
	
	public Entity() {
		
	}
	
	public void addComponent(Component cp) {
		components.add(cp);
		cp.setEntityContainer(this);
	}
	
	public void addListening(Set<MessageType> messages) {
		messageTypes.addAll(messages);
	}
	
	/**
	 * Sets the base rotation speed
	 * Can be either 1, 0, or -1
	 * @param direction
	 */
//	public void setRotating(int direction) {
//		velocityR = direction;
//	}
	
	/**
	 * Sets the base acceleration direction
	 * Can be either 1, 0, or -1
	 * @param direction
	 */
//	public void setAccelerating(int direction) {
//		accelerationDirection = direction;
//	}
	
	
/**
 * Takes accelerationDirection and modifies it according to rotation and accelModifier
 * accelerationX/Y is set to these modified values
 */
//	public void accelerate() {
//		accelerationY = (float) (accelerationDirection * Math.sin(rotation) * accelModifier);
//		accelerationX = (float) (accelerationDirection * Math.cos(rotation) * accelModifier);
//	}
	
	
	
//	public boolean doesCollide(Entity other) {
//		return body.getTransform().intersects(other.body.getTransform()) && isAlive() && other.isAlive();
//	}
	
	
	
	public boolean isVisible() {
		return isAlive();
	}
	
	public abstract void update(int delta);
	
	/**
	 * Called by the game to tell an Entity that it has collided with
	 * Should not interfere with Entity other
	 * Entity other
	 * @param other
	 */
	public abstract void collide(Entity other);
	
	public boolean isAlive() {
		// Alive should be set by the health component through the message system?
		return alive;
	}
	
	public void dispatchMessage(MessageType msgType, Message msg) {
		// Dispatches a message to all components that will receive it.
		for (Component cp : components) {
			if (cp.receives(msgType)) {
				cp.handleMessage(msgType, msg);
			}			
		}
	}
}
