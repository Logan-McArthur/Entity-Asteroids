package com.PromethiaRP.Draeke.Asteroids;

public class Bullet extends Entity{

	protected int lifeCounter = 0;
	
	public Bullet(float x, float y) {
		super(x, y);
		
		setStructure(new float[]{0,0, 5,1, 5,4, 0,5}, 2.5f, 2.5f, 1);
		scaleX = 1.2f;
		scaleY = 1.2f;
	}
	
	public void update(int delta) {
		lifeCounter -= delta;
		if (lifeCounter <= 0) {
			alive = false;
			lifeCounter = 0;
		}
		
		move(delta);
	}
	
	public void recycle(float positionX, float positionY, float velocityX, float velocityY, float rotation, int bulletLife) {
		centerX = positionX;
		centerY = positionY;
		this.velocityX = velocityX + (float)Math.cos(rotation)*maxSpeed;// * .8f;
		this.velocityY = velocityY + (float)Math.sin(rotation)*maxSpeed;// * .8f;
		this.rotation = rotation;
		
		lifeCounter = bulletLife;
		alive = true;
	}
	
	public void collide(Entity other) {
		if (other instanceof Asteroid) {
			alive = false;
		}
	}
}
