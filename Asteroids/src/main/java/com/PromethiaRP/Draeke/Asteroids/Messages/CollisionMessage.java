package com.PromethiaRP.Draeke.Asteroids.Messages;

import com.PromethiaRP.Draeke.Asteroids.Entity;
import com.PromethiaRP.Draeke.Asteroids.Component.Allegiance;
import com.PromethiaRP.Draeke.Asteroids.Component.Physics;

public class CollisionMessage extends Message{

//	public Physics other;
//	public Allegiance faction;
//	public CollisionMessage(Physics other, Allegiance faction) {
//		this.other = other;
//		this.faction = faction;
//	}
	
	public Entity other;
	public CollisionMessage(Entity other) {
		this.other = other;
	}
}
