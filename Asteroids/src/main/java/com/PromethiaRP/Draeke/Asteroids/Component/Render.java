package com.PromethiaRP.Draeke.Asteroids.Component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import com.PromethiaRP.Draeke.Asteroids.GameWorld;
import com.PromethiaRP.Draeke.Asteroids.Exceptions.IllegalMessageException;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;
import com.PromethiaRP.Draeke.Asteroids.Messages.RenderMessage;

public class Render extends Component {

	private Structure struct;
	
	public Render(Structure struct) {
		this.struct = struct;
		this.messageTypes.add(MessageType.RENDER);
	}
	
	@Override
	public void handleMessage(GameWorld gameWorld, MessageType type, Message msg) {
		
		switch (type) {
		case RENDER:
			if (msg instanceof RenderMessage) {
				Graphics grafix = ((RenderMessage) msg).grafix;
				
				grafix.setColor(Color.black);
				Shape shap = struct.getTransform();
				grafix.fill(shap);
				grafix.setColor(Color.white);
				grafix.draw(shap);
			} else {
				throw new IllegalMessageException("Render did not receive RenderMessage with the corresponding type RENDER");
			}
			break;
		default:
				
			break;		
		}
	}

}
