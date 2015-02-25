package com.PromethiaRP.Draeke.Asteroids;

public class Cooldown {

	private boolean counting;
	private int count;
	
	
	public Cooldown() {}
	
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
}
