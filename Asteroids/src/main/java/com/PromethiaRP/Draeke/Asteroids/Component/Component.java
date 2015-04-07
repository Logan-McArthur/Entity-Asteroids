package com.PromethiaRP.Draeke.Asteroids.Component;

import java.util.HashSet;
import java.util.Set;

import com.PromethiaRP.Draeke.Asteroids.Entity;
import com.PromethiaRP.Draeke.Asteroids.GameWorld;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

// TODO: Compartmentalize the components
// Physics and Structure are tightly coupled right now
// Isn't it everything and Structure though?
// So what will every Entity have, almost without fail
// 		An orientation in space?
//		

// Is it bad to have some Components coupled together?
// Should there even be one Component superclass? The example had InputComponents and the like
// And do I want it so that just by having different components the Entities behave differently?

// If there is a weapon component and a weapon script, aren't they both supposed to accomplish the same thing?

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
	
	public void addAllTypes(MessageType... msgs) {
		for (MessageType typ : msgs) {
			messageTypes.add(typ);
		}
	}
	
	public Set<MessageType> getListeningFor() {
		return messageTypes;
	}
	
	//public abstract void update(int delta);
	
	public abstract void handleMessage(GameWorld gameWorld, MessageType type, Message msg);
	
	public boolean receives(MessageType type) {
		return messageTypes.contains(type);
	}
}
