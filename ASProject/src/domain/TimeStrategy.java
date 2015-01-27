package domain;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class TimeStrategy extends Strategy implements Serializable{

	private Integer maximTime;

	public TimeStrategy() {
	}
	
	public TimeStrategy(Game game) {
		super(game);
	}
	public TimeStrategy(Integer time){
		maximTime = time;
	}
	
	public TimeStrategy(Game game , Integer time) {
		super(game);
		maximTime = time;
	}


	public Integer getMaximTime() {
		return maximTime;
	}

	public void setMaximTime(Integer maximTime) {
		this.maximTime = maximTime;
	}

	public TimeStrategy createStrategy(){
		return null;

	}
	@Override
	public String toString() {
		return "TimeStrategy [maximTime=" + maximTime + "]";
	}

}
