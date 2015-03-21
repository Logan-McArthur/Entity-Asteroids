package com.PromethiaRP.Draeke.Asteroids.Component;

import java.util.HashSet;
import java.util.Set;

import com.PromethiaRP.Draeke.Asteroids.Entity;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

public abstract class Component {
//	private int componentID;
	
//	public Component(int ID) {
//		componentID = ID;
//	}
	
	protected Entity entity;
	
	protected Set<MessageType> messageTypes = new HashSet<MessageType>();
	
	public Component() {
		
	}
	
	public void setEntityContainer(Entity ent) {
		entity = ent;
		ent.addListening(messageTypes);
	}
	
	public Set<MessageType> getListeningFor() {
		return messageTypes;
	}
	
	//public abstract void update(int delta);
	
	public abstract void handleMessage(MessageType type, Message msg);
	
	public boolean receives(MessageType type) {
		return messageTypes.contains(type);
	}
}
