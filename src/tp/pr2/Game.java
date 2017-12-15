/* Práctica 2: Mejora y extensión de la aplicación 2048
 * Autores: Sergio Arroyo Galán
 * 			Alejandro Casado Benito
 * Tecnología de la Programación
 * Facultad de Informática
 * Universidad Complutense de Madrid
*/

package tp.pr2;
import java.util.*;

public class Game {
	private Board board;
	private int size;
	private int initCells;
	private Random myRandom;
	private MoveResults mrGame;
	private GameState gs; /////????????????????????????????????????????????
	private GameRules currentRules;
	
	
	// Constructora VERSION 1
	/*
	public Game(int dim, int numInitial, Random r){
		this.mrGame  = new MoveResults(0, 0, false);
		this.size = dim;
		this.initCells = numInitial;
		this.myRandom = r;
		this.board = new Board(this.size);
	}
	*/
	// Constructora VERSION 2
	public Game(GameRules rules, int dim, int initCells, Random rd){
		this.size = dim;
		this.initCells = initCells;
		this.myRandom = rd;
		this.currentRules = rules;
	}
	
	public int getSize(){
		return this.size;
	}
	public int getInitCells() {
		return this.initCells;
	}
	public Random getMyRandom(){
		return this.myRandom;
	}
	/*
	 * En teoria no hace falta
	public int getSize(){ // Practica 2: Si usuario introduce return, coger valores por defecto
		this.size = 4;
		return this.size;
	}
	*/
	public boolean isFinished(){
		
		// Si el tablero esta lleno y no se pueden hacer fusiones
		if(this.board.fullBoard() && !this.board.canMerge()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void inicializaTablero(int iniciales) {
		int fil, col,highest,score;
		
		for(int i = 0; i < iniciales; i++) {
			 fil = myRandom.nextInt(this.size);
			 col = myRandom.nextInt(this.size);
			//this.board.generarNuevaCelda(fil, col); VERSION 1
			//this.board.generarNuevaCeldaINV(fil, col);// Genera 2048 y 1024 PRUEBAS VERSION 2
			 this.board.generarNuevaCeldaFIB(fil, col); // Genera 1 y 2 PRUEBAS VERSION 2
			 
		}
		highest = this.board.highest();
		
		this.mrGame.setHighest(highest);
		this.board.setScore3(0);
		this.mrGame.setScore(this.board.getScore3());
	}
	
	public void move(Direction dir){
		MoveResults mRBOARD2 = this.board.executeMove(dir);
		MoveResults mRBoard = this.board.getMr();
		this.board.setScore3(0);
		if(mRBoard.getMoved()){
			this.mrGame.setScore(this.mrGame.getScore() +mRBoard.getScore());
			if(!this.mrGame.compara(mRBoard)) {
				this.mrGame.setHighest(mRBoard.getHighest());
			}
			if(!this.isFinished()){
				Position []p = new Position[this.board.freeCells().length];
				p = this.board.freeCells();
				int aleatoria  = this.myRandom.nextInt(p.length);
				int f = p[aleatoria].getFila();
				int c = p[aleatoria].getColumna();
				//this.board.generarNuevaCelda(f,c); VERSION 1
				//this.board.generarNuevaCeldaINV(f, c); // VERSION 2 QUITAR
				this.board.generarNuevaCeldaFIB(f, c); // VERSION 2 QUITAR
			}
		}
	}
	
	public void exit(){
		System.exit(0);
	}
	
	public void reset(){
		
		int fil, col;
		
		for(int i = 0; i < this.size; i++){
			for(int j = 0; j < this.size; j++){
				Position p = new Position(i, j);
				this.board.setCell(p, 0);
			}
		}
		
		inicializaTablero(this.initCells);
		
	}
	
	public String toString() {
		String result = new String();
		String newline = System.getProperty("line.separator");
		System.out.print( this.board.toString()+ newline +"Score: " + this.mrGame.getScore()  + " "
			+ "Mejor Valor: " + this.mrGame.getHighest()+ newline);
		return result.toString();
	}
	
	public void help(){
		System.out.println("The available commands are:");
		System.out.println("    help: print this help message");
		System.out.println("    reset: start a new game");
		System.out.println("    exit: terminate the program");
		System.out.println("    Move <direction>: execute a move in one of the four directions, up, down, left, right");
	}
	
	
	// MÉTODOS PRÁCTICA 2
	
	public void undo(){
		
	}
	
	public void redo(){
		
	}
	
	// Devuelve el estado actual del juego, invocando, para ello, el metodo getState() de la clase Board
	
	public GameState getState(){ // ????????????????????????????????????????????????????
		this.gs.setBoardState(this.board.getState());
		return gs;
	}
	
	// Restablece el juego al que determina el estado pasado como argumento, invocando, para ello, el
	// metodo setState() de la clase Board
	/*
	public void setGame(GameState aState){
		
	}
	*/
}