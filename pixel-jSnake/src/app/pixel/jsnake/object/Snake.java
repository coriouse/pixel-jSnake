package app.pixel.jsnake.object;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

import app.pixe.jsnake.arena.Arena;
import app.pixel.jsnake.graphic.Render;
import app.pixel.jsnake.input.Direction;
import app.pixel.jsnake.input.Input;

public class Snake extends Mob {

	private int add = 0;

	public float time = 0;

	public Snake(float posX, float posY, float width, float height) {
		super(posX, posY);
		this.width = width;
		this.height = height;
	}

	public void update(float deltaTime) {

		this.time = deltaTime;

		float borderWidth = (Render.gameWidth / 2);
		float borderHeight = (Render.gameHeight / 2);

		float moveX = 0;
		float moveY = 0;

		if (Input.getKey(KeyEvent.VK_LEFT)) {
			direction = Direction.LEFT;
			startPosX = posX;
			startPosY = posY;

		}
		if (Input.getKey(KeyEvent.VK_RIGHT)) {
			direction = Direction.RIGHT;
			startPosX = posX;
			startPosY = posY;

		}

		if (Input.getKey(KeyEvent.VK_UP)) {
			direction = Direction.UP;
			startPosX = posX;
			startPosY = posY;
		}

		if (Input.getKey(KeyEvent.VK_DOWN)) {
			direction = Direction.DOWN;
			startPosX = posX;
			startPosY = posY;

		}

		// TODO only fo testing
		if (Input.getKey(KeyEvent.VK_SPACE)) {
			Arena.currentArena.addSprite(new Tail(posX + 20, posY, this, 1));
			Arena.currentArena.addSprite(new Tail(posX + 40, posY, this, 2));
			Arena.currentArena.addSprite(new Tail(posX + 60, posY, this, 3));
			Arena.currentArena.addSprite(new Tail(posX + 80, posY, this, 4));
			Arena.currentArena.addSprite(new Tail(posX + 100, posY, this, 5));
			Arena.currentArena.addSprite(new Tail(posX + 120, posY, this, 6));
			Arena.currentArena.addSprite(new Tail(posX + 140, posY, this, 7));
			Arena.currentArena.addSprite(new Tail(posX + 160, posY, this, 8));
			System.out.println("press");
			add++;
		}

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

}
