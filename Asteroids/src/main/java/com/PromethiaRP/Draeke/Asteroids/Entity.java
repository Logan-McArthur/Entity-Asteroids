package com.PromethiaRP.Draeke.Asteroids;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.PromethiaRP.Draeke.Asteroids.Component.*;
import com.PromethiaRP.Draeke.Asteroids.Messages.*;

// TODO: Begin storing components directly in objects, or at least references
// It does not really make sense to have them be so anonymous, it causes there to be coupling at creation

public class Entity {
	
	protected GameWorld gameWorld;
	
	protected boolean alive = true;
	
	protected Set<MessageType> messageTypes = new HashSet<MessageType>();
	
	protected List<Component> components = new ArrayList<Component>();
	
	private int structureIndex;
	private int allegianceIndex;
	private int healthIndex;
	private int inputIndex;
	private int physicsIndex;
	private int renderIndex;
	
	public final String name;
	
	public Entity(GameWorld gameWorld, String name) {
		this.name = name;
		this.gameWorld = gameWorld;
	}
//	public Entity(GameWorld gameWorld) {
//		this.gameWorld = gameWorld;
//	}
	
	public void addComponent(Component cp) {
		components.add(cp);
		cp.setEntityContainer(this);
	}
	
	public void addComponents(Component... cps) {
		for (Component cp : cps) {
			addComponent(cp);
		}
	}
	
	public void addListening(Set<MessageType> messages) {
		messageTypes.addAll(messages);
	}
	
	public void update(int delta){
		
	}
	
	
	
//	public boolean isAlive() {
//		// Alive should be set by the health component through the message system?
//		return alive;
//	}
//	
	// TODO: Consider having dispatchMessage return values back to sender
	// But if I have the scripts directly accessing other components, consider not having the return values
	public void dispatchMessage(MessageType msgType, Message msg) {
		// Dispatches a message to all components that will receive it.
		for (Component cp : components) {
			if (cp.receives(msgType)) {
				cp.handleMessage(gameWorld, msgType, msg);
			}
		}
	}
	
}
