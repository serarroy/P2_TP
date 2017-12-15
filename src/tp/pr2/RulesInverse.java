package tp.pr2;

import java.util.Random;

public class RulesInverse {

	private Board b;
	private int VALOR_GANADOR = 2;
	
	public int getWinValue(){
		return this.b.lowest();
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
