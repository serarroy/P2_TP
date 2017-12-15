package tp.pr2;

public class GameStateStack {
	private GameState[] gs;
	private int CAPACITY = 20;
	private int contador; // Indica la ocupación del array
	
	
	// Devuelve el ultimo estado almacenado
	public GameState pop(){
		return this.gs[this.contador].getGameState(); //?????????????
	}
		
	// Almacena nuevo estado
	public void push(GameState state){
		// Si hay hueco para insertar
		if(this.contador != this.CAPACITY){
			this.gs[this.contador] = state;
			++this.contador;
		}
	}
		
	// Devuelve si la estructura de datos esta vacia
	public boolean isEmpty(){
		return this.contador == this.CAPACITY;
	}
}
