package app.pixel.jsnake.object;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;
import app.pixel.jsnake.graphic.Render;
import app.pixel.jsnake.input.Input;

public class Snake extends Mob {

	public Snake(float posX, float posY, float width, float height) {
		super(posX, posY);
		this.width = width;
		this.height = height;
	}

	public void update(float deltaTime) {

		float borderWidth = (Render.gameWidth / 2);
		float borderHeight = (Render.gameHeight / 2);

		float moveX = 0;
		float moveY = 0;

		if (Input.getKey(KeyEvent.VK_LEFT)) {
			moveX -= runSpeed;
		}
		if (Input.getKey(KeyEvent.VK_RIGHT)) {
			moveX += runSpeed;
		}

		if (Input.getKey(KeyEvent.VK_UP)) {
			moveY -= runSpeed;
		}

		if (Input.getKey(KeyEvent.VK_DOWN)) {
			moveY += runSpeed;
		}

		posX += moveX;
		posY += moveY;

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
