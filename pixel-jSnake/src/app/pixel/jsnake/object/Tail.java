package app.pixel.jsnake.object;

import java.awt.Color;
import java.awt.Graphics;


import app.pixel.jsnake.graphic.Render;
import app.pixel.jsnake.input.Direction;

public class Tail extends Mob {

	private Mob mob;
	

	public Tail(float posX, float posY, Mob mob, int order) {
		super(posX, posY);
		this.mob = mob;
		this.width = 20;
		this.height = 20;
		this.order = order;
		
		System.out.println("Tail posX=" + posX + ", posY=" + posY+", order="+this.order+", "+mob);

	}

	public void update(float deltaTime) {
		
		

		if(mob.direction == Direction.LEFT) {
			posX = mob.posX+20;			
		}
		
		if(mob.direction == Direction.DOWN) {
			posX = mob.posX;
			posY = mob.posY-20;
			//moveX = mob.posX;
			//moveY = mob.posY-this.order;
		}
		
		if(mob.direction == Direction.RIGHT) {
			//moveX = mob.posX-this.order;
			//moveY = mob.posY;
		}
		
		if(mob.direction == Direction.UP) {
			//moveX = mob.posX;
			//moveY = mob.posY+this.order;
		}
		
		//posX += moveX;
		//posY += moveY;
		
	//	System.out.println("Tail posX=" + posX + ", posY=" + posY+", order="+order);

	}

	public void render(Graphics g) {
		g.setColor(new Color(110, 70, 40));
		g.fillRect((int) (posX - width / 2) + Render.gameWidth / 2, (int) (posY - height / 2) + Render.gameHeight / 2,
				(int) width, (int) height);
	}

	@Override
	public String toString() {
		return "Tail [order=" + order + "]";
	}
	
	

}
