package domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class Strategy implements Serializable{
	
	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE, generator="stra_gen")
	@SequenceGenerator (name= "stra_gen", sequenceName = "stra_id_sq")
	public Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	private Game game;

	public Strategy() {
	}
	public Strategy(Game game) {
		this.game = game;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	@Override
	public String toString() {
		return "Strategy [id=" + id + ", game=" + game + "]";
	}
	
}
