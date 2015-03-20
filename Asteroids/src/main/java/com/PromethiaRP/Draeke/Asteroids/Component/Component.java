package com.PromethiaRP.Draeke.Asteroids.Component;

import com.PromethiaRP.Draeke.Asteroids.Entity;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

public abstract class Component {
//	private int componentID;
	
//	public Component(int ID) {
//		componentID = ID;
//	}
	
	protected Entity entity;
	
	public Component(Entity en) {
		entity = en;
	}
//	public int getEntityID() {
//		return componentID;
//	}
	
	public abstract void update(int delta);
	
	public abstract void handleMessage(MessageType type, Message msg);
	
	public abstract boolean receives(MessageType type);
}
