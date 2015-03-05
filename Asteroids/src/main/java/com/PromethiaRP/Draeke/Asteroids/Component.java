package com.PromethiaRP.Draeke.Asteroids;

public abstract class Component {
	private int componentID;
	
	public Component(int ID) {
		componentID = ID;
	}
	
	public int getEntityID() {
		return componentID;
	}
	
	public abstract void update(int delta);
}
