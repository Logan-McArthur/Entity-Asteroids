package com.PromethiaRP.Draeke.Asteroids.Component;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.PromethiaRP.Draeke.Asteroids.GameWorld;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;

public class Structure extends Component{

	public static final float DEFAULT_SCALE_X = 1.0f;
	public static final float DEFAULT_SCALE_Y = 1.0f;
	
//	protected GameWorld gameWorld;
	
		// How the Body looks
		protected Polygon structure;
		protected float centerOffsetX = 0.0f;
		protected float centerOffsetY = 0.0f;

		// Removing scale from Structure
//		protected float scaleX = 1.0f;
//		protected float scaleY = 1.0f;
		
		// Where the Body is and how it is oriented
		protected Vector2f center = new Vector2f(0.0f, 0.0f);
//		protected float centerX = 0.0f;
//		protected float centerY = 0.0f;
		protected float rotation = 0.0f;

		// Setters for Positional fields
//		public void setPositionX(float x) {
//			center.x = x;
//		}
//		public void setPositionY(float y) {
//			center.y = y;
//		}
		public void setRotation(float r) {
			this.rotation = r;
		}
//		public void setPosition(float posX, float posY, float rotation) {
//			center.set(posX, posY);
//			this.rotation = rotation;
//		}
//		
		public void setPosition(Vector2f position, float rotation) {
			center.set(position);
			this.rotation = rotation;
		}
		
//		public void moveX(float deltaX) {
//			this.centerX += deltaX;
//		}
//		public void moveY(float deltaY) {
//			this.centerY += deltaY;
//		}
		public void move(Vector2f delta) {
			center.add(delta);
		}
		public void moveRotation(float deltaRotation) {
			this.rotation += deltaRotation;
		}
		
		// Getters for Positional fields
		public float getPositionX() {
			return center.x;
		}
		public float getPositionY() {
			return center.y;
		}
		/**
		 * Returns an immutable Vector2f equal to the position of the Structure
		 * @return center
		 */
		public Vector2f getPosition() {
			return center.copy();
		}
		public float getRotation() {
			return this.rotation;
		}
		
		public Structure(float[] points, float centerOffX, float centerOffY, float scaleX, float scaleY) {
			setStructure(new Polygon(points), centerOffX, centerOffY);
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
//			Transform rotate = Transform.createRotateTransform(rotation);//, centerOffsetX, centerOffsetY);
//			Transform scale = Transform.createScaleTransform(scaleX, scaleY);
//			Transform translatePosition = Transform.createTranslateTransform(center.x, center.y);
//			return structure.transform(translateOffset).transform(rotate).transform(scale.concatenate(translatePosition));
//			return structure.transform(translateOffset.concatenate(rotate).concatenate(scale).concatenate(translatePosition));
//			return structure.transform(Transform.createScaleTransform(scaleX, scaleY)).transform(trans.concatenate(Transform.createTranslateTransform(centerX - centerOffsetX, centerY - centerOffsetY)));

//			return structure.transform(Transform.createScaleTransform(scaleX, scaleY)).transform(trans).transform(Transform.createTranslateTransform(center.x - centerOffsetX, center.y - centerOffsetY));
			return structure.transform(trans).transform(Transform.createTranslateTransform(center.x - centerOffsetX, center.y - centerOffsetY));

		}
		
		public Vector2f transformVector(Vector2f vec) {
			vec = vec.copy();
			
			vec = Transform.createRotateTransform(rotation).transform(vec);
			
	//		vec.x = vec.x + centerOffsetX;
	//		vec.y = vec.y + centerOffsetY;
	//		vec.add(center);
			return vec;
			
//			Transform scale = Transform.createScaleTransform(scaleX, scaleY);
//			Transform rotate = Transform.createRotateTransform(rotation, centerOffsetX, centerOffsetY);
//			
//			Transform translate = Transform.createTranslateTransform(center.x - centerOffsetX, center.y - centerOffsetY);
//			return translate.transform(rotate.transform(scale.transform(vec)));
//			structure.transform(Transform.createScaleTransform(scaleX, scaleY)).transform(trans).transform(Transform.createTranslateTransform(center.x - centerOffsetX, center.y - centerOffsetY));
			
		}
		
		public void handleMessage(GameWorld gameWorld, MessageType type, Message msg) {
			switch (type) {
			case MOVE:
				
				break;
			default:
				
				break;
			}
		}
}
