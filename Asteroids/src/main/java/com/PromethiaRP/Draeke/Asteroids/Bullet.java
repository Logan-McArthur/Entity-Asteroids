package com.PromethiaRP.Draeke.Asteroids;

import com.PromethiaRP.Draeke.Asteroids.Component.Cooldown;

public class Bullet extends Entity{

	private Cooldown lifetime;
	private Cooldown invulnTime;

	public Bullet() {
	
		invulnTime = new Cooldown();
		lifetime = new Cooldown();

	}
	
	
	
	public void update(int delta) {
		lifetime.update(delta);
		invulnTime.update(delta);
	}
	
	public void start(int lifeDuration) {
		lifetime.start(lifeDuration);
	}
	

	
	public void collide(Entity other) {
		if (other instanceof Asteroid) {
//			if (!hitPoints.hurt(1)) {
//				
//			}
		}
	}

	@Override
	public boolean isAlive() {
		return lifetime.counting();
	}
}
