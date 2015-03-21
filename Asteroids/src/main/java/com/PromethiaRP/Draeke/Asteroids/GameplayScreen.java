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

import com.PromethiaRP.Draeke.Asteroids.Component.Health;
import com.PromethiaRP.Draeke.Asteroids.Component.KeyboardInput;
import com.PromethiaRP.Draeke.Asteroids.Component.Physics;
import com.PromethiaRP.Draeke.Asteroids.Component.Render;
import com.PromethiaRP.Draeke.Asteroids.Component.Structure;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;
import com.PromethiaRP.Draeke.Asteroids.Messages.RenderMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.UpdateMessage;

public class GameplayScreen extends BasicGameState{

	// Entities contain their components, which declare that they receive certain messages
	// Those messages are used in the update routines
	

	private final int MAX_NUMBER_ENTITIES = 100;
	
	private static List<Entity> entities;
	//private int entityOpenIndex;
	
	// If components update via messages, just dispatch the input before update
//	private Component[] inputComponents;
//	private Component[] updateComponents;

//	private static Bullet[] bulletPool;
//	private static int nextBulletIndex = 0;

//	private static List<Asteroid> asteroidPool = new ArrayList<Asteroid>();

//	InputManager inputManager;
	
//	Player play;// = new Player(400,300);
//	int playerIndex;
	private boolean isOnScreen(Entity ent) {
		return true;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,Graphics grafix) throws SlickException {
		grafix.setLineWidth(2f);
		grafix.setColor(Color.white);
		
		for (Entity ent : entities) {
			if (isOnScreen(ent)) {
				ent.dispatchMessage(MessageType.RENDER, new RenderMessage(grafix));
			}
		}
		
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
//		bulletPool = new Bullet[MAX_NUMBER_BULLETS];
//		initializeBullets();
//		entities = new Entity[MAX_NUMBER_ENTITIES];
		entities = new ArrayList<Entity>(MAX_NUMBER_ENTITIES);
		
		initializeAsteroids();
		Player play = new Player();
		Structure struct = new Structure(new float[]{0.0f,0.0f, 30.0f,10.0f, 0.0f, 20.0f}, 5, 10);
		Physics phys = new Physics(struct, 1.0f, 1.0f);
		Render rend = new Render(struct);
		Health heal = new Health(5);
		KeyboardInput key = new KeyboardInput(createInput(container));
		play.addComponent(struct);
		play.addComponent(phys);
		play.addComponent(rend);
		play.addComponent(heal);
		play.addComponent(key);
		struct.setPosition(350, 350, 0);
		entities.add(play);
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
//			if (ent.body.getTrueCenterX() > container.getWidth()) {
//				ent.body.setPositionX(0);
//			}
//			if (ent.body.getTrueCenterX() < 0) {
//				ent.body.setPositionX(container.getWidth());
//			}
//			if (ent.body.getTrueCenterY() > container.getHeight()) {
//				ent.body.setPositionY( 0);
//			}
//			if (ent.body.getTrueCenterY() < 0) {
//				ent.body.setPositionY(container.getHeight());
//			}
			Message msg = new UpdateMessage(delta);
			ent.dispatchMessage(MessageType.UPDATE_INPUT, msg);
			ent.dispatchMessage(MessageType.UPDATE_DELTA, msg);
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
	
//	private void initializeBullets() {
//		for (int i = 0; i < bulletPool.length; i++) {
//			bulletPool[i] = new Bullet(0.0f, 0.0f);
//		}
//	}

	private Entity constructBullet(float xPos, float yPos) {
		Bullet blt = new Bullet();
		float centerOffsetX = 2.5f;
		float centerOffsetY = 2.5f;
		float bulletMass = 1.0f;
		float bulletMoment = 1.0f;
		Structure struct = new Structure(new float[]{0,0, 5,1, 5,4, 0,5}, centerOffsetX, centerOffsetY);
		Physics phys = new Physics(struct, bulletMass, bulletMoment);
		Render rend = new Render(struct);
		struct.scale(1.2f);
		
		blt.addComponent(phys);
		blt.addComponent(struct);
		blt.addComponent(rend);
		return blt;
	}
	
	private Entity constructAsteroid(AsteroidSize size, float posX, float posY) {
		return constructAsteroid(size, posX, posY, 0,0,0,0);
	}
	
	private Entity constructAsteroid(AsteroidSize size, float posX, float posY, float rotation, float velX, float velY, float velR) {
		Structure struct = new Structure(new float[]{15,8, 10,0, 1,3, 1,12, 10,15}, 7.5f, 7.5f, size.scaleX, size.scaleY);
		Physics phys = new Physics(struct, 1f, 1f);
		Render rend = new Render(struct);
		
		phys.setVelocity(velX, velY, velR);
		struct.setPosition(posX, posY, rotation);
		
		Asteroid aster = new Asteroid(size);
		aster.addComponent(struct);
		aster.addComponent(phys);
		aster.addComponent(rend);
		return aster;
	}
	
//	public static Bullet[] allocateBullets(int number) {
//		int collected = 0;
//		Bullet[] bullets = new Bullet[number];
//		for (int i = nextBulletIndex; collected < number; i++) {
//			if ( i == bulletPool.length){
//				i = 0;
//			}
//
//			if ( ! bulletPool[i].alive) {
//				bullets[collected] = bulletPool[i];
//				collected++;
//			}
//		}
//
//		return bullets;
//	}
	
	private void initializeAsteroids() {
		entities.add(constructAsteroid(AsteroidSize.SMALL, 100, 100));
		entities.add(constructAsteroid(AsteroidSize.MEDIUM, 200, 200));
		entities.add(constructAsteroid(AsteroidSize.LARGE, 300, 300));
		entities.add(constructAsteroid(AsteroidSize.MEDIUM, 200, 300));
		entities.add(constructAsteroid(AsteroidSize.LARGE, 300, 200));
		
//		for (Asteroid aster : asteroidPool) {
//			aster.alive = true;
//			aster.velocityX = (MainApplication.rand.nextFloat() - .5f) * aster.maxSpeed;
//			aster.velocityY = (MainApplication.rand.nextFloat() - .5f) * aster.maxSpeed;
//			aster.velocityR = (MainApplication.rand.nextFloat() - .5f) * 0.8f;
//		}
		
		// TODO: Move initialization to the place where the component is created
//		float[] points = new float[]{15,8, 10,0,1,3,1,12,10,15};
//		
//		this.size = size;
//		
//		body.scaleX = size.scaleX;
//		body.scaleY = size.scaleY;
//		body.setStructure(points, 7.5f, 7.5f, 0);
//
//		alive = false;
	}
	
//	public static Asteroid[] allocateAsteroids(int number) {
//		int collected = 0;
//		Asteroid[] asteroids = new Asteroid[number];
//		for (int i = 0; collected < number; i++) {
//			if (i == asteroidPool.size()) {
//				asteroidPool.add(new Asteroid(AsteroidSize.LARGE, 0.0f, 0.0f));
//			}
//			if (asteroidPool.get(i).isAlive()){
//				continue;
//			}else {
//				asteroids[collected] = asteroidPool.get(i);
//				collected++;
//			}
//		}
//
//		return asteroids;
//	}

	@Override
	public int getID() {
		return MainApplication.GAMEPLAY_SCREEN;
	}

}
