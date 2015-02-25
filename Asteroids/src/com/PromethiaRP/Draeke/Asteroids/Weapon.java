package com.PromethiaRP.Draeke.Asteroids;

public enum Weapon {
SINGLE(750, 1, new float[]{0}, 2000),
DOUBLE(1000, 2, new float[]{-1*(float)Math.PI/24,(float)Math.PI/24}, 1850),
TRIPLE(1250, 3, new float[]{-1 * (float) Math.PI/16,0,(float)Math.PI/16}, 1600);

final int COOLDOWN;
final int NUMBER_SHOTS;
final float[] SHOT_ANGLES;
final int BULLET_LIFE;

Weapon(int cooldown, int numShots, float[] shotAngles, int bulletLife) {
	COOLDOWN = cooldown;
	NUMBER_SHOTS = numShots;
	SHOT_ANGLES = shotAngles;
	BULLET_LIFE = bulletLife;
}
	

}
