package com.PromethiaRP.Draeke.Asteroids.Component;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import com.PromethiaRP.Draeke.Asteroids.Entity;
import com.PromethiaRP.Draeke.Asteroids.Exceptions.IllegalMessageException;
import com.PromethiaRP.Draeke.Asteroids.Messages.ImpulseMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;
import com.PromethiaRP.Draeke.Asteroids.Messages.UpdateMessage;

public class Body extends Component{

	public Body(Polygon struct, int front, float centerOffX, float centerOffY, float mass, float moment) {
		this.setStructure(struct, centerOffX, centerOffY, front);
		setMass(mass);
		setMoment(moment);
		messageTypes.add(MessageType.IMPULSE);
		messageTypes.add(MessageType.UPDATE_DELTA);
	}
	
	// Geometry fields
	
	// How the Body looks
	protected Polygon structure;
	protected float centerOffsetX = 0.0f;
	protected float centerOffsetY = 0.0f;
	protected float scaleX = 1.0f;
	protected float scaleY = 1.0f;
	
	
	// Data that is used to shoot bullets
	// This shouldn't be here, instead give equivalent data to the weapon
//	protected int frontVertex;
	
	
	
	// Physics fields
	protected float invMass;
	protected float invMoment;
	
	// How the Body moves through space
	protected float velocityX = 0.0f;
	protected float velocityY = 0.0f;
	protected float velocityR = 0.0f;

	// How the Body accelerates
	protected float forceX = 0.0f;
	protected float forceY = 0.0f;
	protected float torque = 0.0f;
	
	// Where the Body is and how it is oriented
	protected float centerX = 0.0f;
	protected float centerY = 0.0f;
	protected float rotation = 0.0f;

	// Physics methods
	/**
	 * Returns the inverse of value if value > 0. Returns 0 otherwise.
	 * 
	 * @param value The value to be inversed
	 * @return
	 */
	private float getInverse(float value) {
		float inverse = 0.0f;
		if (value > 0) {
			inverse = 1.0f / value;
		}
		return inverse;
	}
	public void setMass(float mass) {
		this.invMass = getInverse(mass);
	}
	public void setMoment(float moment) {
		this.invMoment = getInverse(moment);
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
	
	public void setVelocityR(float r) {
		this.velocityR = r;
	}
	
	public float getVelocityX() {
		return this.velocityX;
	}
	public float getVelocityY() {
		return this.velocityY;
	}
	public float getVelocityR() {
		return this.velocityR;
	}
	// Geometry methods
	public void setStructure(float[] points, float centerOffX, float centerOffY, int front) {
		setStructure(new Polygon(points), centerOffX, centerOffY, front);
	}
	public void setStructure(Polygon struct, float centerOffX, float centerOffY, int front) {
		structure = struct;
		this.centerOffsetX = centerOffX;
		this.centerOffsetY = centerOffY;
		frontVertex = front;
	}

	public Shape getTransform() {
		Transform trans = Transform.createRotateTransform(rotation, centerOffsetX, centerOffsetY);
		return structure.transform(Transform.createScaleTransform(scaleX, scaleY)).transform(trans).transform(Transform.createTranslateTransform(centerX - centerOffsetX, centerY - centerOffsetY));
	}

	
	// Methods between Physics and Geometry
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
	
	public void applyForce(float forceX, float forceY) {
		this.forceX += forceX;
		this.forceY += forceY;
	}
	
	public void applyTorque(float torque) {
		this.torque += torque;
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

		this.forceX = 0;
		this.forceY = 0;
		this.torque = 0;
	}

	@Override
	public void handleMessage(MessageType type, Message msg) {
		switch (type) {
		case IMPULSE:
			if (msg instanceof ImpulseMessage) {
				ImpulseMessage im = (ImpulseMessage) msg;
				this.applyForwardForce(im.forwardForce);
				this.applySidewaysForce(im.sidewaysForce);
				this.applyTorque(im.torque);
			} else {
				throw new IllegalMessageException("Body did not receive ImpulseMessage with the corresponding type IMPULSE.");
			}
			break;
		case UPDATE_DELTA:
			if (msg instanceof UpdateMessage) {
				UpdateMessage um = (UpdateMessage)msg;
				this.update(um.delta);
			} else {
				throw new IllegalMessageException("Body did not receive UpdateMessage with the corresponding type UPDATE_DELTA.");
			}
			break;
		}
	}

}
