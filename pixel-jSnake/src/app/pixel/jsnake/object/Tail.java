package app.pixel.jsnake.object;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

import app.pixel.jsnake.graphic.Render;
import app.pixel.jsnake.input.Direction;
import app.pixel.jsnake.input.Input;

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

	public void update(float deltaTime) {

		if (posX < this.mob.startPosX && this.mob.direction == Direction.DOWN) {
			direction = Direction.DOWN;
			count++;
			if (count == 1) {
				posX = this.mob.startPosX;
				posY = this.mob.startPosY;
			}
			count = 0;
		}

		if (Math.abs(posY) > Math.abs(this.mob.startPosY) && this.mob.direction == Direction.LEFT) {
			direction = Direction.LEFT;
			count++;
			if (count == 1) {
				posX = this.mob.startPosX;
				posY = this.mob.startPosY;
			}
			count = 0;

		}

		if (posX < this.mob.startPosX && this.mob.direction == Direction.UP) {
			direction = Direction.UP;
			count++;
			if (count == 1) {
				posX = this.mob.startPosX;
				posY = this.mob.startPosY;
			}
			count = 0;

		}

		if (Math.abs(posY) > Math.abs(this.mob.startPosY) && this.mob.direction == Direction.RIGHT) {
			direction = Direction.RIGHT;
			count++;
			if (count == 1) {
				posX = this.mob.startPosX;
				posY = this.mob.startPosY;
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
