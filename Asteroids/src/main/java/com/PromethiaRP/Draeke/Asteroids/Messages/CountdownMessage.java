package com.PromethiaRP.Draeke.Asteroids.Messages;

import com.PromethiaRP.Draeke.Asteroids.Component.Component;

public class CountdownMessage extends Message {

	public Component receiver;
	public int length;
	public boolean counting;
	public CountdownMessage(Component receiver, int length, boolean counting) {
		this.receiver = receiver;
		this.length = length;
		this.counting = counting;
	}
}
