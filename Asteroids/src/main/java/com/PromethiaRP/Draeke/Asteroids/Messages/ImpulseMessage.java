package com.PromethiaRP.Draeke.Asteroids.Messages;

public class ImpulseMessage extends Message {

	public float forwardForce;
	public float sidewaysForce;
	public float torque;
	
	public ImpulseMessage(float forwardForce, float sidewaysForce, float torque) {
		this.forwardForce = forwardForce;
		this.sidewaysForce = sidewaysForce;
		this.torque = torque;
	}
}
