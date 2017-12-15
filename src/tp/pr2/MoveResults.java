/* Pr�ctica 2: Mejora y extensi�n de la aplicaci�n 2048
 * Autores: Sergio Arroyo Gal�n
 * 			Alejandro Casado Benito
 * Tecnolog�a de la Programaci�n
 * Facultad de Inform�tica
 * Universidad Complutense de Madrid
*/

package tp.pr2; 
public class MoveResults { 

	private int score;
	private int highest;
	private boolean moved; // Indica si en la jugada reci�n hecha se han movido baldosas

	// Constructora
	public MoveResults(int score, int highest, boolean m) {
		this.score = score;
		this.highest = highest;
		this.moved = m;
	}
	
	public void setScore(int valor){
		this.score = valor;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public int getHighest() {
		return this.highest;
	}
	public void setMoved(boolean m) {
		this.moved = m;
	}
	public void setHighest(int valor){
		this.highest = valor;
	}
	
	public boolean getMoved(){
		return this.moved;
	}
	
	public boolean compara(MoveResults m){
		return this.getHighest() > m.getHighest();
	}
}