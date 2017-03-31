package app.pixel.jsnake.game;

import app.pixe.jsnake.arena.Arena;
import app.pixel.jsnake.graphic.Render;
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
		Arena.currentArena.addSprite(new Snake(0, 0, 20,20));
	}

	public static void quit() {
		System.exit(1);
	}

}
