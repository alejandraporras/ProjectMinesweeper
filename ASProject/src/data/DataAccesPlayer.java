package data;

import org.hibernate.Session;

import domain.InitSessionFactory;
import domain.Player;
import domain.PlayerController;

public class DataAccesPlayer implements PlayerController{

	@Override
	public Player getPlayer(String username) {
		//System.out.println("Player amb username: " + username);
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		Player player = (Player) session.createQuery("from Player where username = :XX").setString("XX", username).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return player;
	}

	@Override
	public boolean existsPlayer(String username) {
		boolean exists = true;
		if(getPlayer(username) == null){
			exists =false;
		}
		return exists;
	}
	


}
