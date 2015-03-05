package com.PromethiaRP.Draeke.Asteroids;

public class Health extends Component{

	private int lifePoints;
	public Health(int ID, int life) {
		super(ID);
		lifePoints = life;
	}
	@Override
	public void update(int delta) {
		
	}

	public boolean isAlive() {
		return lifePoints == 0;
	}
	
	/**
	 * 
	 * @param damage The amount of damage to inflict
	 * @return True if the entity has died as a result of the injury
	 */
	public boolean hurt(int damage) {
		lifePoints = Math.max(0, lifePoints - damage);
		return isAlive();
	}
	
	public void setHealth(int health) {
		lifePoints = health;
	}
}
