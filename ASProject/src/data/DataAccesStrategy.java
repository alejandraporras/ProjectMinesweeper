package data;

import org.hibernate.Session;

import domain.Game;
import domain.GameUtil;
import domain.InitSessionFactory;
import domain.Strategy;

public class DataAccesStrategy {
	
	public Strategy createStrategy(Game game) {
	
		Strategy strategy = GameUtil.getRandomStrategy(game);
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(strategy);
		game.setStrategy(strategy);
		
		session.getTransaction().commit();
		session.close();
		return strategy;
	}
}
