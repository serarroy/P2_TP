/* Pr�ctica 2: Mejora y extensi�n de la aplicaci�n 2048
 * Autores: Sergio Arroyo Gal�n
 * 			Alejandro Casado Benito
 * Tecnolog�a de la Programaci�n
 * Facultad de Inform�tica
 * Universidad Complutense de Madrid
*/

package tp.pr2; 
import java.util.*; // Scanner

public class Game2048 {

	public static void main(String[] args){
		
		int size = Integer.parseInt(args[0]);
		int initCells = Integer.parseInt(args[1]);
		long seed = new Random().nextInt(100);
		Controller c = new Controller(new Game(size, initCells, new Random(seed)), new Scanner(System.in));
		c.run();
	}
}