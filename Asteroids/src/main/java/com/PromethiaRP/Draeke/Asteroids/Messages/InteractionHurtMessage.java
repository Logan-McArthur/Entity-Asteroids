package com.PromethiaRP.Draeke.Asteroids.Messages;

import com.PromethiaRP.Draeke.Asteroids.Entity;

public class InteractionHurtMessage extends Message{

	public int damage;
	public Entity attacker;
	public InteractionHurtMessage(Entity attacker, int damage) {
		this.damage = damage;
		this.attacker = attacker;
	}
}
