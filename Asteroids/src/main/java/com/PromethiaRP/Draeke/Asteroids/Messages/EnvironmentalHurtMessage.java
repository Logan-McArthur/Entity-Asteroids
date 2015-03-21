package com.PromethiaRP.Draeke.Asteroids.Messages;

public class EnvironmentalHurtMessage extends Message {

	int damage;
	public EnvironmentalHurtMessage(int damage) {
		this.damage = damage;
	}
}
