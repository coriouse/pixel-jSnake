package app.pixel.jsnake.object;

import app.pixel.jsnake.input.Direction;

/**
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Mob extends Sprite {

	protected Direction direction = Direction.LEFT;

	protected float runSpeed = 60.0f;
	
	public int order;

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
