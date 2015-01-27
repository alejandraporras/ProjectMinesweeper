package data;

import org.hibernate.Session;

import domain.Game;
import domain.GameController;
import domain.InitSessionFactory;
import domain.Level;
import domain.Player;

public class DataAccesGame implements GameController{
	@Override
	public Game getGame(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public static void main(String[] args) {
		
	}


	@Override
	public Game createGame(Level level, Player player) {
		Game game = new Game(false,false, level, player); // 100 tries number, 180 segs
	
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		//session.buildLockRequest(LockOptions.NONE).lock(level); 
		//Hibernate.initialize(level.getGames());
		level.getGames().add(game); //agrego al level el nuevo game;
		session.save(game);
	
		player.getGames().add(game);
		
		player.setCurrentGame(game);
		
		session.getTransaction().commit();
		session.close();
		return game;
	}


	@Override
	public void updateSurrondings(Integer rows, Integer cols, Game game) {
		// TODO Auto-generated method stub
		
	}
	


}
