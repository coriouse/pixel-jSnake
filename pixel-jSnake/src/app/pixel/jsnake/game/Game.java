package app.pixel.jsnake.game;

import app.pixel.jsnake.graphic.Render;
import app.pixel.jsnake.levels.LevelExecuter;
import app.pixel.jsnake.levels.LevelFactory;
import app.pixel.jsnake.levels.LevelName;
import app.pixel.jsnake.levels.LevelStore;

/**
 * Game handler
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Game {

	public static void main(String[] args) {
		Render.init();
		LevelFactory executeLevel = new LevelStore();
		LevelExecuter levelExecuter = executeLevel.getLevel(LevelName.MENU);
		levelExecuter.execute();
	}

	public static void quit() {
		System.exit(1);
	}
}
