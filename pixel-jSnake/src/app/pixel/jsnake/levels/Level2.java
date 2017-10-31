package app.pixel.jsnake.levels;

import app.pixel.jsnake.arena.Arena;
import app.pixel.jsnake.object.Brick;
import app.pixel.jsnake.object.Food;
import app.pixel.jsnake.object.Score;
import app.pixel.jsnake.object.Snake;

public class Level2 implements LevelExecuter{

	@Override
	public void execute() {
		Arena.currentArena = new Arena();
		Arena.currentArena.addSprite(new Snake(0, 0, 25, 25));
		Arena.currentArena.addSprite(new Food(60, 0, 7));
		Arena.currentArena.addSprite(new Food(-40, 0, 8));
		Arena.currentArena.addSprite(new Food(0, 40, 5));
		Arena.currentArena.addSprite(new Brick(90, -150)); 
		
		Arena.currentArena.addSprite(new Score(-270, 225));
		
	}

}
