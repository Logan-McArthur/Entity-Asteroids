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

import com.PromethiaRP.Draeke.Asteroids.Component.Component;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;
import com.PromethiaRP.Draeke.Asteroids.Messages.UpdateMessage;

public class GameplayScreen extends BasicGameState{

	// Entities contain their components, which declare that they receive certain messages
	// Those messages are used in the update routines
	
	private final int MAX_NUMBER_BULLETS = 30;
	private final int MAX_NUMBER_ENTITIES = 100;
	
	private static Entity[] entities;
	private int entityOpenIndex;
	
	// If components update via messages, just dispatch the input before update
	private Component[] inputComponents;
	private Component[] updateComponents;

//	private static Bullet[] bulletPool;
//	private static int nextBulletIndex = 0;

//	private static List<Asteroid> asteroidPool = new ArrayList<Asteroid>();

//	InputManager inputManager;
	
//	Player play;// = new Player(400,300);
//	int playerIndex;
	@Override
	public void render(GameContainer container, StateBasedGame game,Graphics grafix) throws SlickException {
		grafix.setLineWidth(2f);
		grafix.setColor(Color.white);
		
		for (Entity ent : entities) {
			if (ent.isVisible()) {
				grafix.setColor(Color.black);
				grafix.fill(ent.body.getTransform());
				grafix.setColor(Color.white);
				grafix.draw(ent.body.getTransform());
			}
		}
		
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
//		bulletPool = new Bullet[MAX_NUMBER_BULLETS];
//		initializeBullets();
		entities = new Entity[MAX_NUMBER_ENTITIES];
		
		initializeAsteroids();
	}


	public InputManager createInput(GameContainer container) {
		
		InputManager inputManager = new InputManager(container);
		
		inputManager.createKeyControl(Input.KEY_UP, "Forward");
		inputManager.createKeyControl(Input.KEY_DOWN, "Backward");
		inputManager.createKeyControl(Input.KEY_LEFT, "TurnLeft");
		inputManager.createKeyControl(Input.KEY_RIGHT, "TurnRight");
		inputManager.createKeyControl(Input.KEY_SPACE, "Shoot");
		
		return inputManager;
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		boolean asteroidsAlive = false;
		
		
		for (Entity ent : entities) {
			// TODO: Check the center methods from body
			if (ent.body.getTrueCenterX() > container.getWidth()) {
				ent.body.setPositionX(0);
			}
			if (ent.body.getTrueCenterX() < 0) {
				ent.body.setPositionX(container.getWidth());
			}
			if (ent.body.getTrueCenterY() > container.getHeight()) {
				ent.body.setPositionY( 0);
			}
			if (ent.body.getTrueCenterY() < 0) {
				ent.body.setPositionY(container.getHeight());
			}
			Message msg = new UpdateMessage("", delta);
			
			ent.update(delta);
		}
		
		
		{	// Game status update block
//			if (! play.isAlive()) {
//				game.enterState(MainApplication.GAMEOVER_SCREEN, new FadeOutTransition(), new FadeInTransition());
//			}
			if (! asteroidsAlive) {
				
			}
		}
		
		
	}

	public void broadcastMessage(MessageType type, Message msg) {
		for (Entity ent : entities) {
			ent.dispatchMessage(type, msg);
		}
	}
	
	private void initializeBullets() {
		for (int i = 0; i < bulletPool.length; i++) {
			bulletPool[i] = new Bullet(0.0f, 0.0f);
		}
	}

	private Bullet constructBullet(float xPos, float yPos) {
		Bullet blt = new Bullet();
		
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
			if (asteroidPool.get(i).isAlive()){
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
