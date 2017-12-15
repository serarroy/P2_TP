/* Práctica 2: Mejora y extensión de la aplicación 2048
 * Autores: Sergio Arroyo Galán
 * 			Alejandro Casado Benito
 * Tecnología de la Programación
 * Facultad de Informática
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