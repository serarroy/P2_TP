/* Práctica 2: Mejora y extensión de la aplicación 2048
 * Autores: Sergio Arroyo Galán
 * 			Alejandro Casado Benito
 * Tecnología de la Programación
 * Facultad de Informática
 * Universidad Complutense de Madrid
*/

package tp.pr2;
public class Position {

	private int fila, columna;

	// Constructora
	public Position(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	// Accesora
	public int getFila() {
		return this.fila;
	}
	
	// Accesora
	public int getColumna() {
		return this.columna;
	}
	
	public Position neighbour(Direction dir, int size){
		
		Position p = new Position(0, 0); // Para referirnos a la baldosa vecina
		
		if(this.columna == 0 && dir == Direction.LEFT){
			p.fila = -1;
			p.columna = -1;
		}
		else if(this.columna == size - 1 && dir == Direction.RIGHT){
			p.fila = -1;
			p.columna = -1;
		}
		else if(this.fila == 0 && dir == Direction.UP){
			p.fila = -1;
			p.columna = -1;
		}
		else if(this.fila == size - 1 && dir == Direction.DOWN){
			p.fila = -1;
			p.columna = -1;
		}
		else if(dir == Direction.UP){
			p.fila = p.fila - 1; // Fila de arriba
			// La columna se mantiene igual
		}
		else if(dir == Direction.DOWN){
			p.fila = p.fila + 1; // Fila de abajo
			// La columna se mantiene igual
		}
		else if(dir == Direction.LEFT){
			p.columna = p.columna - 1; // Columna de la izquierda
			// La fila se mantiene igual
		}
		else if(dir == Direction.RIGHT){
			p.columna = p.columna + 1; // Columna de la derecha
			// La fila se mantiene igual
		}
			
		return p;
	}
}