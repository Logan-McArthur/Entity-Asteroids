package com.PromethiaRP.Draeke.Asteroids.Component;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

public class Structure extends Component{

	public static final float DEFAULT_SCALE_X = 1.0f;
	public static final float DEFAULT_SCALE_Y = 1.0f;
	
		// How the Body looks
		protected Polygon structure;
		protected float centerOffsetX = 0.0f;
		protected float centerOffsetY = 0.0f;
		protected float scaleX = 1.0f;
		protected float scaleY = 1.0f;
		
		// Where the Body is and how it is oriented
		protected float centerX = 0.0f;
		protected float centerY = 0.0f;
		protected float rotation = 0.0f;

		// Setters for Positional fields
		public void setPositionX(float x) {
			this.centerX = x;
		}
		public void setPositionY(float y) {
			this.centerY = y;
		}
		public void setRotation(float r) {
			this.rotation = r;
		}
		public void setPosition(float posX, float posY, float rotation) {
			centerX = posX;
			centerY = posY;
			this.rotation = rotation;
		}
		
		public void moveX(float deltaX) {
			this.centerX += deltaX;
		}
		public void moveY(float deltaY) {
			this.centerY += deltaY;
		}
		public void moveRotation(float deltaRotation) {
			this.rotation += deltaRotation;
		}
		
		// Getters for Positional fields
		public float getPositionX() {
			return centerX;
		}
		public float getPositionY() {
			return centerY;
		}
		public float getRotation() {
			return this.rotation;
		}
		
		
		public Structure(float[] points, float centerOffX, float centerOffY) {
			this(points, centerOffX, centerOffY, DEFAULT_SCALE_X, DEFAULT_SCALE_Y);			
		}
		
		public Structure(float[] points, float centerOffX, float centerOffY, float scaleX, float scaleY) {
			setStructure(new Polygon(points), centerOffX, centerOffY);
			this.scaleX = scaleX;
			this.scaleY = scaleY;
			
			//this.messageTypes.add(MessageType.MOVE);
		}
		
		
		// Geometry methods
		public void setStructure(Polygon struct, float centerOffX, float centerOffY) {
			structure = struct;
			this.centerOffsetX = centerOffX;
			this.centerOffsetY = centerOffY;
		}

		public Shape getTransform() {
			// Translate (centerOffset)
			// Rotate (0,0)
			// Scale
			// Translate (position in space)
			Transform trans = Transform.createRotateTransform(rotation, centerOffsetX, centerOffsetY);
//			Transform translateOffset = Transform.createTranslateTransform(-centerOffsetX, -centerOffsetY);
//			Transform rotate = Transform.createRotateTransform(rotation);
//			Transform scale = Transform.createScaleTransform(scaleX, scaleY);
//			Transform translatePosition = Transform.createTranslateTransform(centerX, centerY);
//			return structure.transform(translateOffset.concatenate(rotate).concatenate(scale).concatenate(translatePosition));
			
			return structure.transform(Transform.createScaleTransform(scaleX, scaleY)).transform(trans).transform(Transform.createTranslateTransform(centerX - centerOffsetX, centerY - centerOffsetY));
		}

		
		
		public void scale(float scale) {
			scaleX *= scale;
			scaleY *= scale;
		}
		
		
		public void handleMessage(MessageType type, Message msg) {
			switch (type) {
			case MOVE:
				
				break;
			default:
				
				break;
			}
		}
}
