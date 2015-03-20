package com.PromethiaRP.Draeke.Asteroids.Messages;

public class ActionMessage extends Message{

	public boolean perform;
	
	public ActionMessage(String type, boolean perform) {
		super(type);
		this.perform = perform;
	}
}
