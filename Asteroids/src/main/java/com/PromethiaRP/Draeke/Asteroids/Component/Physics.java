package com.PromethiaRP.Draeke.Asteroids.Component;

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
	protected float accelModifier = .001f;
	protected float maxSpeed = 1.2f;
	
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
	
	protected Structure struct;
	
	public Physics(Structure struct, float mass, float moment) {
		this.struct = struct;
		setMass(mass);
		setMoment(moment);
		messageTypes.add(MessageType.IMPULSE);
		messageTypes.add(MessageType.UPDATE_DELTA);
	}
	
	public void applyDrag(float percent) {
		velocityX *= percent;
		velocityY *= percent;
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
	public void setVelocityX(float x) {
		this.velocityX = x;
	}
	public void setVelocityY(float y) {
		this.velocityY = y;
	}
	public void setVelocityR(float r) {
		this.velocityR = r;
	}
	public void setVelocity(float velX, float velY, float velR) {
		velocityX = velX;
		velocityY = velY;
		velocityR = velR;
	}
	
	// Getters for Movement fields
	public float getVelocityX() {
		return this.velocityX;
	}
	public float getVelocityY() {
		return this.velocityY;
	}
	public float getVelocityR() {
		return this.velocityR;
	}
	
	
	public void applyForce(float forceX, float forceY) {
		this.forceX += forceX;
		this.forceY += forceY;
	}
	
	public void applyTorque(float torque) {
		this.torque += torque;
	}
	
	public void applyForwardForce(float force) {
		float forceX = force * (float)Math.cos(struct.getRotation());
		float forceY = force * (float)Math.sin(struct.getRotation());
		applyForce(forceX, forceY);
	}
	
	/**
	 * Applies a force at PI/2 radians to rotation
	 * @param force
	 */
	public void applySidewaysForce(float force) {
		float forceX = force * (float)Math.cos(struct.getRotation() + Math.PI/2);
		float forceY = force * (float)Math.sin(struct.getRotation() + Math.PI/2);
		applyForce(forceX, forceY);
	}
	
	public void update(int delta) {

		
		
		float accelerationX = invMass * forceX;
		float accelerationY = invMass * forceY;
		float accelerationR = invMoment * torque;
		
		//float accelMag2 = accelerationX * accelerationX + accelerationY * accelerationY;
		//float accelMag = (float)Math.sqrt(accelMag2);
		
		//if (Math.abs(velocityX + accelerationX * delta) < maxSpeed) {
			velocityX += accelerationX ;
		//}
		//if (Math.abs(velocityY + accelerationY * delta) < maxSpeed) {
			velocityY += accelerationY ;
		//}
		
		velocityR += accelerationR ;
		struct.moveRotation(velocityR * delta * this.turnSpeedModifier);
		struct.moveX(velocityX * delta * this.speedModifier);
		struct.moveY(velocityY * delta * this.speedModifier);

		applyDrag(.9f);
		
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
				throw new IllegalMessageException("Physics did not receive ImpulseMessage with the corresponding type IMPULSE.");
			}
			break;
		case UPDATE_DELTA:
			if (msg instanceof UpdateMessage) {
				UpdateMessage um = (UpdateMessage)msg;
				this.update(um.delta);
			} else {
				throw new IllegalMessageException("Physics did not receive UpdateMessage with the corresponding type UPDATE_DELTA.");
			}
			break;
		default:
			break;
		}
	}

	
}
