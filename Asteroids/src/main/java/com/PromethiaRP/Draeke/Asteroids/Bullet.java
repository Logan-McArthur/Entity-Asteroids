package com.PromethiaRP.Draeke.Asteroids;

public class Bullet extends Entity{

	private Cooldown lifetime;
	private Cooldown invulnTime;
	protected Body body;
	
	public Bullet(int ID, Body bod) {
		super(ID);
		body = bod;
		invulnTime = new Cooldown();
		lifetime = new Cooldown();
//		setStructure(new float[]{0,0, 5,1, 5,4, 0,5}, 2.5f, 2.5f, 1);
//		scaleX = 1.2f;
//		scaleY = 1.2f;
	}
	
	
	
	public void update(int delta) {
		lifetime.update(delta);
		invulnTime.update(delta);
//		move(delta);
	}
	
	public void start(int lifeDuration) {
		lifetime.start(lifeDuration);
	}
	

	
	public void collide(Entity other) {
		if (other instanceof Asteroid) {
			if (!hitPoints.hurt(1)) {
				
			}
		}
	}



	@Override
	public boolean isAlive() {
		return lifetime.counting();
	}
}
