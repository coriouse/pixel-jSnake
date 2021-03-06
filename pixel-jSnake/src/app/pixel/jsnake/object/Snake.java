package app.pixel.jsnake.object;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import com.sun.glass.events.KeyEvent;

import app.pixel.jsnake.graphic.Render;
import app.pixel.jsnake.input.Direction;
import app.pixel.jsnake.input.Input;
import app.pixel.jsnake.levels.LevelExecuter;
import app.pixel.jsnake.levels.LevelFactory;
import app.pixel.jsnake.levels.LevelName;
import app.pixel.jsnake.levels.LevelStore;

/**
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Snake extends Mob {

	public int lengthShake = 0;

	private List<Float[]> steps = new LinkedList<Float[]>();

	public Snake(float posX, float posY, float width, float height) {
		super(posX, posY);
		this.width = width;
		this.height = height;
		addPos(posX, posY);
	}
	
	@Override
	public void update(float deltaTime) {

		float borderWidth = (Render.gameWidth / 2);
		float borderHeight = (Render.gameHeight / 2);

		float moveX = 0;
		float moveY = 0;

		if (Input.getKeyUp(KeyEvent.VK_LEFT) && direction != Direction.RIGHT) {
			direction = Direction.LEFT;
			startPosX = posX;
			startPosY = posY;
			addPos(startPosX, startPosY);

		}
		if (Input.getKeyUp(KeyEvent.VK_RIGHT) && direction != Direction.LEFT) {
			direction = Direction.RIGHT;
			startPosX = posX;
			startPosY = posY;
			addPos(posX, posY);

		}

		if (Input.getKeyUp(KeyEvent.VK_UP) && direction != Direction.DOWN) {
			direction = Direction.UP;
			startPosX = posX;
			startPosY = posY;
			addPos(startPosX, startPosY);

		}

		if (Input.getKeyUp(KeyEvent.VK_DOWN) && direction != Direction.UP) {
			direction = Direction.DOWN;
			startPosX = posX;
			startPosY = posY;
			addPos(startPosX, startPosY);
		}
		
		Sprite[] sprites = getColliders(posX, posY);
		if (sprites.length > 0) {
			for (Sprite sprite : sprites) {
				if (sprite instanceof Food) {
				lengthen(posX + (lengthShake * 30), posY);
				((Food) sprite).isVisible = false;
				Score.SCORE += steps.size();
				continue;
				}
			}
		}
		

		// TODO only fo testing
		/*if (Input.getKeyUp(KeyEvent.VK_SPACE)) {			
			lengthen(posX + (lengthShake * 30), posY);
		}*/
		
		if (Input.getKeyUp(KeyEvent.VK_ESCAPE)) {
			LevelFactory executeLevel = new LevelStore();
			LevelExecuter levelExecuter = executeLevel.getLevel(LevelName.MENU);
			levelExecuter.execute();
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
		
		addPos(posX, posY);

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
	
	

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(110, 70, 40));
		if (lengthShake > 0) {
			for (Float[] pos : steps) {
				g.fillRect((int) (pos[0] - width / 2) + Render.gameWidth / 2,
						(int) (pos[1] - height / 2) + Render.gameHeight / 2, (int) width, (int) height);
			}
		} else {
			g.fillRect((int) (posX - width / 2) + Render.gameWidth / 2,
					(int) (posY - height / 2) + Render.gameHeight / 2, (int) width, (int) height);
		}
	}

	private void addPos(float posX, float posY) {
		shift(new Float[] { posX, posY, 0f });
	}

	private void lengthen(float posX, float posY) {
		++lengthShake;
		steps.add(new Float[] { posX, posY, 0f });

	}

	private void shift(Float[] step) {
		Float[] temp = null;
		for (int i = 0; i < lengthShake; i++) {
			temp = steps.get(i);
			steps.set(i, step);
			step = temp;
		}
	}
	
	@SuppressWarnings("unused")
	private void cycleDead(int posX, int posY) {
		//TODO need to finish		
	}


}
