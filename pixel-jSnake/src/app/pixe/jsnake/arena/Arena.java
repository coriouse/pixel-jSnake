package app.pixe.jsnake.arena;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import app.pixel.jsnake.object.Sprite;
/**
 * Main area of level
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Arena {
	//TODO add levels
	public static Arena currentArena = null;

	private static long lastTime = System.nanoTime();

	public List<Sprite> spites = new ArrayList<Sprite>();
	public List<Sprite> addSpites = new ArrayList<Sprite>();
	public List<Sprite> removeSpites = new ArrayList<Sprite>();

	public static void update() {

		float deltaTime = (System.nanoTime() - lastTime) / 1000000000.0f;
		lastTime = System.nanoTime();
		for (Sprite sprite : currentArena.spites) {
			sprite.update(deltaTime);
		}

		for (Sprite sprite : currentArena.addSpites) {
			if (!currentArena.spites.contains(sprite)) {
				currentArena.spites.add(sprite);
			}
		}

		currentArena.addSpites.clear();

		for (Sprite sprite : currentArena.removeSpites) {
			if (currentArena.spites.contains(sprite)) {
				currentArena.spites.remove(sprite);
			}
		}

		currentArena.removeSpites.clear();
	}

	public static void render(Graphics g) {
		for (Sprite sprite : currentArena.spites) {
			sprite.render(g);
		}
	}

	public void addSprite(Sprite sprite) {
		if (!addSpites.contains(sprite)) {
			addSpites.add(sprite);
		}
	}

	public void removeSprite(Sprite sprite) {
		if (!removeSpites.contains(sprite)) {
			removeSpites.add(sprite);
		}
	}
	
	public void cleanSprites() {
		for (Sprite sprite : currentArena.spites) {
			removeSprite(sprite);
		}
	}

}
