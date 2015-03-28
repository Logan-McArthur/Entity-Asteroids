package com.PromethiaRP.Draeke.Asteroids.Component;

import org.newdawn.slick.geom.Vector2f;

import com.PromethiaRP.Draeke.Asteroids.GameWorld;
import com.PromethiaRP.Draeke.Asteroids.Exceptions.IllegalMessageException;
import com.PromethiaRP.Draeke.Asteroids.Messages.ImpulseMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;
import com.PromethiaRP.Draeke.Asteroids.Messages.UpdateMessage;

public class Physics extends Component{

	public static final float MAX_FORCE = 10.0f;
	public static final float MAX_TORQUE = 10.0f;
	
	protected float speedModifier = .2f;
	protected float turnSpeedModifier = .0035f;
	protected float accelModifier = .1f;
	protected float accelRModifier = .08f;
	protected float maxSpeed = 2f;
	protected float dragFactor = 1f;
	// Physics fields
	protected float invMass;
	protected float invMoment;
	
	// How the Body moves through space
//	protected float velocityX = 0.0f;
//	protected float velocityY = 0.0f;
	protected Vector2f velocity = new Vector2f(0.0f, 0.0f);
	protected float velocityR = 0.0f;

	// How the Body accelerates
//	protected float forceX = 0.0f;
//	protected float forceY = 0.0f;
	protected Vector2f force = new Vector2f(0.0f, 0.0f);
	protected float torque = 0.0f;
	
	protected Structure struct;
	
	public Physics(Structure struct, float mass, float moment) {
		this.struct = struct;
		setMass(mass);
		setMoment(moment);
		messageTypes.add(MessageType.IMPULSE);
		messageTypes.add(MessageType.UPDATE_DELTA);
	}
	
	public void setDrag(float percent) {
		this.dragFactor = percent;
	}
	public float getDrag() {
		return dragFactor;
	}
	
	public void applyDrag(float percent) {
		velocity.scale(percent);
		velocityR *= percent;
	}
	
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
	
	
	
	// Setters for Movement fields
//	public void setVelocityX(float x) {
//		this.velocity.x = x;
//	}
//	public void setVelocityY(float y) {
//		this.velocity.y = y;
//	}
	public void setVelocity(Vector2f vel) {
		this.velocity.set(vel);
	}
	public void setVelocityR(float r) {
		this.velocityR = r;
	}
	public void setVelocity(Vector2f vel, float velR) {
		setVelocity(vel);
		velocityR = velR;
	}
	
	// Getters for Movement fields
	public float getVelocityX() {
		return this.velocity.x;
	}
	public float getVelocityY() {
		return this.velocity.y;
	}
	/** 
	 * Returns an immutable copy of the velocity vector
	 * @return velocity
	 */
	public Vector2f getVelocity() {
		return velocity.copy();
	}
	public float getVelocityR() {
		return this.velocityR;
	}
	
	
//	public void applyForce(float forceX, float forceY) {
//		this.forceX += forceX;
//		this.forceY += forceY;
//	}
	public void applyForce(Vector2f force) {
		this.force.add(force);
	}
	
	public void applyTorque(float torque) {
		this.torque += torque;
	}
	
	public void applyForwardForce(float force) {
		float forceX = force * (float)Math.cos(struct.getRotation());
		float forceY = force * (float)Math.sin(struct.getRotation());
		Vector2f apply = new Vector2f(forceX, forceY);
		applyForce(apply);
	}
	
	/**
	 * Applies a force at PI/2 radians to rotation
	 * @param force
	 */
	public void applySidewaysForce(float force) {
		float forceX = force * (float)Math.cos(struct.getRotation() + Math.PI/2);
		float forceY = force * (float)Math.sin(struct.getRotation() + Math.PI/2);
		Vector2f apply = new Vector2f(forceX, forceY);
		applyForce(apply);
	}
	
	public void update(int delta) {

		
		Vector2f acceleration = force.scale(invMass * accelModifier);
//		float accelerationX = invMass * forceX * accelModifier;
//		float accelerationY = invMass * forceY * accelModifier;
		float accelerationR = invMoment * torque * accelRModifier;
		
		if (velocity.copy().add(acceleration).length() < maxSpeed){
			velocity.add(acceleration);
		}
		
		velocityR += accelerationR ;
		struct.moveRotation(velocityR * delta * this.turnSpeedModifier);
//		struct.moveX(velocityX * delta * this.speedModifier);
//		struct.moveY(velocityY * delta * this.speedModifier);
		struct.move(velocity.copy().scale(delta*this.speedModifier));
		applyDrag(this.dragFactor);
		
		
		
		this.force.set(0f, 0f);
		this.torque = 0;
	}

	@Override
	public void handleMessage(GameWorld gameWorld, MessageType type, Message msg) {
		switch (type) {
		case IMPULSE:
			if (msg instanceof ImpulseMessage) {
				ImpulseMessage im = (ImpulseMessage) msg;
				this.applyForwardForce(im.forwardForce);
				this.applySidewaysForce(im.sidewaysForce);
				this.applyTorque(im.torque);
			} else {
				throw new IllegalMessageException("Physics did not receive ImpulseMessage with the corresponding type IMPULSE.");
			}
			break;
		case UPDATE_DELTA:
			if (msg instanceof UpdateMessage) {
				UpdateMessage um = (UpdateMessage)msg;
				this.update(um.delta);
				
				gameWorld.resolveCollision(struct.getTransform(), entity);
				
			} else {
				throw new IllegalMessageException("Physics did not receive UpdateMessage with the corresponding type UPDATE_DELTA.");
			}
			break;
		default:
			break;
		}
	}

	
}
