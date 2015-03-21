package com.PromethiaRP.Draeke.Asteroids.Messages;

import org.newdawn.slick.Graphics;

public class RenderMessage extends Message{

	public Graphics grafix;
	public RenderMessage(Graphics grafix) {
		this.grafix = grafix;
	}
}
