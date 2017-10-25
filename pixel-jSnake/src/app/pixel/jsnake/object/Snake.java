package app.pixel.jsnake.object;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sun.glass.events.KeyEvent;

import app.pixel.jsnake.graphic.Render;
import app.pixel.jsnake.input.Direction;
import app.pixel.jsnake.input.Input;

public class Snake extends Mob {

	public float time = 0;
	public int lengthShake = 0;

	public List<Map<Direction, Float[]>> steps = new LinkedList<Map<Direction, Float[]>>();

	public Snake(float posX, float posY, float width, float height) {
		super(posX, posY);
		this.width = width;
		this.height = height;
		addPos(Direction.LEFT, posX, posY);

	}

	int count = 0;

	public void update(float deltaTime) {

		this.time = deltaTime;

		float borderWidth = (Render.gameWidth / 2);
		float borderHeight = (Render.gameHeight / 2);

		float moveX = 0;
		float moveY = 0;

		if (Input.getKeyUp(KeyEvent.VK_LEFT) && direction != Direction.RIGHT) {
			System.out.println("LEFT KEY");
			direction = Direction.LEFT;
			startPosX = posX;
			startPosY = posY;
			addPos(Direction.LEFT, startPosX, startPosY);

		}
		if (Input.getKeyUp(KeyEvent.VK_RIGHT) && direction != Direction.LEFT) {
			direction = Direction.RIGHT;
			startPosX = posX;
			startPosY = posY;
			addPos(Direction.RIGHT, posX, posY);

		}

		if (Input.getKeyUp(KeyEvent.VK_UP) && direction != Direction.DOWN) {
			direction = Direction.UP;
			startPosX = posX;
			startPosY = posY;
			addPos(Direction.UP, startPosX, startPosY);

		}

		if (Input.getKeyUp(KeyEvent.VK_DOWN) && direction != Direction.UP) {
			System.out.println("DOWN KEY");
			direction = Direction.DOWN;
			startPosX = posX;
			startPosY = posY;
			addPos(Direction.DOWN, startPosX, startPosY);
		}

		// TODO only fo testing
		if (Input.getKeyUp(KeyEvent.VK_SPACE)) {
			++lengthShake;
			addStep(direction, posX + (lengthShake * 20), posY);
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
		addPos(direction, posX, posY);

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
		if (lengthShake > 0) {
			for (Map<Direction, Float[]> pos : steps) {
				g.fillRect((int) (pos.entrySet().iterator().next().getValue()[0] - width / 2) + Render.gameWidth / 2,
						(int) (pos.entrySet().iterator().next().getValue()[1] - height / 2) + Render.gameHeight / 2,
						(int) width, (int) height);
			}
		} else {
			g.fillRect((int) (posX - width / 2) + Render.gameWidth / 2,
					(int) (posY - height / 2) + Render.gameHeight / 2, (int) width, (int) height);
		}
	}

	public void addPos(Direction direction, float posX, float posY) {
		Map<Direction, Float[]> step = new HashMap<>();
		step.put(direction, new Float[] { posX, posY, 0f });
		shiftArrRight(step);

	}

	public void addStep(Direction direction, float posX, float posY) {
		Map<Direction, Float[]> step = new HashMap<>();
		step.put(direction, new Float[] { posX, posY, 0f });
		steps.add(step);

	}

	public void shiftArrRight(Map<Direction, Float[]> step) {
		Map<Direction, Float[]> temp = null;
		for (int i = 0; i < lengthShake; i++) {
			temp = steps.get(i);
			steps.set(i, step);
			step = temp;
		}
	}

}
