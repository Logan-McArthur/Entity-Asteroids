package com.PromethiaRP.Draeke.Asteroids;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TitleScreen extends BasicGameState{

	private List<Asteroid> asteroids = new ArrayList<Asteroid>();
	
	private InputManager inputManager;// = new InputManager();
	
	private Cooldown asteroidSpawnCooldown = new Cooldown();
	
	Image startButtonImage;
	Image quitButtonImage;
	private int maxBackgroundAsteroids = 30;
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		asteroidSpawnCooldown.start(MainApplication.rand.nextInt(300) + 1);
		inputManager = new InputManager(container);
		inputManager.createMouseControl(Input.MOUSE_LEFT_BUTTON, "LeftMouse");
		startButtonImage = new Image("C:\\Users\\Logan McArthur\\Pictures\\StartButton.png");
		quitButtonImage =new Image("C:\\Users\\Logan McArthur\\Pictures\\QuitButton.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics grafix)
			throws SlickException {
		grafix.setColor(Color.white);
		grafix.setLineWidth(2);
		
		grafix.drawImage(startButtonImage, 175, 300);
		grafix.drawImage(quitButtonImage, 425, 300);
		
		for (int i = 0; i < asteroids.size(); i++) {
			if (asteroids.get(i).alive){
				grafix.setColor(Color.black);
				grafix.fill(asteroids.get(i).getTransform());
				grafix.setColor(Color.white);
				grafix.draw(asteroids.get(i).getTransform());
			}
		}
		
		//grafix.drawString("Asteroids", 100, 100);
		}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		{ // Background asteroid update block
			for (int i = 0; i < asteroids.size(); i++) {
				if (asteroids.get(i).alive) {
					asteroids.get(i).update(delta);
					if (asteroids.get(i).getTrueCenterX()  < 0) {
						asteroids.get(i).alive = false;
					}
					if (asteroids.get(i).getTrueCenterY() < 0) {
						asteroids.get(i).alive = false;
					}
					if (asteroids.get(i).getTrueCenterX() > MainApplication.SCREEN_WIDTH) {
						asteroids.get(i).alive = false;
					}
					if (asteroids.get(i).getTrueCenterY() > MainApplication.SCREEN_HEIGHT) {
						asteroids.get(i).alive = false;
					}
				}
			}
		}
		
		{ // Spawn new asteroid block
			asteroidSpawnCooldown.update(delta);
			if (! asteroidSpawnCooldown.counting()) {
				int num = MainApplication.rand.nextInt(4);
				if (num == 0) {
					asteroidSpawnCooldown.start(20);
				} else {
					for (int i = 0; i < num; i++) {
						spawnAsteroid();
					}
					asteroidSpawnCooldown.start(50 + MainApplication.rand.nextInt(2500));
				}
			}
		}
	}

	@Override
	public int getID() {
		
		return MainApplication.TITLE_SCREEN;
	}

	private void spawnAsteroid() {
		int asteroidIndex = getDeadAsteroidIndex();
		if (asteroidIndex == -1) {
			return;
		}
		Asteroid aster = asteroids.get(asteroidIndex);
		int ranX = Math.round(MainApplication.rand.nextInt(2));
		int ranY = Math.round(MainApplication.rand.nextInt(2));
		float xMod = 1.0f;
		float yMod = 1.0f;
		if (MainApplication.rand.nextBoolean()) {
			xMod = MainApplication.rand.nextFloat();
		}else {
			yMod = MainApplication.rand.nextFloat();
		}
		aster.recycle(ranX * MainApplication.SCREEN_WIDTH * xMod,
						ranY * MainApplication.SCREEN_HEIGHT * yMod,
						AsteroidSize.LARGE, 
						(xMod * yMod) * 2 - 1);
		aster.velocityX = Math.round((.5f - ranX)*2) * MainApplication.rand.nextFloat() * aster.maxSpeed * .4f;
		aster.velocityY = Math.round((.5f - ranY)*2) * MainApplication.rand.nextFloat() * aster.maxSpeed * .4f;
		aster.velocityR = (MainApplication.rand.nextFloat()-.5f) * 2 / 3;
		}
	
	private int getDeadAsteroidIndex() {
		for (int i = 0; i < asteroids.size(); i++) {
			if(asteroids.get(i).alive) {
				continue;
			} else {
				return i;
			}
		}
		if (asteroids.size() < maxBackgroundAsteroids) {
			asteroids.add(new Asteroid(AsteroidSize.LARGE, 0.0f, 0.0f));
			return asteroids.size()-1;
		}
		return -1;
	}
	
}
