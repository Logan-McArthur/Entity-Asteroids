package com.PromethiaRP.Draeke.Asteroids;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.command.MouseButtonControl;

public class InputManager implements InputProviderListener{

	InputProvider provider;

	private Map<String, Boolean> controlMap = new HashMap<String, Boolean>();
	
	public InputManager(GameContainer container) {
		provider = new InputProvider(container.getInput());
		provider.addListener(this);
	}
	
	@Override
	public void controlPressed(Command command) {
		BasicCommand cmd;
		if (command instanceof BasicCommand) {
			cmd = (BasicCommand) command;
			controlMap.put(cmd.getName(), new Boolean(true));
			return;
		}

		
	}

	@Override
	public void controlReleased(Command command) {
		BasicCommand cmd;
		if (command instanceof BasicCommand) {
			cmd = (BasicCommand) command;
			controlMap.put(cmd.getName(), new Boolean(false));
			return;
		}
		
	}
	
	public boolean isControlPressed(String command) {
		return controlMap.get(command).booleanValue();
	}
	
	public void createKeyControl(int key, String name) {
		BasicCommand cmd = new BasicCommand(name);
		provider.bindCommand(new KeyControl(key), cmd);
		controlMap.put(name, new Boolean(false));
	}

	public void createMouseControl(int button, String name) {
		BasicCommand cmd = new BasicCommand(name);
		provider.bindCommand(new MouseButtonControl(button), cmd);
		controlMap.put(name, new Boolean(false));
	}
}
