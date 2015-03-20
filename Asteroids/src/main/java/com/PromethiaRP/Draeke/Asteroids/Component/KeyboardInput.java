package com.PromethiaRP.Draeke.Asteroids.Component;

import com.PromethiaRP.Draeke.Asteroids.Entity;
import com.PromethiaRP.Draeke.Asteroids.InputManager;
import com.PromethiaRP.Draeke.Asteroids.Messages.ActionMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.ImpulseMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

public class KeyboardInput extends Component {

	InputManager inputManager;
	
	public KeyboardInput(Entity en, InputManager in) {
		super(en);
		inputManager = in;
	}

	@Override
	public void update(int delta) {
		float thrustForce = 1.0f;
		float torque = 1.0f;
		int turnThrust = 0;
		int forwardThrust = 0;
		int sidewaysThrust = 0;
		boolean shoot = false;
		if (inputManager.isControlPressed("Forward")) {
			forwardThrust += 1;
			//entity.body.applyForce(thrustForce * (float)Math.cos(play.body.getRotation()), thrustForce * (float)Math.sin(play.body.getRotation()));
		}
		if (inputManager.isControlPressed("Backward")) {
			forwardThrust += -1;
			//entity.body.applyForce(-1 *thrustForce * (float)Math.cos(play.body.getRotation()), -1*thrustForce * (float)Math.sin(play.body.getRotation()));
		}
		
		
		if (inputManager.isControlPressed("TurnLeft")) {
			turnThrust += -1;
			//play.body.setVelocityR(-1);
		} else if (inputManager.isControlPressed("TurnRight")) {
			turnThrust += 1;
			//play.body.setVelocityR(1);
		}
		//else {
		//	play.body.setVelocityR(0);
		//}
		
//		if (inputManager.isControlPressed("Shoot")) {
//			play.setShooting(true);
//		} else {
//			play.setShooting(false);
//		}
		shoot = inputManager.isControlPressed("Shoot");
		Message msgAction = new ActionMessage("Shoot", shoot);
		entity.dispatchMessage(MessageType.ACTION, msgAction);
		
	}

	
	public void handleMessage(MessageType type, Message msg) {
		// This component does not receive messages
	}

	@Override
	public boolean receives(MessageType type) {
		// No messages
		return false;
	}
	
}
