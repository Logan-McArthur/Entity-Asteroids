package com.PromethiaRP.Draeke.Asteroids;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public abstract class Entity {

	protected float speedModifier = .2f;
	protected float turnSpeedModifier = .0025f;
	protected float accelModifier = .001f;
	protected float maxSpeed = 1.2f;
	
	protected float rotation = 0.0f;
	protected float centerX = 0.0f;
	protected float centerY = 0.0f;
	
	protected float velocityX = 0.0f;
	protected float velocityY = 0.0f;
	protected float velocityR = 0.0f;
	
	protected float accelerationX = 0.0f;
	protected float accelerationY = 0.0f;
	
	protected float accelerationDirection = 0.0f;
	
	protected float scaleX = 1.0f;
	protected float scaleY = 1.0f;
	
	protected Polygon structure;
	protected float centerOffsetX = 0.0f;
	protected float centerOffsetY = 0.0f;
	
	protected boolean alive = false;
	
	
	protected int frontVertex;
	
	public Entity(float x, float y) {
		centerX = x;
		centerY = y;
	}
	
	/**
	 * Sets the base rotation speed
	 * Can be either 1, 0, or -1
	 * @param direction
	 */
	public void setRotating(int direction) {
		velocityR = direction;
	}
	
	/**
	 * Sets the base acceleration direction
	 * Can be either 1, 0, or -1
	 * @param direction
	 */
	public void setAccelerating(int direction) {
		accelerationDirection = direction;
	}
	
	public void move(int delta) {
		if (Math.abs(velocityX + accelerationX * delta) < maxSpeed) {
			velocityX += accelerationX * delta;
		}
		if (Math.abs(velocityY + accelerationY * delta) < maxSpeed) {
			velocityY += accelerationY * delta;
		}
		rotation += velocityR * delta * turnSpeedModifier;
		centerX += velocityX * delta * speedModifier;
		centerY += velocityY * delta * speedModifier;
		
	}
/**
 * Takes accelerationDirection and modifies it according to rotation and accelModifier
 * accelerationX/Y is set to these modified values
 */
	public void accelerate() {
		accelerationY = (float) (accelerationDirection * Math.sin(rotation) * accelModifier);
		accelerationX = (float) (accelerationDirection * Math.cos(rotation) * accelModifier);
	}
	
	public Shape getTransform() {
		Transform trans = Transform.createRotateTransform(rotation, centerOffsetX, centerOffsetY);
		return structure.transform(Transform.createScaleTransform(scaleX, scaleY)).transform(trans).transform(Transform.createTranslateTransform(centerX - centerOffsetX, centerY - centerOffsetY));
	}
	
	public boolean doesCollide(Entity other) {
		return getTransform().intersects(other.getTransform()) && alive && other.alive;
	}
	
	public void setStructure(float[] points, float centerOffsetX, float centerOffsetY, int front) {
		structure = new Polygon(points);
		this.centerOffsetX = centerOffsetX;
		this.centerOffsetY = centerOffsetY;
		frontVertex = front;
		
	}
	/**
	 * 
	 * @return The actual center x coordinate
	 */
	public float getTrueCenterX() {
		return centerX + centerOffsetX;
	}
	/**
	 * 
	 * @return The actual center y coordinate
	 */
	public float getTrueCenterY() {
		return centerY + centerOffsetY;
	}
	
	public boolean isVisible() {
		return alive;
	}
	
	public abstract void update(int delta);
	
	/**
	 * Called by the game to tell an Entity that it has collided with
	 * Should not interfere with Entity other
	 * Entity other
	 * @param other
	 */
	public abstract void collide(Entity other);
	
}
