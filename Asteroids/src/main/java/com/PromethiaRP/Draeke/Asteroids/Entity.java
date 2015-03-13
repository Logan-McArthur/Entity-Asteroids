package com.PromethiaRP.Draeke.Asteroids;

import com.PromethiaRP.Draeke.Asteroids.Component.Body;
import com.PromethiaRP.Draeke.Asteroids.Component.Health;

public abstract class Entity {

//	protected int entityID;
//	
	protected float speedModifier = .2f;
	protected float turnSpeedModifier = .0025f;
	protected float accelModifier = .001f;
	protected float maxSpeed = 1.2f;
	
	
	protected float accelerationX = 0.0f;
	protected float accelerationY = 0.0f;
	
	protected float accelerationDirection = 0.0f;
	
	protected Body body;
	protected Health hitPoints;// = new Health();
	
	
//	public Entity(int ID) {
//		entityID = ID;
//	}
	
	public Entity() {
		
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
	
	
	
	public boolean doesCollide(Entity other) {
		return body.getTransform().intersects(other.body.getTransform()) && isAlive() && other.isAlive();
	}
	
	
	
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
		
		return hitPoints.isAlive();
	}
}
