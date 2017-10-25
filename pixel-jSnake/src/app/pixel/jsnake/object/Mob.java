package app.pixel.jsnake.object;

import app.pixel.jsnake.input.Direction;

/**
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Mob extends Sprite {
	
	//direction by default
	protected Direction direction = Direction.LEFT;

	protected float runSpeed = 60.0f;	
	public float time = 0;	
	public float startPosX = 0;
	public float startPosY = 0;	
	
	public Mob(float posX, float posY) {
		super(posX, posY);
		this.startPosX = posX;
		this.startPosY = posY;
	}

	public void backToStartPoint() {
		this.posX = startPosX;
		this.posY = startPosY;
	}

}
