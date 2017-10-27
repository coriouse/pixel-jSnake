package app.pixel.jsnake.game;

import app.pixe.jsnake.arena.Arena;
import app.pixel.jsnake.graphic.Render;
import app.pixel.jsnake.object.Food;
import app.pixel.jsnake.object.Score;
import app.pixel.jsnake.object.Snake;

/**
 * Game handler
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Game {

	public static void main(String[] args) {
		Render.init();
		startGame();
	}

	public static void startGame() {
		Arena.currentArena = new Arena();
		Arena.currentArena.addSprite(new Snake(0, 0, 25, 25));
		Arena.currentArena.addSprite(new Food(60, 0, 7));
		Arena.currentArena.addSprite(new Food(-40, 0, 8));
		Arena.currentArena.addSprite(new Food(0, 40, 5));
		Arena.currentArena.addSprite(new Score(-270, 225));
	}

	public static void quit() {
		System.exit(1);
	}
}
