package domain;

import java.util.List;

public interface LevelController {
	public Level getLevel(String name);
	public List<Level> all();
	public void createLevels();
}
