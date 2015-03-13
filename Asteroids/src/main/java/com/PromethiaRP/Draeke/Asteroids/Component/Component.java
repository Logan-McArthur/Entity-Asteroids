package com.PromethiaRP.Draeke.Asteroids.Component;

public abstract class Component {
//	private int componentID;
	
//	public Component(int ID) {
//		componentID = ID;
//	}
	
	public Component() {
		
	}
//	public int getEntityID() {
//		return componentID;
//	}
	
	public abstract void update(int delta);
}
