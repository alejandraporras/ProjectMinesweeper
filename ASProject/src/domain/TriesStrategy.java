package domain;
import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class TriesStrategy extends Strategy implements Serializable {

	private Integer maximTries;


	public TriesStrategy(Game game) {
		super(game);
	}
	public TriesStrategy(Integer tries){
		maximTries = tries;
	}
	public TriesStrategy(Game game, Integer tries) {
		super(game);
		maximTries = tries;

	}
	public TriesStrategy() {
		
	}
	public Integer getMaximTries() {
		return maximTries;
	}

	public void setMaximTries(Integer maximTries) {
		this.maximTries = maximTries;
	}
	public TriesStrategy createStrategy(){
		return null;

	}

	@Override
	public String toString() {
		return "TriesStrategy [maximTries=" + maximTries + "]";
	}

}
