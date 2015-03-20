package com.PromethiaRP.Draeke.Asteroids.Messages;

public class UpdateMessage extends Message{

	public int delta;
	
	public UpdateMessage(int delta) {
		this.delta = delta;
	}

}
