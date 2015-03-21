package com.PromethiaRP.Draeke.Asteroids.Messages;

public class MoveMessage extends Message{

	public float deltaX, deltaY, deltaR;
	public MoveMessage(float deltaX, float deltaY, float deltaR) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.deltaR = deltaR;
	}
}
