package app.pixel.jsnake.object;
/**
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Mob extends Sprite {

	protected float runSpeed = 50.0f;
	
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
