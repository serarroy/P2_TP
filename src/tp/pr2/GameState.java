/* Pr�ctica 2: Mejora y extensi�n de la aplicaci�n 2048
 * Autores: Sergio Arroyo Gal�n
 * 			Alejandro Casado Benito
 * Tecnolog�a de la Programaci�n
 * Facultad de Inform�tica
 * Universidad Complutense de Madrid
*/

package tp.pr2;

public class GameState{

	//private static final int CAPACITY = 20; En GameStateStack
	private int score, highest;
	private int[][] boardState;
	
	public GameState(){} // Constructora de momento
	// Hacemos mutadora para usar en 
	public void setBoardState(int[][]a){
		this.boardState = a;
	}
	
	public GameState getGameState(){
		return this;
	}
	// Devuelve el ultimo estado almacenado
	/*
	public GameState pop(){
		
	}
	*/
	// Almacena un nuevo estado
	/*
	public void push(GameState state){
		
	}
	*/
}