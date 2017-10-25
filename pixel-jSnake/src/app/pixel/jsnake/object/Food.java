package app.pixel.jsnake.object;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import app.pixel.jsnake.graphic.Render;

public class Food extends Sprite {

	private float radius = 15f;
	private int liveTime;
	
	
	public Food(float posX, float posY, int liveTime) {
		super(posX, posY);
		this.liveTime = liveTime;
	}

	private long lengthOfLifeBall = System.currentTimeMillis();
	private long awaitTime = 0;	

	boolean isVisible = false;

	long curentTime = 0;
	long curentTimeAwait = 0;

	public void update(float deltaTime) {
		showFood();
	}

	public void render(Graphics g) {
		if (isVisible) {
			g.setColor(Color.green);
			g.fillOval(((int) (posX - width / 2) + Render.gameWidth / 2) - (int) radius,
					((int) (posY - height / 2) + Render.gameHeight / 2) - (int) radius, (int) radius * 2,
					(int) radius * 2);
		}

	}

	private void showFood() {
		if (lengthOfLifeBall > 0 && awaitTime == 0) {
			curentTime = (System.currentTimeMillis() - lengthOfLifeBall) / 1000;
			System.out.println("curentTime=" + curentTime);
		}

		if (awaitTime > 0 && lengthOfLifeBall == 0) {
			curentTimeAwait = (System.currentTimeMillis() - awaitTime) / 1000;
			System.out.println("curentTimeAwait=" + curentTimeAwait);
		}

		if (curentTime > liveTime) {
			curentTime = 0;
			lengthOfLifeBall = 0;
			awaitTime = System.currentTimeMillis();
			isVisible = false;
			System.out.println("Hide ball");

		}

		if (curentTimeAwait > liveTime) {
			curentTimeAwait = 0;
			awaitTime = 0;
			lengthOfLifeBall = System.currentTimeMillis();
			isVisible = true;
			System.out.println("Show ball");
			posX = getPosRandom();
			posY = getPosRandom();
		}
	}
	
	private int getPosRandom() {
		Random random = new Random();
		return -250+random.nextInt(250);
	}
	

}
