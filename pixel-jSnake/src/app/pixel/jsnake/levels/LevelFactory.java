package app.pixel.jsnake.levels;

public interface LevelFactory {
	LevelExecuter getLevel(LevelName levelName);
}
