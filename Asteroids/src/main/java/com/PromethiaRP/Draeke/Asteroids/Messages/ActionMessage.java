package com.PromethiaRP.Draeke.Asteroids.Messages;

public class ActionMessage extends Message{

	public boolean perform;
	
	public ActionMessage(boolean perform) {
		this.perform = perform;
	}
}
