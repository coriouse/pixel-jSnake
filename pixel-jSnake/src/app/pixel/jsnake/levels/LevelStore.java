package app.pixel.jsnake.levels;

public class LevelStore implements LevelFactory {

	@Override
	public LevelExecuter getLevel(LevelName levelName) {
		
		switch(levelName) {
		case MENU:
			return new MenuLevel();
		case LEVEL1:
			return new Level1();
		case LEVEL2:
			return new Level2();
		case LEVEL3:
			return new Level3();
			default:
				return new MenuLevel();
		}
		
	}

}
