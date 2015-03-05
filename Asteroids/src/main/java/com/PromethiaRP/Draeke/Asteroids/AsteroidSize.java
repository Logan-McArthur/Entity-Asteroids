package com.PromethiaRP.Draeke.Asteroids;

public enum AsteroidSize {
	LARGE(3, 3, 1.8f, 1.8f, new float[]{(float) (2 * Math.PI / 3) , (float)(4 * Math.PI /3), (float)(6 * Math.PI / 3)}),
	MEDIUM(3, 2, 1.4f, 1.4f, new float[]{(float) (2 * Math.PI / 3) , (float)(4 * Math.PI /3), (float)(6 * Math.PI / 3)}),
	SMALL(0, 1, 1.0f, 1.0f, new float[]{0.0f, 0.0f, 0.0f});

	final int numberOfOffspring;
	final float[] baseAngles;
	final float scaleX;
	final float scaleY;
	final int maxHealth;
	AsteroidSize(int offspring, int health, float xScale, float yScale, float[] angles) {
		numberOfOffspring = offspring;
		baseAngles = angles;
		scaleX = xScale;
		scaleY = yScale;
		maxHealth = health;
	}

	public AsteroidSize getOffspringSize() {
		if (this == AsteroidSize.LARGE) {
			return AsteroidSize.MEDIUM;
		} else if (this == AsteroidSize.MEDIUM) {
			return AsteroidSize.SMALL;
		}
		return AsteroidSize.LARGE;
	}
}
