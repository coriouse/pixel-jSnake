package app.pixel.jsnake.object;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Map;

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

	}

	int count = 0;

	Map<Direction, Float[]> step = null;

	int index = 0;
	public void update(float deltaTime) {
	
		if (step == null && !this.mob.steps.isEmpty()) {
			
			if(this.mob.steps.size()-1 == index)
				step = this.mob.steps.get(index);
				
			//System.out.println("Step="+step+ ", order=" + this.order+", step size="+this.mob.steps.size()+", steps="+Arrays.toString(this.mob.steps.toArray()));
			
		}

		if (step != null && step.containsKey(Direction.DOWN) && posX < step.get(Direction.DOWN)[0]) {
			direction = Direction.DOWN;
			count++;
			if (count == 1) {
				posX = step.get(Direction.DOWN)[0];
				posY = step.get(Direction.DOWN)[1];			
				step = null;
				index++;
					
				System.out.println("tails=" + mob.tails + ", order=" + this.order+", direction = DOWN, posX="+posX+", posY="+posY+", index="+index);
			
			}
			count = 0;
		}

		if (step != null && step.containsKey(Direction.LEFT) &&  Math.abs(posY) > Math.abs(step.get(Direction.LEFT)[1])) {
			direction = Direction.LEFT;
			count++;
			if (count == 1) {
				posX = step.get(Direction.LEFT)[0];
				posY = step.get(Direction.LEFT)[1];	
				step = null;
				index++;
			
				
				System.out.println("tails=" + mob.tails + ", order=" + this.order+", direction = LEFT, posX="+posX+", posY="+posY);
			}
			count = 0;
		}
		
		if (step != null && step.containsKey(Direction.UP) &&  Math.abs(posX) < Math.abs(step.get(Direction.UP)[0])) {
			direction = Direction.UP;
			count++;
			if (count == 1) {
				posX = step.get(Direction.UP)[0];
				posY = step.get(Direction.UP)[1];	
				step = null;
				index++;
			
				
				System.out.println("tails=" + mob.tails + ", order=" + this.order+", direction = UP, posX="+posX+", posY="+posY);
			}
			count = 0;
		}
		
		
		if (step != null && step.containsKey(Direction.RIGHT) &&  Math.abs(posY) > Math.abs(step.get(Direction.RIGHT)[1])) {
			direction = Direction.RIGHT;
			count++;
			if (count == 1) {
				posX = step.get(Direction.RIGHT)[0];
				posY = step.get(Direction.RIGHT)[1];	
				step = null;
				index++;
			
				
				System.out.println("tails=" + mob.tails + ", order=" + this.order+", direction = RIGHT, posX="+posX+", posY="+posY);
			}
			count = 0;
		}
		

		float borderWidth = (Render.gameWidth / 2);
		float borderHeight = (Render.gameHeight / 2);

		float moveX = 0;
		float moveY = 0;

		switch (direction) {
		case LEFT:
			moveX -= runSpeed;
			break;
		case RIGHT:
			moveX += runSpeed;
			break;
		case UP:
			moveY -= runSpeed;
			break;
		case DOWN:

			moveY += runSpeed;
			break;

		}

		posX += moveX * deltaTime;
		posY += moveY * deltaTime;

		if (posX > borderWidth) {
			posX = -borderWidth;
		} else if (posX < 0 && Math.abs(posX) > borderWidth) {
			posX = borderWidth;
		}

		if (posY > borderHeight) {
			posY = -borderHeight;
		} else if (posY < 0 && Math.abs(posY) > borderHeight) {
			posY = borderHeight;
		}

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
