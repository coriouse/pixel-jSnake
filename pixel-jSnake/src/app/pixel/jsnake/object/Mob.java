package app.pixel.jsnake.object;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import app.pixel.jsnake.input.Direction;

/**
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Mob extends Sprite {

	protected Direction direction = Direction.LEFT;

	protected float runSpeed = 60.0f;
	
	public float time = 0;
	
	public int order;

	public float startPosX = 0;
	public float startPosY = 0;

	
	public int tails = 0; 
	
	
	
	
	public List<Map<Direction, Float[]>> steps = new LinkedList<Map<Direction, Float[]>>();
	
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
