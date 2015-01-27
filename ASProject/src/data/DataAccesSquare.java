package data;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import domain.Game;
import domain.InitSessionFactory;
import domain.Square;
import domain.SquareController;

public class DataAccesSquare implements SquareController {

	@Override
	public List<Square> all() {
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Square> squares = session.createQuery("from Square").list();
		System.out.println(squares.size());
		for(Square square : squares){
			System.out.println(square.toString());
		}
		session.getTransaction().commit();
		session.close();
		return squares;
	}

	@Override
	public void saveSquare(Square square){

		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(square);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public List<Square> getSquaresWithMine(Game game) {
		List<Square> squares = all();
		List<Square> squaresWithMines = new ArrayList<Square>();
		for(Square square : squares){
			if(square.isMine()) squaresWithMines.add(square);
		}
		return squaresWithMines;
	}

	@Override
	public Square getSquare(Game game, Integer i, Integer j) {
		List<Square> squares = all();
		Square squareFound = null;
		for(Square square : squares){
			System.out.println(square.toString());
			if(square.getGame().getId() == game.getId() && square.getRowNumber() == i && square.getColumnNumber() == j) {
				squareFound = square;
				break;
			}
		}
		System.out.println(squareFound.toString());
		return squareFound;
	}

	@Override
	public void saveOrUpdate(List<Square> squares) {

		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		for(Square square : squares){
			session.saveOrUpdate(square);
		}
		session.getTransaction().commit();
		session.close();
		
	}

}
