package app.pixel.jsnake.levels;

import app.pixel.jsnake.arena.Arena;
import app.pixel.jsnake.game.Menu;

public class MenuLevel implements LevelExecuter {

	@Override
	public void execute() {
		Arena.currentArena = new Arena();
		Arena.currentArena.addSprite(new Menu(-130, -40));
	}
}
