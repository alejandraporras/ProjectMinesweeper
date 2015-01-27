package data;

import org.hibernate.Session;

import domain.Administrator;
import domain.InitSessionFactory;
import domain.Player;
import domain.RegisteredUser;
import domain.RegisteredUserController;

public class DataAccesUser implements RegisteredUserController{

	@Override
	public RegisteredUser getRegisteredUser(String username) {
		
		System.out.println("Player amb username: " + username);
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		RegisteredUser registeredUser = (RegisteredUser) session.createQuery("from RegisteredUser where username = :XX").setString("XX", username).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return registeredUser;
	}
	@Override
	public void createRegisteredUsers() {
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		Player player = new Player("Alejandra","Porras","ale","pass","alejan@gmail.com");
		session.save(player);
		player = new Player("Felipe","Porras","felipe","xxx","fp@gmail.com");
		Administrator admin = new Administrator("Pepe","Porras","admin","xxx");
		session.save(player);
		session.save(admin);
		session.getTransaction().commit();
		session.close();
		
	}

}
