package com.PromethiaRP.Draeke.Asteroids;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GameplayScreen extends BasicGame{

	// Entities contain their components, which declare that they receive certain messages
	// Those messages are used in the update routines
	

	public GameplayScreen(String title) {
		super(title);
	}

	private final int MAX_NUMBER_ENTITIES = 100;
	
//	private static List<Entity> entities;
//	
	private GameWorld gameWorld;
	@Override
	public void render(GameContainer container, Graphics grafix) throws SlickException {
		grafix.setLineWidth(2f);
		grafix.setColor(Color.white);
		
		gameWorld.render(grafix);
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		gameWorld = new GameWorld(container, MAX_NUMBER_ENTITIES);
		gameWorld.init();
//		entities = new ArrayList<Entity>(MAX_NUMBER_ENTITIES);
		
//		initializeAsteroids();
//		Player play = new Player(null);
//		Structure struct = new Structure(new float[]{0.0f,0.0f, 30.0f,10.0f, 0.0f, 20.0f}, 10, 10);
//		Physics phys = new Physics(struct, 1.0f, 1.0f);
//		Render rend = new Render(struct);
//		Health heal = new Health(5);
//		KeyboardInput key = new KeyboardInput(createInput(container));
//		play.addComponent(struct);
//		play.addComponent(phys);
//		play.addComponent(rend);
//		play.addComponent(heal);
//		play.addComponent(key);
//		struct.setPosition(new Vector2f(350,350), 1);
//		entities.add(play);
	}


//	public InputManager createInput(GameContainer container) {
//		
//		InputManager inputManager = new InputManager(container);
//		
//		inputManager.createKeyControl(Input.KEY_UP, "Forward");
//		inputManager.createKeyControl(Input.KEY_DOWN, "Backward");
//		inputManager.createKeyControl(Input.KEY_LEFT, "TurnLeft");
//		inputManager.createKeyControl(Input.KEY_RIGHT, "TurnRight");
//		inputManager.createKeyControl(Input.KEY_Q, "StrafeLeft");
//		inputManager.createKeyControl(Input.KEY_E, "StrafeRight");
//		inputManager.createKeyControl(Input.KEY_SPACE, "Shoot");
//		
//		return inputManager;
//	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {

//		boolean asteroidsAlive = false;
		
		gameWorld.update(delta);
		
//		for (Entity ent : entities) {
//			// TODO: Check the center methods from body
////			if (ent.body.getTrueCenterX() > container.getWidth()) {
////				ent.body.setPositionX(0);
////			}
////			if (ent.body.getTrueCenterX() < 0) {
////				ent.body.setPositionX(container.getWidth());
////			}
////			if (ent.body.getTrueCenterY() > container.getHeight()) {
////				ent.body.setPositionY( 0);
////			}
////			if (ent.body.getTrueCenterY() < 0) {
////				ent.body.setPositionY(container.getHeight());
////			}
//			Message msg = new UpdateMessage(delta);
//			ent.dispatchMessage(MessageType.UPDATE_INPUT, msg);
//			ent.dispatchMessage(MessageType.UPDATE_DELTA, msg);
//			ent.update(delta);
//		}
//		
	}

//	public void broadcastMessage(MessageType type, Message msg) {
//		for (Entity ent : entities) {
//			ent.dispatchMessage(type, msg);
//		}
//	}
	
//	private void initializeBullets() {
//		for (int i = 0; i < bulletPool.length; i++) {
//			bulletPool[i] = new Bullet(0.0f, 0.0f);
//		}
//	}

//	private Entity constructBullet(float xPos, float yPos) {
//		Entity blt = new Entity(gameWorld);
//		float centerOffsetX = 2.5f;
//		float centerOffsetY = 2.5f;
//		float bulletMass = 1.0f;
//		float bulletMoment = 1.0f;
//		Structure struct = new Structure(new float[]{0,0, 5,1, 5,4, 0,5}, centerOffsetX, centerOffsetY, 1.2f, 1.2f);
//		Physics phys = new Physics(struct, bulletMass, bulletMoment);
//		Render rend = new Render(struct);
//		//struct.scale(1.2f);
//		
//		blt.addComponent(phys);
//		blt.addComponent(struct);
//		blt.addComponent(rend);
//		return blt;
//	}
	
//	private Entity constructAsteroid(AsteroidSize size, Vector2f pos) {
//		return constructAsteroid(size, pos, 0, new Vector2f(0,0),0);
//	}
//	
//	private Entity constructAsteroid(AsteroidSize size, Vector2f pos, float rotation, Vector2f vel, float velR) {
//		Structure struct = new Structure(new float[]{15,8, 10,0, 1,3, 1,12, 10,15}, 7.5f, 7.5f, size.scaleX, size.scaleY);
//		Physics phys = new Physics(struct, 1f, 1f);
//		Render rend = new Render(struct);
//		
//		phys.setVelocity(vel, velR);
//		struct.setPosition(pos, rotation);
//		
//		Asteroid aster = new Asteroid(null, size);
//		aster.addComponent(struct);
//		aster.addComponent(phys);
//		aster.addComponent(rend);
//		return aster;
//	}
	
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
	
//	private void initializeAsteroids() {
//		entities.add(constructAsteroid(AsteroidSize.SMALL, new Vector2f(100, 100)));
//		entities.add(constructAsteroid(AsteroidSize.MEDIUM, new Vector2f(15, 85)));
//		entities.add(constructAsteroid(AsteroidSize.LARGE, new Vector2f(300, 300)));
//		entities.add(constructAsteroid(AsteroidSize.MEDIUM, new Vector2f(200, 300)));
//		entities.add(constructAsteroid(AsteroidSize.LARGE, new Vector2f(700, 200)));
//		
////		for (Asteroid aster : asteroidPool) {
////			aster.alive = true;
////			aster.velocityX = (MainApplication.rand.nextFloat() - .5f) * aster.maxSpeed;
////			aster.velocityY = (MainApplication.rand.nextFloat() - .5f) * aster.maxSpeed;
////			aster.velocityR = (MainApplication.rand.nextFloat() - .5f) * 0.8f;
////		}
//		
//		// TODO: Move initialization to the place where the component is created
////		float[] points = new float[]{15,8, 10,0,1,3,1,12,10,15};
////		
////		this.size = size;
////		
////		body.scaleX = size.scaleX;
////		body.scaleY = size.scaleY;
////		body.setStructure(points, 7.5f, 7.5f, 0);
////
////		alive = false;
//	}
	
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


}
