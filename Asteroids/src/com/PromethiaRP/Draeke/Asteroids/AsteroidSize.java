package com.PromethiaRP.Draeke.Asteroids;

public enum AsteroidSize {
LARGE(3, 1.8f, 1.8f, new float[]{(float) (2 * Math.PI / 3) , (float)(4 * Math.PI /3), (float)(6 * Math.PI / 3)}),// (float) (2 * Math.PI)}),
MEDIUM(3, 1.4f, 1.4f, new float[]{(float) (2 * Math.PI / 3) , (float)(4 * Math.PI /3), (float)(6 * Math.PI / 3)}),
SMALL(0, 1.0f, 1.0f, new float[]{0.0f, 0.0f, 0.0f});

final int numberOfOffspring;
final float[] baseAngles;
final float scaleX;
final float scaleY;
AsteroidSize(int offspring, float xScale, float yScale, float[] angles) {
	numberOfOffspring = offspring;
	baseAngles = angles;
	scaleX = xScale;
	scaleY = yScale;
}
}
