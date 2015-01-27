package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Player extends RegisteredUser implements Serializable {
	
	@Column(unique=true, nullable=false)
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	private Game currentGame;
	
	@OneToMany(mappedBy="formerPlayer")
	private final Set<Game> games = new HashSet<Game>();

	public Player(){
		super();
	}
	public Player(String name, String surname, String username, String password) {
		super(name, surname, username, password);
		
	}
	public Player(String name, String surname, String username, String password, String email) {
		super(name, surname, username, password);
		this.email = email;
		
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Game getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}

	public Set<Game> getGames() {
		return games;
	}
	@Override
	public boolean isPlayer(){
		return true;
	}
	@Override
	public String toString(){
		
		return ("Name: " + this.getName() + " Surname: " + this.getSurname() + " username: " 
				+ this.getUsername() + " password: " + this.getPassword()+ " Email: " + email);

	}
}
