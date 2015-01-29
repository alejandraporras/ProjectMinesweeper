package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import presentation.PresentationController;

public class CUPlayGame {

	private final PresentationController presentationController;
	CULogin login;
	CUConsultLevels cuLevels;
	private Player player;
	private Level level;
	private Game game;
	List<Square> squares = new ArrayList<Square>();
	List<Square> squareToReveal = new ArrayList<Square>();
	List<Square> squaresWithMine  = new ArrayList<Square>();
	List<Square> squaresMarked = new ArrayList<Square>();
	private Integer numberSquaresToWin;
	private  boolean isLost;

	public void initValues(){
		Integer rows = level.getSquaresXRow();
		Integer cols = level.getSquaresXColumn();
		Integer mines = level.getMinesNumber();
		Integer  numSquares= cols*rows;
		numberSquaresToWin = numSquares - mines;
	}
	
	public boolean isWon(){
		if(numberSquaresToWin == 0)return true;
		else return false;
		
	}
	
	public CUPlayGame(PresentationController presentationController){
		this.presentationController = presentationController;
		login = new CULogin();
		cuLevels = new CUConsultLevels();
		isLost = false;
	}
	public void authentication(String username, String password) throws Exception{
		login.login(username,password);
	}
	public  List<Level> allLevels(){
		return cuLevels.getLevels();
	}
	public void createGame(Level level, Player player){
		this.level = level;
		this.player = player;
		game = DataFactoriaController.getInstance().getGameController().createGame(level, player);
		createSquares(level);
		initValues();

	}
	public void createSquares(Level level){
	
		Integer rows = level.getSquaresXRow();
		Integer cols = level.getSquaresXColumn();
		Integer mines = level.getMinesNumber();
		
		for (Integer i=0; i < rows; ++i){
			for (Integer j=0; j < cols; ++j){
				Square square = new Square(game,i,j);
				squares.add(square);
			}
		}
		Random random = new Random();
		Integer numberSquares = squares.size();
	
		for(Integer m=0; m < mines; ++m){
			int r = random.nextInt(numberSquares);
			Square aux =squares.get(r) ;
			while (aux.isMine()){
				r = random.nextInt(numberSquares);
				aux =squares.get(r) ;  // sin esto, loop infinito
			}
			aux.setMine(true);
			aux.setValue(-1);
			squaresWithMine.add(aux);
		}

		DataFactoriaController.getInstance().getSquareController().saveOrUpdate(squares);
		for(Integer m=0; m < squaresWithMine.size(); ++m){
			updateSurrondings(squaresWithMine.get(m).getRowNumber(),squaresWithMine.get(m).getColumnNumber() );
			
		}
		DataFactoriaController.getInstance().getDataAccesStrategy().createStrategy(game);
		printBoard();
	}

	public void updateSurrondings(Integer rows, Integer cols ){
		List<Square>squaresToUpdate = new ArrayList<Square>();
		for (int i = rows-1; i <= rows+1; ++i){
			for(int j = cols-1; j <= cols+1; ++j){
				if( i != rows || j != cols){
					if(game.validSquare(i,j)){
						Square square = getSquare(i,j);
						if(!square.isMine()){
							square.updateValue();
							squaresToUpdate.add(square);
						}
					}
				}
				
			}
		}
		DataFactoriaController.getInstance().getSquareController().saveOrUpdate(squaresToUpdate);
	
	}
	
	public Square getSquare(int row, int col){
		for(Square square : squares){
			if(square.getRowNumber()== row && square.getColumnNumber() == col) return square;
		}
		return null;
	}
	public int getIndexSquare(int row, int col){
		for(int i = 0; i < squares.size(); ++i){
			Square square = squares.get(i);
			if(square.getRowNumber()== row && square.getColumnNumber() == col) return i;
		}
		return -1;
	}
	
	

	public void marcarDesmarcar(int nf, int nc) throws Exception {
		int indexSquare = getIndexSquare(nf,nc);
		Square square = squares.get(indexSquare);
		if(!square.isUncovered()){
			if (square.isMarked()) {
				presentationController.desmarcarBoton(nf,nc);
				squaresMarked.remove(indexSquare);
			}
			else {
				presentationController.marcarBoton(nf,nc);
				squaresMarked.add(square);
			}
			
			square.setMarked(!square.isMarked());
			
			
		}
		
	}
	public void toUncoverSquareRec(int row, int col){
		if(game.validSquare(row,col)) {
				Square square = getSquare (row,col);
				if(!square.isMarked()){
					if(square.isMine()){
						isLost = true;
						square.setUncovered(true);
						squareToReveal.add(square);
						return;
					}
					
					else if(square.getValue() == null  && !square.isUncovered()){
						--numberSquaresToWin;
						square.setUncovered(true);
						squareToReveal.add(square);
						toUncoverSquareRec( row-1, col );
						toUncoverSquareRec( row-1, col-1);
						toUncoverSquareRec( row-1, col+1);
						
						toUncoverSquareRec( row, col-1 );
						toUncoverSquareRec( row, col+1 );
						
						toUncoverSquareRec( row+1, col );
						toUncoverSquareRec( row+1, col+1 );
						toUncoverSquareRec( row+1, col-1 );
						
					}
					else if(square.getValue() != null && !square.isUncovered()){
						--numberSquaresToWin;
						square.setUncovered(true);
						squareToReveal.add(square);
						return;
					}
					else return;
					
				}
		}
		else return;
	}
	
	public List<Square> toUncoverSquare(int row, int col){
		Square square = getSquare (row,col);
		if(square.isUncovered()){ //esta descubierta
			List<Square> auxiliar = new ArrayList<Square>();
			Square squareAux = new Square();
			auxiliar.add(squareAux);
			return auxiliar;
		}
		else{
			List<Square> squaresToUncover = new ArrayList<Square>();
			square.setUncovered(true);
			Integer squareValue = square.getValue();
			if(squareValue == 0 ){ // is blank recursivity
				for(int i = row-1;  i <= row+1; ++i){
					for(int j = col-1;  j <= col+1; ++j){
						if(game.validSquare(i,j)) {
							if(i != row || j != col){
								List<Square> result = toUncoverSquare(i,j);
								//result.remove(result.size()-1);
								squaresToUncover.addAll(result);
								
							}
						}
							
					}
				}
			}
			else if(!square.isMine()){//is number
				squaresToUncover.add(square);
			}
			
			return squaresToUncover;
		}
		
		
		
	}
	public void printSquares(){
		for(Square square : squares){
			System.out.println(square.toString());
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}

	public List<Square> getSquares() {
		return squares;
	}
	public void setSquares(List<Square> squares) {
		this.squares = squares;
	}
	
	
	public List<Square> getSquaresMarked() {
		return squaresMarked;
	}

	public void setSquaresMarked(List<Square> squaresMarked) {
		this.squaresMarked = squaresMarked;
	}

	public void printBoard(){
		System.out.println("Imprimiendo tableroooooooo");
		for(int i = 0; i < 8; ++i){
			for(int j = 0; j < 8; ++j){
				Square square = getSquare(i,j);
				if(square.isMine()){
					System.out.print("X ");
				}
				else {

					if( square.getValue() == null){
						System.out.print("O ");
					}
					else System.out.print(square.getValue() + " ");
				}
			}
			System.out.println();
		}
	}
	public List<Square> getSquareToReveal() {
		return squareToReveal;
	}
	public void setSquareToReveal(List<Square> squareToReveal) {
		this.squareToReveal = squareToReveal;
	}

	public Integer getNumberSquaresToWin() {
		return numberSquaresToWin;
	}

	public void setNumberSquaresToWin(Integer numberSquaresToWin) {
		this.numberSquaresToWin = numberSquaresToWin;
	}
	public boolean getIsLost(){
		return isLost;
	}

	public List<Square> getSquaresWithMine() {
		return squaresWithMine;
	}

	public void setSquaresWithMine(List<Square> squaresWithMine) {
		this.squaresWithMine = squaresWithMine;
	}
	public Game getGame(){
		return game;
	}
}
