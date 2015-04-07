package com.PromethiaRP.Draeke.Asteroids;

import java.util.HashMap;
import java.util.Map;

import com.PromethiaRP.Draeke.Asteroids.Component.*;
import com.PromethiaRP.Draeke.Asteroids.Messages.CollisionMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.EntityDieMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;
import com.PromethiaRP.Draeke.Asteroids.Messages.RenderMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.UpdateMessage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class GameWorld {

	private GameContainer container;
	
	//private List<Entity> entities;
	private Entity[] entities;
	private int openIndex = 0;
	private Map<Shape, Entity> collisionBoxes;
	Script scrp = new Script("src/main/lua/PlayerShip.lua");
	
	public GameWorld(GameContainer container, int maxEntities) {
		this.container = container;
		//entities = new ArrayList<Entity>(maxEntities);
		entities = new Entity[maxEntities];
		collisionBoxes = new HashMap<Shape, Entity>();
	}
	
	private Script getScript(String scriptName) {
		return scrp;
	}
	
	public void runScript(String scriptName, String functionName) {
		getScript(scriptName).invokeFunction(functionName, this);
	}
	
	public Entity createBlankEntity(String entityName) {
		Entity ent = new Entity(this, entityName);
		this.addEntity(ent);
		return ent;
	}
	private void addEntity(Entity ent) {
		if (openIndex == entities.length) {
			throw new IllegalArgumentException();
		}
		entities[openIndex] = ent;
		openIndex++;
	}
	
	public void init() {
		initializeAsteroids();
		
//		Entity ship = new Entity(this, "Ship");
		this.runScript("PlayerShip.lua", "constructEntity");
		//scrp.invokeFunction("constructEntity", this, ship );
		//this.addEntity(ship);
//		this.addEntity(constructShip(new Vector2f(350,350), 0, new Vector2f(0,0), 0));
	}
	
	public void render(Graphics grafix) {
		for (Entity ent : entities) {
			if (ent == null) {
				continue;
			}
		//for (int i = 0; i < openIndex && i < entities.length; i++) {
	//		if (ent.isAlive() && isOnScreen(ent)) {
				ent.dispatchMessage(MessageType.RENDER, new RenderMessage(grafix));
	//		}
		}
	}
	
	// At the beginning, clear the collision profiles, but only the beginning because those profiles might be useful
	public void update(int delta) {
		collisionBoxes.clear();
		for (Entity ent : entities) {
			if (ent == null) {
				continue;
			}
//		for (int i = 0; i < openIndex && i < entities.length; i++) {
			Message msg = new UpdateMessage(delta);
			ent.dispatchMessage(MessageType.UPDATE_INPUT, msg);
			ent.dispatchMessage(MessageType.UPDATE_DELTA, msg);
//			entities[i].update(delta);
		}
	}
	
	private void initializeAsteroids() {
		constructAsteroid(AsteroidSize.SMALL, new Vector2f(100, 100), 1, new Vector2f(.3f, .1f), .01f);
//		entities.add(constructAsteroid(AsteroidSize.MEDIUM, new Vector2f(15, 85)));
//		entities.add(constructAsteroid(AsteroidSize.LARGE, new Vector2f(300, 300)));
//		entities.add(constructAsteroid(AsteroidSize.MEDIUM, new Vector2f(200, 300)));
//		entities.add(constructAsteroid(AsteroidSize.LARGE, new Vector2f(700, 200)));
		
	}
	
	
	// TODO: Delete this temporary method
	@Deprecated
	public float[] getShipModel() {
		return new float[]{0.0f,0.0f, 30.0f,10.0f, 0.0f, 20.0f};
	}
	
	public Structure constructStructure(float[] model, float centerX, float centerY, float rotation, 
			float centerOffX, float centerOffY, float scaleX, float scaleY) {
		Structure struct = new Structure(model, centerOffX, centerOffY, scaleX, scaleY);
		struct.setPosition(new Vector2f(centerX, centerY), rotation);
		return struct;
	}
	
	public Physics constructPhysics(Structure struct, float mass, float moment, float velX, float velY, float velR, float drag) {
		Physics phys = new Physics(struct, mass, moment);
		phys.setVelocity(new Vector2f(velX, velY), velR);
		phys.setDrag(drag);
		return phys;
	}
	
	public Health constructHealth(int life) {
		return new Health(life);
	}
	
	
	public KeyboardInput constructKeyboardInput() {
		return new KeyboardInput(createInput(container));
	}
	public Render constructRender(Structure struct) {
		return new Render(struct);
	}
	public Weapon constructWeapon(Physics phys,float mountX, float mountY, float velForward, float velSideways, int cooldown, int bulletLife) {
		//WeaponType typ = WeaponType.SINGLE;
		
		//Weapon weap = new Weapon(struct, new Vector2f(30,10), typ.COOLDOWN, typ.NUMBER_SHOTS, typ.SHOT_ANGLES, typ.BULLET_LIFE );
		return new Weapon(phys, new Vector2f(mountX,mountY), cooldown, 1, new Vector2f(velForward, velSideways), bulletLife );
				
	}
	public Allegiance constructAllegiance() {
		return new Allegiance();
	}
	public Countdown constructCountdown() {
		return new Countdown();
	}
	public Entity constructShip(Vector2f position, float rotation, Vector2f velocity, float vectorR) {
//		float[] shipModel = getShipModel();
//		float centerOffsetX = 10f;
//		float centerOffsetY = 10f;
//		float scaleX = 1.0f;
//		float scaleY = 1.0f;
//		
//		float mass = 1f;
//		float moment = 1f;
//		
//		int health = 5;
//		
//		Structure struct = new Structure(shipModel, centerOffsetX, centerOffsetY, scaleX, scaleY);
//		struct.setPosition(position, rotation);
		
		//Physics phys = constructPhysics(struct, mass, moment, velocity.x, velocity.y, vectorR, .95f);
//				new Physics(struct, mass, moment);
//		phys.setVelocity(velocity, vectorR);
//		phys.setDrag(.95f);
		
		//Render rend = new Render(struct);
		
		//Health heal = constructHealth(health);
		
		//KeyboardInput key = constructKeyboardInput();
		
		//WeaponType typ = WeaponType.SINGLE;
		//Weapon weap = new Weapon(struct, new Vector2f(30,10), typ.COOLDOWN, typ.NUMBER_SHOTS, typ.SHOT_ANGLES, typ.BULLET_LIFE );
		//Weapon weap = new Weapon(phys, new Vector2f(22,0), typ.COOLDOWN, typ.NUMBER_SHOTS, new Vector2f(2, 0), typ.BULLET_LIFE );
		
//		Allegiance all = new Allegiance();
//		Countdown coun = new Countdown();
		
		Entity ship = new Entity(this, "Ship");
		
		scrp.invokeFunction("constructEntity", this, ship );
		return ship;
	}
	
	
	// From the Player.java class
	

	
	public InputManager createInput(GameContainer container) {
		
		InputManager inputManager = new InputManager(container);
		
		inputManager.createKeyControl(Input.KEY_UP, "Forward");
		inputManager.createKeyControl(Input.KEY_DOWN, "Backward");
		inputManager.createKeyControl(Input.KEY_LEFT, "TurnLeft");
		inputManager.createKeyControl(Input.KEY_RIGHT, "TurnRight");
		inputManager.createKeyControl(Input.KEY_Q, "StrafeLeft");
		inputManager.createKeyControl(Input.KEY_E, "StrafeRight");
		inputManager.createKeyControl(Input.KEY_SPACE, "Shoot");
		
		return inputManager;
	}
	
	public Entity constructBullet(Vector2f position, float rotation, Vector2f velocity, float velocityR, int lifeSpan) {
		float[] bulletModel = new float[]{0,0, 5,1, 5,4, 0,5};
		float centerOffsetX = 2.5f;
		float centerOffsetY = 2.5f;
		float scaleX = 1.2f;
		float scaleY = 1.2f;
		
		float mass = 1f;
		float moment = 1f;
		
		int health = 1;
		
		Structure struct = new Structure(bulletModel, centerOffsetX, centerOffsetY, scaleX, scaleY);
		struct.setPosition(position, rotation);
		Physics phys = new Physics(struct, mass, moment);
		phys.setVelocity(velocity, velocityR);
		Health heal = new Health(health);
		Countdown count = new Countdown();
		Render rend = new Render(struct);
		Allegiance all = new Allegiance();
		Countdown coun = new Countdown();
		
		Entity ent = new Entity(this, "Bullet");
		ent.addComponent(struct);
		ent.addComponent(phys);
		ent.addComponent(heal);
		ent.addComponent(count);
		ent.addComponent(rend);
		ent.addComponent(all);
		ent.addComponent(coun);
		this.addEntity(ent);
		
		return ent;
	}
	
	public Entity constructAsteroid(AsteroidSize size, Vector2f position, float rotation, Vector2f velocity, float velocityR) {
		float[] asteroidModel = new float[]{15,8, 10,0, 1,3, 1,12, 10,15};
		float centerOffsetX = 7.5f;
		float centerOffsetY = 7.5f;
		float scaleX = size.scaleX;
		float scaleY = size.scaleY;
		
		int health = 1;
		
		Structure struct = new Structure(asteroidModel, centerOffsetX, centerOffsetY, scaleX, scaleY);
		struct.setPosition(position, rotation);
		Physics phys = new Physics(struct, 1.0f, 1.0f);
		phys.setVelocity(velocity, velocityR);
		Health heal = new Health(health);
		Render rend = new Render(struct);
		Allegiance all = new Allegiance();
		
		Entity ent = new Entity(this, "Asteroid");
		ent.addComponent(struct);
		ent.addComponent(phys);
		ent.addComponent(heal);
		ent.addComponent(rend);
		ent.addComponent(all);
		
		this.addEntity(ent);
		
		return ent;
	}
	
	// The spawn asteroid offspring
	
//	private void spawnOffspring() {
//	Asteroid[] asteroids = GameplayScreen.allocateAsteroids(size.numberOfOffspring);
//	for (int i = 0; i < asteroids.length; i++) {
//		Body bod = asteroids[i].body;
//		bod.centerX = body.centerX;
//		bod.centerY = body.centerY;
//		asteroids[i].size = size.getOffspringSize();
//		bod.rotation = body.rotation + size.baseAngles[i] + MainApplication.rand.nextFloat()-.5f;
//		
//		bod.velocityX = (float)(Math.cos(bod.rotation)) * asteroids[i].maxSpeed;
//		bod.velocityY = (float)(Math.sin(bod.rotation)) * asteroids[i].maxSpeed;
//		bod.velocityR = (MainApplication.rand.nextFloat() - 0.5f);
//		asteroids[i].hitPoints.setHealth(asteroids[i].size.maxHealth);
//	}
//}
	
	public void resolveCollision(Shape collision, Entity owner) {
//		if (!owner.isAlive()) {
//			
//		}
		for (Shape other : collisionBoxes.keySet()) {
			if (other.intersects(collision)) {
				//if (collisionBoxes.get(other).isAlive()) {
					CollisionMessage cmOther = new CollisionMessage(owner);
					CollisionMessage cmOwner = new CollisionMessage(collisionBoxes.get(other));
					owner.dispatchMessage(MessageType.ENTITY_COLLIDE, cmOwner);
					collisionBoxes.get(other).dispatchMessage(MessageType.ENTITY_COLLIDE, cmOther);
				//}
			}
		}
		collisionBoxes.put(collision, owner);
	}
	
	public void killEntity(Entity ent) {
		EntityDieMessage edm = new EntityDieMessage();
		ent.dispatchMessage(MessageType.ENTITY_DIE, edm);
		
	}
}
