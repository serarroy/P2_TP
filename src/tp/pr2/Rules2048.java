package tp.pr2;

import java.util.Random;

public class Rules2048 implements GameRules{

	private Board b; //?
	private int VALOR_GANADOR = 2048;
	
	public Rules2048(){
		
	}
	
	public void addNewCellAt(Board board, Position pos, Random rand){
		
	}
	// Fusiona dos celulas y devuelve el numero obtenido por la fusion
	public int merge(Cell self, Cell other){
		
	}
	// Devuelve el mejor valor del tablero, comprobandose si es un valor ganador
	// y se ha ganado el juego
	int getWinValue(Board board);
	// Devuelve si el juego se ha ganado o no
	public boolean win(Board board);
}