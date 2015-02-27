package com.PromethiaRP.Draeke.Asteroids;

public class Asteroid extends Entity{
	
	private AsteroidSize size;
	
	public Asteroid(AsteroidSize size, float x, float y) {
		super(x, y);
		
		float[] points = new float[]{15,8, 10,0,1,3,1,12,10,15};
		
		this.size = size;
		
		scaleX = size.scaleX;
		scaleY = size.scaleY;
		setStructure(points, 7.5f, 7.5f, 0);

		alive = false;
		
		maxSpeed = 0.6f;
	}
	
	private void spawnOffspring() {
		Asteroid[] asteroids = GameplayScreen.allocateAsteroids(size.numberOfOffspring);
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i].recycle(centerX, centerY, 
					(size == AsteroidSize.LARGE ? AsteroidSize.MEDIUM : AsteroidSize.SMALL),
					rotation + size.baseAngles[i] + MainApplication.rand.nextFloat()-.5f);
			//asteroids[i].size = (size == AsteroidSize.LARGE ? AsteroidSize.MEDIUM : AsteroidSize.SMALL);
			asteroids[i].velocityX = (float)(Math.cos(asteroids[i].rotation)) * asteroids[i].maxSpeed;
			asteroids[i].velocityY = (float)(Math.sin(asteroids[i].rotation)) * asteroids[i].maxSpeed;
			asteroids[i].velocityR = (MainApplication.rand.nextFloat() - 0.5f);
		}
	}
	
	public void update(int delta) {
		move(delta);
	}
	
	public void collide(Entity other) {
		if (other instanceof Bullet) {
			
			if (size == AsteroidSize.SMALL) {
				alive = false;
				return;
			}
			spawnOffspring();
			alive = false;
			return;
		}
		if (other instanceof Player) {
			alive = false;
		}
	}
	
	public void recycle(float xPosition, float yPosition, AsteroidSize size, float direction) {
		centerX = xPosition;
		centerY = yPosition;
		this.size = size;
		rotation = direction;
		
		scaleX = size.scaleX;
		scaleY = size.scaleY;
		alive = true;
	}
}
