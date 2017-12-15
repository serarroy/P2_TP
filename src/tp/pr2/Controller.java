/* Práctica 2: Mejora y extensión de la aplicación 2048
 * Autores: Sergio Arroyo Galán
 * 			Alejandro Casado Benito
 * Tecnología de la Programación
 * Facultad de Informática
 * Universidad Complutense de Madrid
*/

package tp.pr2; 
import java.util.Scanner;
 
public class Controller {

	private Game game;
	private Scanner in;
	
	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
	}
	
	public String toString() {
		return this.game.toString();
	}
	// Realiza la simulacion del juego
	/*
 	public void run() {
		String newline = System.getProperty("line.separator");
		String comando, aux = "", aux2 ="";
		Direction dir; // Direccion en la que realizar el movimiento
		
		this.game.inicializaTablero(this.game.getInitCells()); // Inicializamos el tablero con initCells cells
		
		while(!this.game.isFinished()){
			this.toString();
			System.out.print("Command > ");
			
			String s = in.nextLine();
			aux = s.toLowerCase(); // Pasamos el string a minusculas
			aux2 = aux.trim(); // Quitamos los blancos del principio y del final
			
			String[] ar = aux2.split("\\s+"); // Quitamos los blancos intermedios
			if(ar.length == 1){
				if(ar[0].equals("reset") || ar[0].equals("help") || ar[0].equals("exit")){
					if(ar[0].equals("reset")){
						this.game.reset();
					}
					else if(ar[0].equals("help")){
						this.game.help();
					}
					else{
						System.out.println("Hasta la proxima!!!");
						//System.exit(0);
						this.game.exit();
					}
				}
				else{
					System.out.println("Unknown command");
				}
			}
			else{
				if(ar[0].equals("move")){
					if(ar[1].equals("up") || ar[1].equals("down") || ar[1].equals("right") || ar[1].equals("left")){
						if(ar[1].equals("up")) {
							dir = Direction.UP;
						
							this.game.move(dir);
						}
						else if(ar[1].equals("down")) {
							dir = Direction.DOWN;
							this.game.move(dir);
						}
						else if(ar[1].equals("left")) {
							dir = Direction.LEFT;
							this.game.move(dir);
						}
						else if(ar[1].equals("right")) {
							dir = Direction.RIGHT;
							this.game.move(dir);
						}
					}
					else{
						System.out.println("Move must be followed by a direction: up, down, left or right");
					}
				}
				else{ // No se introduce ni move, ni help, ni reset, ni exit 
					System.out.println("Unknown command");
				}
			}
		}
		System.out.println("GAME OVER!!!");
	}
	*/
	
	
	// PRACTICA 2
	public void run(){
		Scanner sc;
		while(!game.isFinished()){
			this.toString();
			System.out.print("Command > ");
			String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
			Command command = CommandParser.parseCommand(words, this);
			if(command != null) command.execute(game, this);
			else System.out.println("Unknown command!!!"); // ????????????
		}
	}
}