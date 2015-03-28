package com.PromethiaRP.Draeke.Asteroids;

public enum WeaponType {
SINGLE(750, 1, new float[]{0}, 20),
DOUBLE(1000, 2, new float[]{-1*(float)Math.PI/24,(float)Math.PI/24}, 18),
TRIPLE(1250, 3, new float[]{-1 * (float) Math.PI/16,0,(float)Math.PI/16}, 16);

final int COOLDOWN;
final int NUMBER_SHOTS;
final float[] SHOT_ANGLES;
final int BULLET_LIFE;

WeaponType(int cooldown, int numShots, float[] shotAngles, int bulletLife) {
	COOLDOWN = cooldown;
	NUMBER_SHOTS = numShots;
	SHOT_ANGLES = shotAngles;
	BULLET_LIFE = bulletLife;
}
	

}
