package app.pixel.jsnake.object;

import java.awt.Color;
import java.awt.Graphics;

import app.pixel.jsnake.graphic.Render;

public class Tail extends Mob {

	private Mob mob;

	public Tail(float posX, float posY, Mob mob) {
		super(posX, posY);
		this.mob = mob;
		this.width = 10;
		this.height = 20;

	}

	public void update(float deltaTime) {

		posX = mob.posX + 20;
		posY = mob.posY;

		System.out.println("Tail posX=" + posX + ", posY=" + posY);

	}

	public void render(Graphics g) {
		g.setColor(new Color(110, 70, 40));
		g.fillRect((int) (posX - width / 2) + Render.gameWidth / 2, (int) (posY - height / 2) + Render.gameHeight / 2,
				(int) width, (int) height);

	}

}
