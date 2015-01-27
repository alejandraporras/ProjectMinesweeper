package domain;

import data.DataAccesGame;
import data.DataAccesLevel;
import data.DataAccesPlayer;
import data.DataAccesSquare;
import data.DataAccesStrategy;
import data.DataAccesUser;


public class DataFactoriaController {
	private static DataFactoriaController instance = new DataFactoriaController();
	private PlayerController playerController = new DataAccesPlayer(); ;
	private RegisteredUserController userController = new DataAccesUser();
	private LevelController levelController = new DataAccesLevel();
	private final SquareController squareController = new DataAccesSquare();
	private final DataAccesStrategy dataAccesStrategy = new DataAccesStrategy();
	private final GameController gameController = new DataAccesGame();
	
	private DataFactoriaController(){
		playerController = new DataAccesPlayer();
		userController = new DataAccesUser();
		levelController =  new DataAccesLevel();
	}
	public static DataFactoriaController getInstance() {
		return instance;
	}
	public static void setInstance(DataFactoriaController instance) {
		DataFactoriaController.instance = instance;
	}
	public PlayerController getPlayerController() {
		return playerController;
	}
	public RegisteredUserController getUserController() {
		return userController;
	}
	public LevelController getLevelController() {
		return levelController;
	}
	public void setLevelController(LevelController levelController) {
		this.levelController = levelController;
	}
	public GameController getGameController() {
		return gameController;
	}
	public SquareController getSquareController() {
		return squareController;
	}
	public DataAccesStrategy getDataAccesStrategy() {
		return dataAccesStrategy;
	}
	
	
}
