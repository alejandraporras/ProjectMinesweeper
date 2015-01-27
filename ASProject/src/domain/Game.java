package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator (name= "game_seq", sequenceName = "game_id_sq")
public class Game implements Serializable {
	
	
	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE, generator="game_seq")
	private Integer id;
	
	private boolean isFinished;
	private boolean isWon;
	@OneToMany(mappedBy="game")
	private final Set<Square> squares = new HashSet<Square>();
	
	@ManyToOne
	@JoinColumn
	private Level level;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "player_id")
	private Player currentPlayer;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "strategy_id")
	private Strategy strategy;
	
	@ManyToOne
	@JoinColumn
	private Player formerPlayer;

	public Game(){
		
	}
	public Game(boolean isFinished, boolean isWon, Level level, Player player ){
		this.isFinished = isFinished;
		this.isWon = isWon;
		this.level = level;
		currentPlayer = player;
		
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public boolean isWon() {
		return isWon;
	}

	public void setWon(boolean isWon) {
		this.isWon = isWon;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public Player getFormerPlayer() {
		return formerPlayer;
	}

	public void setFormerPlayer(Player formerPlayer) {
		this.formerPlayer = formerPlayer;
	}

	public Set<Square> getSquares() {
		return squares;
	}
	
	public boolean validSquare(int i, int j){
		return (level.validRow(i) && level.validCol(j));
	}
	@Override
	public String toString() {
		return "Game [id=" + id + ", isFinished=" + isFinished + ", isWon="
				+ isWon + ", squares=" + squares + ", level=" + level
				+ ", currentPlayer=" + currentPlayer + ", strategy=" + strategy
				+ ", formerPlayer=" + formerPlayer + "]";
	}
	

	

	


	
}
