package com.PromethiaRP.Draeke.Asteroids.Messages;

public class ImpulseMessage extends Message {

	public float force;
	
	public ImpulseMessage(String type, float force) {
		super(type);
		this.force = force;
	}
}
