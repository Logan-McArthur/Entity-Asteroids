package com.PromethiaRP.Draeke.Asteroids;

import com.PromethiaRP.Draeke.Asteroids.Component.Body;
import com.PromethiaRP.Draeke.Asteroids.Component.Health;

public class Player extends Entity{

//	private int hitPoints = 5;
//	
	
	private boolean firingWeapon = false;
	
	private Cooldown weaponCooler;
	
	private BlinkAnimation animation;
	
	private Weapon currentWeapon = Weapon.SINGLE;
	
	public Player(Body bod, Health health) {

		this.body = bod;
		this.hitPoints = health;
		
		weaponCooler = new Cooldown();
		animation = new BlinkAnimation();
	}
	
//	public Player(int ID, Body bod, Health health) {
//		super(ID);
//		
//		this.body = bod;
//		this.hitPoints = health;
//		// Won't work right now
////		
////		body.setStructure(new float[]{0.0f, 0.0f, 30.0f, 10.0f, 0.0f, 20.0f}, 15, 10, 1);
////		alive = true;
////		maxSpeed = .8f;
////		
//		weaponCooler = new Cooldown();
//		animation = new BlinkAnimation();
//	}
	
	public void setShooting(boolean shooting) {
		firingWeapon = shooting;
	}
	
	private void fireWeapon() {
		float[] coords = body.getTransform().getPoint(1);
		Bullet[] allocated = GameplayScreen.allocateBullets(currentWeapon.NUMBER_SHOTS);
		for (int i = 0; i < allocated.length; i++) {
			Body allocBod = allocated[i].body;
			
			allocBod.setPositionX(coords[0]);
			allocBod.setPositionY(coords[1]);
			allocBod.setVelocityX(body.getVelocityX());
			allocBod.setVelocityY(body.getVelocityY());
			allocBod.setRotation(body.getRotation() + currentWeapon.SHOT_ANGLES[i]);
			allocated[i].start(currentWeapon.BULLET_LIFE);
//			allocated[i].recycle(coords[0], coords[1], body.velocityX, body.velocityY, body.rotation+currentWeapon.SHOT_ANGLES[i], currentWeapon.BULLET_LIFE);
		}
		
		weaponCooler.start(currentWeapon.COOLDOWN);
	}
	
	@Override
	public void update(int delta) {
		weaponCooler.update(delta);
		animation.update(delta);
		
		if (firingWeapon) {
			if (! weaponCooler.counting() && ! animation.running()) {
				fireWeapon();
			}
		}
		
		// Have the components update separately from the entities?
//		accelerate();
//		move(delta);
	}
	
	private void damage() {
		hitPoints.hurt(1);
	}
	
	@Override
	public void collide(Entity other) {
		if (other instanceof Asteroid) {
			if ( ! animation.running()) {
				damage();
			}
		}
	}
	
	@Override
	public boolean isVisible() {
		return animation.isVisible();
	}

	
}
