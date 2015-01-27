package data;

import java.util.List;

import org.hibernate.Session;

import domain.InitSessionFactory;
import domain.Level;
import domain.LevelController;

public class DataAccesLevel implements LevelController{

	@Override
	public Level getLevel(String name) {

		//System.out.println("Level with name: " + name);
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		Level level = (Level) session.createQuery("from Level where name = :XX").setString("XX", name).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return level;
	}

	@Override
	public List<Level> all() {
		//System.out.println("Levels: ");
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Level> levels = session.createQuery("from Level").list();
		System.out.println(levels.size());
		//session.getTransaction().commit(); si quito esto funciona
		for(Level level : levels){
			System.out.println(level.toString());
		}
		session.getTransaction().commit();
		session.close();
		return levels;
	}

	@Override
	public void createLevels() {

		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		Level level = new Level("Beginner", 8, 8,10);
		session.save(level);
		level =  new Level("Intermediate", 16, 16,40);
		session.save(level);
		level =  new Level("Expert", 16, 30,99);
		session.save(level);
		session.getTransaction().commit();
		session.close();

	}



}
