package com.PromethiaRP.Draeke.Asteroids;

public class BlinkAnimation {
	private int blinkNumber;
	
	private Cooldown blinkRepeater = new Cooldown();
	private Cooldown blinkTimeCounter = new Cooldown();
	
	private boolean visible = true;
	private boolean running = false;
	
	private int invisibleDuration;
	private int visibleDuration;
	
	public BlinkAnimation() {
		this(5, 600, 300);
	}
	
	public BlinkAnimation(int blinkNumber, int visibleDuration, int invisibleDuration) {
		this.invisibleDuration = invisibleDuration;
		this.visibleDuration = visibleDuration;
		this.blinkNumber = blinkNumber;
	}
	
	public void start() {
		blinkRepeater.start(blinkNumber);
		blinkTimeCounter.start(invisibleDuration);
		visible = false;
		running = true;
	}
	
	public void update(int delta) {
		if (running) {
			blinkTimeCounter.update(delta);
			if ( ! blinkTimeCounter.counting()) {
				blinkRepeater.update(1);
				if (visible) {
					blinkTimeCounter.start(invisibleDuration);
				} else {
					blinkTimeCounter.start(visibleDuration);
				}
				visible = ! visible;
			}
			
			if ( ! blinkRepeater.counting()) {
				running = false;
				visible = true;
			}
		}
		
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public boolean running() {
		return running;
	}
}
