package com.PromethiaRP.Draeke.Asteroids;

import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainApplication extends StateBasedGame {

	public static int TITLE_SCREEN = 0;
	public static int GAMEPLAY_SCREEN = 1;
	public static int CREDITS_SCREEN = 2;
	public static int GAMEOVER_SCREEN = 3;
	
	public static final int SCREEN_HEIGHT = 600;
	public static final int SCREEN_WIDTH = 800;
	public static Random rand = new Random();

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			int screenHeight = 600;
			int screenWidth = 800;

			AppGameContainer app = new AppGameContainer(new MainApplication("Asteroids", screenWidth, screenHeight));
			//app.setResizable(false);
			app.setTargetFrameRate(60);
			app.setDisplayMode(screenWidth, screenHeight, false);
			app.setShowFPS(false);
			app.setMultiSample(2);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public MainApplication(String name, int screenWidth, int screenHeight) {
		super(name);
		
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new TitleScreen());
		addState(new GameplayScreen());
		addState(new CreditsScreen());
		addState(new GameOverScreen());
		
		//container.setMouseCursor("C:\\Users\\Logan McArthur\\Pictures\\MouseCursor.png", 1, 1);
		
		this.enterState(GAMEPLAY_SCREEN);
	}

	

}
