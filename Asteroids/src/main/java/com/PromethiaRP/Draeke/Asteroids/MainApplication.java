package com.PromethiaRP.Draeke.Asteroids;

import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class MainApplication {


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

			AppGameContainer app = new AppGameContainer(new GameplayScreen("Asteroids"));
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
}
