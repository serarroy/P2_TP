/* Pr�ctica 1: Mejora y extensi�n de la aplicaci�n 2048
 * Autores: Sergio Arroyo Gal�n
 * 			Alejandro Casado Benito
 * Tecnolog�a de la Programaci�n
 * Facultad de Inform�tica 
 * Universidad Complutense de Madrid
*/

package tp.pr2;
public class Cell {

	private int valor;
	
	// Constructora
	public Cell(int valor){
		this.valor = valor;
	}
	
	// Mutadora
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	// Accesora
	public int getValor() {
		return this.valor; 
	}
	// Incrementa el valor de la celda
	
	public int incrementa(){
		return this.valor * 2;
	}
	
	// METODOS DE LA CLASE 
	
	// Devuelve si una celda y su vecina tienen el mismo valor
	public boolean mismoValor(Cell neighbour) {
		return this.valor == neighbour.valor;
	}
	
	// Devuelve true si la celda esta vacia, false en caso contrario
	public boolean isEmpty() {
		return this.valor == 0; // Cierto si vacia, falso si llena
	}
	
	public boolean doMerge(Cell neighbour){
		
		if(this.mismoValor(neighbour)){ // Comparamos el valor de this con neighbour
			this.setValor(this.valor + neighbour.valor); // Aumentamos valor de this
			neighbour.setValor(0); // Eliminamos neighbour
			return true;
		}
		else{
			return false;
		}
	}	
}