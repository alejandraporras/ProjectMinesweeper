package domain;

public interface GameController {

	public Game getGame(Integer id);
	public Game createGame(Level level, Player player);
	public void updateSurrondings(Integer rows, Integer cols, Game game);
}
