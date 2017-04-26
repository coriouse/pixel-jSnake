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

	private void turnOf(Map<Direction, Float[]> step) {
		if (step == null && !this.mob.steps.isEmpty()) {
			if (this.mob.steps.size() - 1 == index) {
				step = this.mob.steps.get(index);
				System.out.println("Step=" + step + ", order=" + this.order + ", index=" + index + ", step size="
						+ this.mob.steps.size() + ", steps=" + Arrays.toString(this.mob.steps.toArray()));
			}
		}
		if (step != null && step.containsKey(step.entrySet().iterator().next().getKey()) && isAllowTurn(step)) {
			direction = step.entrySet().iterator().next().getKey();
			count++;
			if (count == 1) {
				posX = step.get(step.entrySet().iterator().next().getKey())[0];
				posY = step.get(step.entrySet().iterator().next().getKey())[1];
				step = null;
				index++;
			}
			count = 0;
		}
	}

	private boolean isAllowTurn(Map<Direction, Float[]> step) {
		Direction direction = step.entrySet().iterator().next().getKey();
		switch (direction) {
		case DOWN:
			return ((int) posX < Math.round(step.get(direction)[0]));
		case LEFT:
			return Math.abs((int) posY) >= Math.abs(Math.round(step.get(direction)[1]));
		case UP:
			return Math.abs((int) posX) >= Math.abs(Math.round(step.get(direction)[0]));
		case RIGHT:
			return Math.abs((int) posY) >= Math.abs(Math.round(step.get(direction)[1]));
		default:
			return false;
		}
	}

	int index = 0;

	public void update(float deltaTime) {

		turnOf(step);

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
