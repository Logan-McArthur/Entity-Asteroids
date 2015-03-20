package com.PromethiaRP.Draeke.Asteroids;

import com.PromethiaRP.Draeke.Asteroids.Component.Body;
import com.PromethiaRP.Draeke.Asteroids.Component.Health;

public class Asteroid extends Entity{
	
	private AsteroidSize size;

	public Asteroid(AsteroidSize size) {
		
		
		// TODO: Move initialization to the place where the component is created
		float[] points = new float[]{15,8, 10,0,1,3,1,12,10,15};
		
		this.size = size;
		
		body.scaleX = size.scaleX;
		body.scaleY = size.scaleY;
		body.setStructure(points, 7.5f, 7.5f, 0);

		alive = false;
		
		maxSpeed = 0.6f;
	}

	private void spawnOffspring() {
		Asteroid[] asteroids = GameplayScreen.allocateAsteroids(size.numberOfOffspring);
		for (int i = 0; i < asteroids.length; i++) {
			Body bod = asteroids[i].body;
			bod.centerX = body.centerX;
			bod.centerY = body.centerY;
			asteroids[i].size = size.getOffspringSize();
			bod.rotation = body.rotation + size.baseAngles[i] + MainApplication.rand.nextFloat()-.5f;
			
			bod.velocityX = (float)(Math.cos(bod.rotation)) * asteroids[i].maxSpeed;
			bod.velocityY = (float)(Math.sin(bod.rotation)) * asteroids[i].maxSpeed;
			bod.velocityR = (MainApplication.rand.nextFloat() - 0.5f);
			asteroids[i].hitPoints.setHealth(asteroids[i].size.maxHealth);
		}
	}
	
	public void update(int delta) {
		//move(delta);
	}
	
	public void collide(Entity other) {
		if (other instanceof Bullet || other instanceof Player) {
			if (hitPoints.hurt(1)){
				spawnOffspring();
			}
		}
	}

	@Override
	public boolean isAlive() {
		return alive;
	}
	
//	public void recycle(float xPosition, float yPosition, AsteroidSize size, float direction) {
//		centerX = xPosition;
//		centerY = yPosition;
//		this.size = size;
//		rotation = direction;
//		
//		scaleX = size.scaleX;
//		scaleY = size.scaleY;
//		alive = true;
//	}
}
