package app.pixel.jsnake.game;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

import app.pixel.jsnake.graphic.Render;
import app.pixel.jsnake.input.Input;
import app.pixel.jsnake.levels.LevelExecuter;
import app.pixel.jsnake.levels.LevelFactory;
import app.pixel.jsnake.levels.LevelName;
import app.pixel.jsnake.levels.LevelStore;
import app.pixel.jsnake.object.Sprite;

public class Menu extends Sprite {

	private int backGroundPosX = 0;
	private int backGroundPosY = 0;

	private int width = 100;
	private int height = 20;

	private int itemLelements = 4;
	private int currentItemElement = 0;

	private Integer[] itemPosition = { -32, -14, 10, 26 };

	public Menu(float posX, float posY) {
		super(posX, posY);
		backGroundPosX = (int) posX + 10;
		backGroundPosY = (int) posY + 8;
	}

	@Override
	public void update(float deltaTime) {

		if (Input.getKeyUp(KeyEvent.VK_UP)) {
			--currentItemElement;
			if (currentItemElement == -1) {
				currentItemElement = itemLelements - 1;
				this.backGroundPosY = itemPosition[currentItemElement];
			} else {
				backGroundPosY = itemPosition[currentItemElement];
			}

		}

		if (Input.getKeyUp(KeyEvent.VK_DOWN)) {
			++currentItemElement;
			if (currentItemElement > itemLelements - 1) {
				currentItemElement = 0;
				this.backGroundPosY = itemPosition[currentItemElement];
			} else {
				backGroundPosY = itemPosition[currentItemElement];
			}
		}

		if (Input.getKeyUp(KeyEvent.VK_ENTER)) {
			System.out.println(currentItemElement);
			LevelFactory executeLevel = new LevelStore();
			switch (currentItemElement) {
			case 0:
				executeLevel.getLevel(LevelName.LEVEL1).execute();

				break;
			case 1:
				executeLevel.getLevel(LevelName.LEVEL2).execute();
				break;
			case 2:
				break;
			case 3:
				System.exit(1);
				break;
			}
		}

	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.gray);
		g.setFont(g.getFont().deriveFont(20f));
		g.drawString("Level 1", (int) ((posX - width / 2) + Render.gameWidth / 2) + 35,
				(int) ((posY - height / 2) + Render.gameHeight / 2) + 25);

		g.setFont(g.getFont().deriveFont(20f));
		g.drawString("Level 2", (int) ((posX - width / 2) + Render.gameWidth / 2) + 35,
				(int) (((posY + 20) - height / 2) + Render.gameHeight / 2) + 25);

		g.setFont(g.getFont().deriveFont(20f));
		g.drawString("Level 3", (int) ((posX - width / 2) + Render.gameWidth / 2) + 35,
				(int) (((posY + 40) - height / 2) + Render.gameHeight / 2) + 25);

		g.setFont(g.getFont().deriveFont(20f));
		g.drawString("Exit", (int) ((posX - width / 2) + Render.gameWidth / 2) + 35,
				(int) (((posY + 60) - height / 2) + Render.gameHeight / 2) + 25);

		g.setColor(new Color(220, 220, 220, 127));

		g.fillRect((int) (backGroundPosX - width / 2) + Render.gameWidth / 2,
				(int) (backGroundPosY - height / 2) + Render.gameHeight / 2, width, height);

	}

}
