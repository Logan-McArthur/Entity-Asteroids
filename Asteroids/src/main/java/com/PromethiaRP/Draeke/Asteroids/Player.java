package com.PromethiaRP.Draeke.Asteroids;

public class Player extends Entity{

	private int hitPoints = 5;
	
	private boolean firingWeapon = false;
	
	private Cooldown weaponCooler;
	
	private BlinkAnimation animation;
	
	private Weapon currentWeapon = Weapon.SINGLE;
	
	public Player(float x, float y) {
		super(x,y);
		setStructure(new float[]{0.0f, 0.0f, 30.0f, 10.0f, 0.0f, 20.0f}, 15, 10, 1);
		alive = true;
		maxSpeed = .8f;
		
		weaponCooler = new Cooldown();
		animation = new BlinkAnimation();
	}
	
	public void setShooting(boolean shooting) {
		firingWeapon = shooting;
	}
	
	private void fireWeapon() {
		float[] coords = getTransform().getPoint(1);
		Bullet[] allocated = GameplayScreen.allocateBullets(currentWeapon.NUMBER_SHOTS);
		for (int i = 0; i < allocated.length; i++) {
			allocated[i].recycle(coords[0], coords[1], velocityX, velocityY, rotation+currentWeapon.SHOT_ANGLES[i], currentWeapon.BULLET_LIFE);
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
		
		accelerate();
		move(delta);
	}
	
	private void damage() {
		hitPoints--;
		if (hitPoints == 0) {
			alive = false;
		} else {
			animation.start();
		}
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
