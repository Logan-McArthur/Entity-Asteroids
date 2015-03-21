package com.PromethiaRP.Draeke.Asteroids.Component;

import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

public class Cooldown extends Component {

	private boolean counting;
	private int count;
	
	public Cooldown() {
		messageTypes.add(MessageType.UPDATE_DELTA);
	}
	
	public void start(int cooldownLength) {
		counting = true;
		count = cooldownLength;
	}
	
	public void pause() {
		counting = false;
	}
	
	public void resume() {
		counting = true;
	}
	
	/**
	 * 
	 * @param delta
	 */
	public void update(int delta) {
		if (counting){
			count -= delta;
			if (count <= 0) {
				counting = false;
				
			}
		}
	}
	
	public boolean counting() {
		return counting;
	}

	@Override
	public void handleMessage(MessageType type, Message msg) {
		
	}
}
