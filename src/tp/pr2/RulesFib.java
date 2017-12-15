package tp.pr2;

import java.util.Random;

public class RulesFib implements GameRules{ ///??
	private Board b;
	private int size;
	private Random myRandom;
	private int initCells;
	private int VALOR_GANADOR = 144; //???
	
	public RulesFib(int size, int initCells, Random rand){
		this.myRandom = rand;
		this.size = size;
		this.initCells = initCells;
	}
	
	public int getWinValue(){
		return this.b.highest();
	}
	
	public void addNewCellAt(Board board, Position pos, Random rand){
		
	}
	// Fusiona dos celulas y devuelve el numero obtenido por la fusion
	public int merge(Cell self, Cell other){
		return 0;
	}
	// Devuelve el mejor valor del tablero, comprobandose si es un valor ganador
	// y se ha ganado el juego
	public int getWinValue(Board board){
		return 0;
	}
	// Devuelve si el juego se ha ganado o no
	public boolean win(Board board){
		return true;
	}
}
