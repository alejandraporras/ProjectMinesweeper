package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Level implements Serializable{
	
	@Id
	private String name;
	private Integer squaresXRow;
	private Integer squaresXColumn;
	private Integer minesNumber;
	
	@OneToMany(mappedBy="level", fetch=FetchType.EAGER)
	private final Set<Game> games = new HashSet<Game>();

	public Level(){
		
	}
	public Level(String name, Integer squaresXRow, Integer squaresXColumn,
			Integer minesNumber) {
		this.name = name;
		this.squaresXRow = squaresXRow;
		this.squaresXColumn = squaresXColumn;
		this.minesNumber = minesNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSquaresXRow() {
		return squaresXRow;
	}

	public void setSquaresXRow(Integer squaresXRow) {
		this.squaresXRow = squaresXRow;
	}

	public Integer getSquaresXColumn() {
		return squaresXColumn;
	}

	public void setSquaresXColumn(Integer squaresXColumn) {
		this.squaresXColumn = squaresXColumn;
	}

	public Integer getMinesNumber() {
		return minesNumber;
	}

	public void setMinesNumber(Integer minesNumber) {
		this.minesNumber = minesNumber;
	}

	public Set<Game> getGames() {
		return games;
	}
	
	@Override
	public String toString(){
		return("Name: " + name + " squaresxRow: " + squaresXRow+ " squaresXColumn: " +
				squaresXColumn + " minesNumber: " + minesNumber);
	}
	public boolean validRow(int i) {
		return (i <= squaresXRow-1 && i >= 0);
	}
	public boolean validCol(int j) {
		return (j <= squaresXColumn-1 && j >= 0);
	}
	
	
}
