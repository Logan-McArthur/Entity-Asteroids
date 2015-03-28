package com.PromethiaRP.Draeke.Asteroids.Component;

import com.PromethiaRP.Draeke.Asteroids.GameWorld;
import com.PromethiaRP.Draeke.Asteroids.InputManager;
import com.PromethiaRP.Draeke.Asteroids.Exceptions.IllegalMessageException;
import com.PromethiaRP.Draeke.Asteroids.Messages.ActionMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.ImpulseMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;
import com.PromethiaRP.Draeke.Asteroids.Messages.UpdateMessage;

public class KeyboardInput extends Component {

	InputManager inputManager;
	
	public KeyboardInput(InputManager in) {
		inputManager = in;
		this.messageTypes.add(MessageType.UPDATE_INPUT);
	}

	
	public void pollInput() {
		float thrustForce = 1f;
		float torque = 1f;
		float sideForce = .8f;
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
		}
		if (inputManager.isControlPressed("TurnRight")) {
			turnThrust += 1;
			//play.body.setVelocityR(1);
		}
		if (inputManager.isControlPressed("StrafeLeft")) {
			sidewaysThrust += -1;	// Remember, +y is down
		}
		if (inputManager.isControlPressed("StrafeRight")) {
			sidewaysThrust += 1;
		}
		//else {
		//	play.body.setVelocityR(0);
		//}
		

		shoot = inputManager.isControlPressed("Shoot");
		Message msgAction = new ActionMessage(shoot);
		entity.dispatchMessage(MessageType.ACTION_SHOOT, msgAction);
		Message impulseMessage = new ImpulseMessage(thrustForce * forwardThrust, sideForce * sidewaysThrust, torque * turnThrust);
		entity.dispatchMessage(MessageType.IMPULSE, impulseMessage);
	}

	
	public void handleMessage(GameWorld gameWorld, MessageType type, Message msg) {
		switch (type) {
		case UPDATE_INPUT:
			if (msg instanceof UpdateMessage) {
				pollInput();
			} else {
				throw new IllegalMessageException("KeyboardInput did not receive UpdateMessage with the corresponding type UPDATE_INPUT");
			}
			break;
		default:
			
			break;
		}
	}
	
}
