package app.pixel.jsnake.game;

import app.pixe.jsnake.arena.Arena;
import app.pixel.jsnake.graphic.Render;

/**
 * Game handler
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Game {

	public static void main(String[] args) {
		Render.init();
		Arena.currentArena = new Arena();
		startGame();

	}

	public static void startGame() {
	}

	public static void quit() {
		System.exit(1);
	}

}
