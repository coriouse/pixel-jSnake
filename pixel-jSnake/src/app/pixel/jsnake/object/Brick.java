package app.pixel.jsnake.object;

import java.awt.Color;
import java.awt.Graphics;

import app.pixel.jsnake.graphic.Render;


/**
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Brick extends Sprite {
	
	private final static int WIDTH = 80;
	private final static int HEIGHT = 20;
	

	public Brick(float posX, float posY) {
		super(posX, posY);
		this.width = WIDTH;
		this.height = HEIGHT;
		
	}

	public void update(float deltaTime) {
		
		
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) (posX - width / 2) + Render.gameWidth / 2, (int) (posY - height / 2) + Render.gameHeight / 2,
				(int) width, (int) height);
	}
	

}
