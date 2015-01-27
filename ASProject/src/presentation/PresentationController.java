package presentation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.CUPlayGame;
import domain.DataFactoriaController;
import domain.Level;
import domain.Player;
import domain.Square;

public class PresentationController {
	
	private final LoginView loginView;
	private  LevelsView levelsView;
	//private CUJugarPartida cujp; //controlador de caso de uso
	private final  CUPlayGame game;
	private GameView gameView;
	private final DataFactoriaController dataController;
	private String playerUsername;
	private List<String> levelNames;
	//private final InfoLevel nivell = new InfoLevel();
		
	public PresentationController() {
		dataController = DataFactoriaController.getInstance();
		loginView = new LoginView(this);
		levelsView = new LevelsView(this,"");
		//cujp = new CUJugarPartida(this);
		game = new CUPlayGame(this);
	}

	// Lo hacemos gral (muestra/oculta) si le agregamos un parametro
	public void showLogin(boolean value) {
		loginView.setUsuariField("");
		loginView.setPasswordField("");
		loginView.setVisible(value); //antes esto iba debajo de os setField y se veia raro
	}
	
	public void iniciarPartida(String nomNivell){
		
		Level level = dataController.getLevelController().getLevel(nomNivell);
		Player player = dataController.getPlayerController().getPlayer(playerUsername);
		game.createGame(level, player);///
		Integer rows = level.getSquaresXRow();
		Integer cols = level.getSquaresXColumn();
		String levelNom = level.getName();
		Integer numMines = level.getMinesNumber();
		gameView = new GameView(this, rows, cols, levelNom,numMines.toString());
		gameView.setVisible(true);

	}

	public void showNivells() {
		gameView.setVisible(false);
		levelsView.setVisible(true);
	}

	public void showDialog( String missatge ){
		Object[] options = { "Ok" };
		JOptionPane.showOptionDialog(loginView, missatge, "Warning!",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
	}

	
	public void okLogin(String user, String pwd) {
		this.playerUsername = user ;
		try {
			game.authentication(user, pwd);
			//login.setVisible(false);
			// mejor esto:
			showLogin(false);
			
			levelsView = new LevelsView(this, user);
			levelsView.setVisible(true);
			
			levelsView.initLevels();

			
		} catch (Exception e) {
			String missatge = "Dades incorrectes";
			if(e.getMessage().equals("UsuariIncorrecte")){
				missatge = "Les dades introduides no són correctes";
			}
			
			else if(e.getMessage().equals("UsuariNoJugador")){
				missatge = "Els adminstradors no poden jugar";
			}
			else if(e.getMessage().equals("NoExisteixUsuariRegistrat")){
				missatge = "No hi ha cap usuari registrat amb aquest identificador";
			}
			
			showDialog(missatge); //substituye a las funciones mostraEtsAdmin, mostraDadesIncorrectes
		}
		
	}
	public String getUser(){
		return playerUsername;
	}
	public String[] getLevelNames(){
		List<Level> levels = game.allLevels();
		//CONSULT LEVELS
		String[]names = new String[3];
		for(int i = 0; i < levels.size(); ++i){
			names[i] = levels.get(i).getName();
		}
		return names;
	}
	public  List<Square> descobrirCasella(ButtonXY b) throws Exception{

		game.setSquareToReveal(new ArrayList<Square>());
		game.toUncoverSquareRec(b.getX(), b.getY());
		if(game.getIsLost()){
			showLost();
		}
		else if(game.isWon()){
			gameView.mostraGuanyada(100);
			
		}
		List<Square> result = game.getSquareToReveal();
		System.out.println("Casillas a destapar");
		
		for(Square square : result){
			System.out.println(square.toString());
		}
		System.out.println("****Casillas restantes: " + game.getNumberSquaresToWin());
		
		return result;
		
	}
	public void showLost(){
		List<Square> squaresWithMine = game.getSquaresWithMine();
		for(Square aux : squaresWithMine ){
			gameView.actualizarBoton(aux.getRowNumber(), aux.getColumnNumber(), aux.getValue());
		}
		gameView.mostraPerdut();
	}
	/*
	
	public Integer getTemps() {
		Integer s = partida.getSegons();
		Integer m = partida.getMinuts();
		return s+m*60;
	}
	
	public String[] obtenirNomNivells() throws Exception{
		ArrayList<ArrayList<String>> res = cujp.obtenirNivells();
		String r[] = new String [res.size()];
		for(int i=0; i<res.size();++i){
			r[i]=res.get(i).get(0);
		}
		return r;
	}
	public String[] obtenirFilesNivells() throws Exception{
		ArrayList<ArrayList<String>> res = cujp.obtenirNivells();
		String r[] = new String [res.size()];
		for(int i=0; i<res.size();++i){
			r[i]=res.get(i).get(1);
		}
		return r;
	}public String[] obtenirColumnesNivells() throws Exception{
		ArrayList<ArrayList<String>> res = cujp.obtenirNivells();
		String r[] = new String [res.size()];
		for(int i=0; i<res.size();++i){
			r[i]=res.get(i).get(2);
		}
		return r;
	}public String[] obtenirMinesNivells() throws Exception{
		ArrayList<ArrayList<String>> res = cujp.obtenirNivells();
		String r[] = new String [res.size()];
		for(int i=0; i<res.size();++i){
			r[i]=res.get(i).get(3);
		}
		return r;
	}
	public void mostrarMina(Integer nf, Integer nc){
		partida.mostrarMina(nf,nc);
	}
	*/
	public void printSquares(){
		List<Square> squares = game.getSquares();
		for(Square square : squares){
			System.out.println(square.toString());
		}
	}
	
	public void marcarDesmarcar(int nf, int nc) throws Exception {
		game.marcarDesmarcar(nf, nc);
	}
	
	public void marcarBoton(int nf, int nc) {
		gameView.marcarBoton(nf,nc);
	}
	public void desmarcarBoton(int nf, int nc) {
		gameView.desmarcarBoton(nf,nc);
	}

	public InfoLevel getDadesNivell(String nivelEscollit) {
		Level level = dataController.getLevelController().getLevel(nivelEscollit);
		InfoLevel infoLevel = new InfoLevel();
		infoLevel.setNameLevel(level.getName());
		infoLevel.setRows(level.getSquaresXRow());
		infoLevel.setColumns(level.getSquaresXColumn());
		infoLevel.setMine(level.getMinesNumber());
		return infoLevel;
		
		
	}
}

