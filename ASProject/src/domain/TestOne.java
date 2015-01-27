package domain;

import java.util.List;

import org.hibernate.Session;



public class TestOne {

	public static void main(String[] args) {

		
		createPlayer();

		//createAdministrator();
		//printAllPlayers();
		//printAllAdministrators();
		
	}
	public static void createPlayer(){
		Player player = new Player("Alejandra","Porras","ale","pass","alejan@gmail.com");
		
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(player);
		player = new Player("Felipe","Porras","felipe","xxx","fp@gmail.com");
		Administrator admin = new Administrator("Pepe","Porras","admin","xxx");
		session.save(player);
		session.save(admin);
		session.getTransaction().commit();
		
		session.close();
		//tiene que ir despues que se haga el close
		//printAllUsers();
		createRelationshipGameLevelUser(player);
		//System.out.println(getRegisteredUser("admin").toString());
	}
	public static Player getPlayer(String username){
		System.out.println("Player amb username: " + username);
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		return (Player) session.createQuery("from Player where username = :XX").setString("XX", username).uniqueResult();
	}
	public static RegisteredUser getRegisteredUser(String username) {
		System.out.println("Player amb username: " + username);
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		return (RegisteredUser) session.createQuery("from RegisteredUser where username = :XX").setString("XX", username).uniqueResult();

	}
	public static void createAdministrator(){
		Administrator player = new Administrator("Juan","Perez","juanp","sss");
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(player);
		session.getTransaction().commit();
		
		session.close();

	}

	
	public static void createRelationshipGameLevelUser(Player player){
		Level level = new Level("Beginner", 8, 8, 10); // 8 squares x row, 8 squares x column, 10 mines
		Game game = new Game(); // not finished, not won, 0 tries, 
		//TimeStrategy timeStrategy = new TimeStrategy(1, 100); //Stragy 1, maxim time 100 segs
		
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(level);
		session.save(game);
		
		//Relationship both sides between game and level
		game.setLevel(level);
		level.getGames().add(game);
		
		//Relationship both sides between game and current player
		game.setCurrentPlayer(player);
		player.getGames().add(game); // esto no importa mucho
		player.setCurrentGame(game);
		
		//Relationship between strategy and game
		//game.setStrategy(timeStrategy);
		//timeStrategy.setGame(game);
		
		session.beginTransaction().commit();
		session.close();
		System.out.println(getLevel(game).toString());
		printGames(level);
		System.out.println(getStrategy(game).toString());
		
	}
	private static void printAllPlayers() {
		System.out.println("Players: ");
		Session session = InitSessionFactory.getSessionFactory().openSession();
		  session.beginTransaction();
		  @SuppressWarnings("unchecked")
		  List<Player> players = session.createQuery("from Player").list();
		 System.out.println(players.size());
		  //session.getTransaction().commit(); si quito esto funciona
		  for(Player player : players){
			  System.out.println(player.toString());
		  }
		  session.close();
	}
	private static void printAllAdministrators() {
		System.out.println("Administrators: ");
		Session session = InitSessionFactory.getSessionFactory().openSession();
		  session.beginTransaction();
		  @SuppressWarnings("unchecked")
		  List<Administrator> administrators = session.createQuery("from Administrator").list();
		 System.out.println(administrators.size());
		  session.getTransaction().commit();
		  for(Administrator administrator : administrators){
			  System.out.println(administrator.toString());
		  }
	}
	private static void printAllUsers() {
		System.out.println("All users:");
		Session session = InitSessionFactory.getSessionFactory().openSession();
		  session.beginTransaction();
		  @SuppressWarnings("unchecked")
		  List<RegisteredUser> users = session.createQuery("from RegisteredUser").list();
		 System.out.println(users.size());
		  session.getTransaction().commit();
		  for(RegisteredUser user : users){
			  System.out.println(user.toString());
		  }
	}
	//Print all the games of a level
	private static void printGames(Level level) {
		System.out.println("All games of the level: " + level.getName());
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		List<Game> Games = session.createQuery("from Game where level_name = :XX").setString("XX", level.getName()).list();
		for(Game game : Games){
			System.out.println(game.toString());
		}

	}
	private static Level getLevel(Game game) {
	//Get the level of a game
		System.out.println("Level of Game: " + game.getId());
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		String id = game.getLevel().getName();
		return (Level) session.createQuery("from Level where name = :XX").setString("XX", id).uniqueResult();

	}
	private static Strategy getStrategy(Game game) {
	//Print the level of a game
		System.out.println("Strategy of Game: " + game.getId());
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		Integer id = game.getStrategy().getId();
		Strategy strategy =  (TimeStrategy) session.createQuery("from TimeStrategy where id = :XX").setLong("XX", id).uniqueResult();
		if(strategy == null){
			strategy = (TriesStrategy) session.createQuery("from TriesStrategy where id = :XX").setLong("XX", id).uniqueResult();
		}
		return strategy;
	}
	

}
