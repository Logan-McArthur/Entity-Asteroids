package com.PromethiaRP.Draeke.Asteroids;

public class Asteroid extends Entity{
	
	private AsteroidSize size;

	public Asteroid(AsteroidSize size) {
		
		
//		
		
	}

//	private void spawnOffspring() {
//		Asteroid[] asteroids = GameplayScreen.allocateAsteroids(size.numberOfOffspring);
//		for (int i = 0; i < asteroids.length; i++) {
//			Body bod = asteroids[i].body;
//			bod.centerX = body.centerX;
//			bod.centerY = body.centerY;
//			asteroids[i].size = size.getOffspringSize();
//			bod.rotation = body.rotation + size.baseAngles[i] + MainApplication.rand.nextFloat()-.5f;
//			
//			bod.velocityX = (float)(Math.cos(bod.rotation)) * asteroids[i].maxSpeed;
//			bod.velocityY = (float)(Math.sin(bod.rotation)) * asteroids[i].maxSpeed;
//			bod.velocityR = (MainApplication.rand.nextFloat() - 0.5f);
//			asteroids[i].hitPoints.setHealth(asteroids[i].size.maxHealth);
//		}
//	}
	
	public void update(int delta) {
		//move(delta);
	}
	
	public void collide(Entity other) {
		if (other instanceof Bullet || other instanceof Player) {
//			if (hitPoints.hurt(1)){
//				spawnOffspring();
//			}
		}
	}

	@Override
	public boolean isAlive() {
		return alive;
	}
	
}
