package com.PromethiaRP.Draeke.Asteroids.Component;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import com.PromethiaRP.Draeke.Asteroids.Entity;

public class Body extends Component{

	public Body(Entity en) {
		super(en);
		
	}

	
	protected Polygon structure;
	protected float centerOffsetX = 0.0f;
	protected float centerOffsetY = 0.0f;
	protected int frontVertex;
	protected float scaleX = 1.0f;
	protected float scaleY = 1.0f;

	// Mostly Consistent
	protected float invMass;
	protected float invMoment;
	
	protected float centerX = 0.0f;
	protected float centerY = 0.0f;

	protected float velocityX = 0.0f;
	protected float velocityY = 0.0f;

	protected float velocityR = 0.0f;

	protected float rotation = 0.0f;

	protected float forceX = 0.0f;
	protected float forceY = 0.0f;

	protected float torque = 0.0f;
	
	public void setMass(float mass) {
		if (mass <= 0) {
			invMass = 0;
		} else {
			invMass = 1.0f / mass;
		}
	}
	
	public void setStructure(float[] points, float centerOffsetX, float centerOffsetY, int front) {
		structure = new Polygon(points);
		this.centerOffsetX = centerOffsetX;
		this.centerOffsetY = centerOffsetY;
		frontVertex = front;

	}

	public Shape getTransform() {
		Transform trans = Transform.createRotateTransform(rotation, centerOffsetX, centerOffsetY);
		return structure.transform(Transform.createScaleTransform(scaleX, scaleY)).transform(trans).transform(Transform.createTranslateTransform(centerX - centerOffsetX, centerY - centerOffsetY));
	}

	
	/**
	 * 
	 * @return The actual center x coordinate
	 */
	public float getTrueCenterX() {
		return centerX + centerOffsetX;
	}
	
	public void setPositionX(float x) {
		this.centerX = x;
	}
	public void setPositionY(float y) {
		this.centerY = y;
	}
	public void setRotation(float r) {
		this.rotation = r;
	}
	public float getRotation() {
		return this.rotation;
	}
	public void setVelocityX(float x) {
		this.velocityX = x;
	}
	public void setVelocityY(float y) {
		this.velocityY = y;
	}
	public float getVelocityX() {
		return this.velocityX;
	}
	public float getVelocityY() {
		return this.velocityY;
	}
	public void setVelocityR(float r) {
		this.velocityR = r;
	}
	public float getVelocityR() {
		return this.velocityR;
	}
	/**
	 * 
	 * @return The actual center y coordinate
	 */
	public float getTrueCenterY() {
		return centerY + centerOffsetY;
	}
	
	public void applyForce(float forceX, float forceY) {
		this.forceX += forceX;
		this.forceY += forceY;
	}
	
	public void applyForwardForce(float force) {
		float forceX = force * (float)Math.cos(rotation);
		float forceY = force * (float)Math.sin(rotation);
		applyForce(forceX, forceY);
	}
	
	/**
	 * Applies a force at PI/2 radians to rotation
	 * @param force
	 */
	public void applySidewaysForce(float force) {
		float forceX = force * (float)Math.cos(rotation + Math.PI/2);
		float forceY = force * (float)Math.sin(rotation + Math.PI/2);
		applyForce(forceX, forceY);
	}
	
	public void update(int delta) {

		float accelerationX = invMass * forceX;
		float accelerationY = invMass * forceY;
		float accelerationR = invMoment * torque;
		
		//if (Math.abs(velocityX + accelerationX * delta) < maxSpeed) {
			velocityX += accelerationX * delta;
		//}
		//if (Math.abs(velocityY + accelerationY * delta) < maxSpeed) {
			velocityY += accelerationY * delta;
		//}
			velocityR += accelerationR * delta;
		rotation += velocityR * delta;
		centerX += velocityX * delta;
		centerY += velocityY * delta;

	}
}
