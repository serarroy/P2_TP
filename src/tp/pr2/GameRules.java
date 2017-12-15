package tp.pr2;
import java.util.Random;

public interface GameRules {

	// Incorpora una celula con valor aleatorio en la posicion pos del tablero board 
	void addNewCellAt(Board board, Position pos, Random rand);
	// Fusiona dos celulas y devuelve el numero obtenido por la fusion
	int merge(Cell self, Cell other);
	// Devuelve el mejor valor del tablero, comprobandose si es un valor ganador
	// y se ha ganado el juego
	int getWinValue(Board board);
	// Devuelve si el juego se ha ganado o no
	public boolean win(Board board);
	// Devuelve si el juego se ha perdido o no
	default boolean lose(Board board){
		
		return true;
	}

	default Board createBoard(int size){ // default method
		return new Board(size);
	}
	
	default void addNewCell(Board board, Random rand){
		
		Position[] p = new Position[board.freeCells().length];
		p = board.freeCells(); // Array de cells libres
		
		int aleat = rand.nextInt(p.length);
		Position pos = p[aleat]; // Elegimos una posicion aleatoria del array
		addNewCellAt(board, pos, rand); // Generamos una cell en esa posicion
	}
}
