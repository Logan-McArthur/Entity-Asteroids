package com.PromethiaRP.Draeke.Asteroids;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameplayScreen extends BasicGameState{

	private final int MAX_NUMBER_BULLETS = 30;

	private static Bullet[] bulletPool;
	private static int nextBulletIndex = 0;

	private static List<Asteroid> asteroidPool = new ArrayList<Asteroid>();

	InputManager inputManager;
	
	Player play = new Player(400,300);

	@Override
	public void render(GameContainer container, StateBasedGame game,Graphics grafix) throws SlickException {
		grafix.setLineWidth(2f);
		grafix.setColor(Color.white);
		if (play.alive && play.isVisible()){
			grafix.setColor(Color.black);
			grafix.fill(play.getTransform());
			grafix.setColor(Color.white);
			grafix.draw(play.getTransform());
		}
		
		for (int i = 0; i < bulletPool.length; i++) {
			if (bulletPool[i].alive) {
				grafix.draw(bulletPool[i].getTransform());
			}
		}
		for (Asteroid aster : asteroidPool) {
			if (aster.alive){
				grafix.setColor(Color.black);
				grafix.fill(aster.getTransform());
				grafix.setColor(Color.white);
				grafix.draw(aster.getTransform());
			}
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		inputManager = new InputManager(container);
		
		inputManager.createKeyControl(Input.KEY_UP, "Forward");
		inputManager.createKeyControl(Input.KEY_DOWN, "Backward");
		inputManager.createKeyControl(Input.KEY_LEFT, "TurnLeft");
		inputManager.createKeyControl(Input.KEY_RIGHT, "TurnRight");
		inputManager.createKeyControl(Input.KEY_SPACE, "Shoot");
		bulletPool = new Bullet[MAX_NUMBER_BULLETS];
		initializeBullets();
		initializeAsteroids();
	}


	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		boolean asteroidsAlive = false;
		
		{	// Player update block

			if (inputManager.isControlPressed("Forward") && inputManager.isControlPressed("Backward")) {
				play.setAccelerating(0);
			} else if (inputManager.isControlPressed("Forward")) {
				play.setAccelerating(1);
			} else if (inputManager.isControlPressed("Backward")) {
				play.setAccelerating(-1);
			} else {
				play.setAccelerating(0);
			}
			
			if (inputManager.isControlPressed("TurnLeft") && inputManager.isControlPressed("TurnRight")) {
				play.setRotating(0);
			} else if (inputManager.isControlPressed("TurnLeft")) {
				play.setRotating(-1);
			} else if (inputManager.isControlPressed("TurnRight")) {
				play.setRotating(1);
			} else {
				play.setRotating(0);
			}
			
			if (inputManager.isControlPressed("Shoot")) {
				play.setShooting(true);
			} else {
				play.setShooting(false);
			}


			if (play.centerX > container.getWidth()) {
				play.centerX = 0;
			}
			if (play.centerX < 0) {
				play.centerX = container.getWidth();
			}
			if (play.centerY > container.getHeight()) {
				play.centerY = 0;
			}
			if (play.centerY < 0) {
				play.centerY = container.getHeight();
			}
			play.update(delta);
		}

		{	// Bullet update block
			for (int i = 0; i < bulletPool.length; i++) {
				if(bulletPool[i].alive){ 
					bulletPool[i].update(delta);
					if (bulletPool[i].centerX < 0) {
						bulletPool[i].centerX = container.getWidth();
					}
					if (bulletPool[i].centerX > container.getWidth()) {
						bulletPool[i].centerX = 0;
					}
					if (bulletPool[i].centerY < 0) {
						bulletPool[i].centerY = container.getHeight();
					}
					if (bulletPool[i].centerY > container.getHeight()) {
						bulletPool[i].centerY = 0;
					}
				}
			}
		}

		{	// Asteroid update block
			for (int j = 0; j < asteroidPool.size(); j++) {
				Asteroid aster = asteroidPool.get(j);
				if (aster.alive) {
					asteroidsAlive = true;
				} else {
					continue;
				}
				if (aster.doesCollide(play)) {
					aster.collide(play);
					play.collide(aster);
				}
				for (int i = 0; i < bulletPool.length; i++) {
					if (bulletPool[i].alive) {
						if (aster.doesCollide(bulletPool[i])) {
							aster.collide(bulletPool[i]);
							bulletPool[i].collide(aster);
						}
						
					}
				}
				
				aster.update(delta);
				
				if (aster.centerX < 0){
					aster.centerX = container.getWidth();
				}
				if (aster.centerX > container.getWidth()) {
					aster.centerX = 0;
				}
				if (aster.centerY > container.getHeight()) {
					aster.centerY = 0;
				}
				if (aster.centerY < 0) {
					aster.centerY = container.getHeight();
				}
			}
		}
		
		{	// Game status update block
			if (! play.alive) {
				game.enterState(MainApplication.GAMEOVER_SCREEN, new FadeOutTransition(), new FadeInTransition());
			}
			if (! asteroidsAlive) {
				
			}
		}
		
		
	}

	
	private void initializeBullets() {
		for (int i = 0; i < bulletPool.length; i++) {
			bulletPool[i] = new Bullet(0.0f, 0.0f);
		}
	}

	public static Bullet[] allocateBullets(int number) {
		int collected = 0;
		Bullet[] bullets = new Bullet[number];
		for (int i = nextBulletIndex; collected < number; i++) {
			if ( i == bulletPool.length){
				i = 0;
			}

			if ( ! bulletPool[i].alive) {
				bullets[collected] = bulletPool[i];
				collected++;
			}
		}

		return bullets;
	}
	
	private void initializeAsteroids() {
		asteroidPool.add(new Asteroid(AsteroidSize.SMALL, 100, 100));
		asteroidPool.add(new Asteroid(AsteroidSize.MEDIUM, 200, 200));
		asteroidPool.add(new Asteroid(AsteroidSize.LARGE, 300, 300));
		asteroidPool.add(new Asteroid(AsteroidSize.MEDIUM, 200, 300));
		asteroidPool.add(new Asteroid(AsteroidSize.LARGE, 300, 200));
		
		for (Asteroid aster : asteroidPool) {
			aster.alive = true;
			aster.velocityX = (MainApplication.rand.nextFloat() - .5f) * aster.maxSpeed;
			aster.velocityY = (MainApplication.rand.nextFloat() - .5f) * aster.maxSpeed;
			aster.velocityR = (MainApplication.rand.nextFloat() - .5f) * 0.8f;
		}
	}
	
	public static Asteroid[] allocateAsteroids(int number) {
		int collected = 0;
		Asteroid[] asteroids = new Asteroid[number];
		for (int i = 0; collected < number; i++) {
			if (i == asteroidPool.size()) {
				asteroidPool.add(new Asteroid(AsteroidSize.LARGE, 0.0f, 0.0f));
			}
			if (asteroidPool.get(i).alive){
				continue;
			}else {
				asteroids[collected] = asteroidPool.get(i);
				collected++;
			}
		}

		return asteroids;
	}

	@Override
	public int getID() {
		return MainApplication.GAMEPLAY_SCREEN;
	}

}
