package com.PromethiaRP.Draeke.Asteroids;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Body extends Component{

	public Body(int ID) {
		super(ID);
		
	}


	protected Polygon structure;
	protected float centerOffsetX = 0.0f;
	protected float centerOffsetY = 0.0f;
	protected int frontVertex;
	protected float scaleX = 1.0f;
	protected float scaleY = 1.0f;

	// Mostly Consistent
	public float mass;

	protected float centerX = 0.0f;
	protected float centerY = 0.0f;

	protected float velocityX = 0.0f;
	protected float velocityY = 0.0f;

	protected float velocityR = 0.0f;

	protected float rotation = 0.0f;


	public void setStructure(float[] points, float centerOffsetX, float centerOffsetY, int front) {
		structure = new Polygon(points);
		this.centerOffsetX = centerOffsetX;
		this.centerOffsetY = centerOffsetY;
		frontVertex = front;

	}

	public Shape getTransform() {
		Transform trans = Transform.createRotateTransform(rotation, centerOffsetX, centerOffsetY);
		return structure.transform(Transform.createScaleTransform(scaleX, scaleY)).transform(trans).transform(Transform.createTranslateTransform(centerX - centerOffsetX, centerY - centerOffsetY));
	}

	
	/**
	 * 
	 * @return The actual center x coordinate
	 */
	public float getTrueCenterX() {
		return centerX + centerOffsetX;
	}
	/**
	 * 
	 * @return The actual center y coordinate
	 */
	public float getTrueCenterY() {
		return centerY + centerOffsetY;
	}
	
	
	public void update(int delta) {


		if (Math.abs(velocityX + accelerationX * delta) < maxSpeed) {
			velocityX += accelerationX * delta;
		}
		if (Math.abs(velocityY + accelerationY * delta) < maxSpeed) {
			velocityY += accelerationY * delta;
		}
//		rotation += velocityR * delta * turnSpeedModifier;
//		centerX += velocityX * delta * speedModifier;
//		centerY += velocityY * delta * speedModifier;

	}
}
