package tp.pr2;
import java.util.Scanner;
import java.util.Random;

public class PlayCommand extends Command{
	
	private int size;
	private int initCells;
	private long semilla;
	Direction dir;
	GameType gameType;
	private Scanner sc;
	private Game game; ///////////////////////////
	
	
	public PlayCommand(String commandInfo, String helpInfo, GameType gt) {
		super(commandInfo, helpInfo);
		this.gameType = gt;
	}
	
	public void execute(Game game, Controller controller){
		Random rand;
		System.out.println("Introduzca el tamaño del tablero: ");
		if(sc.hasNextInt()){
			this.size = sc.nextInt();
		}
		else{
			this.size = game.getSize();
		}
		System.out.println("Introduzca el numero de celdas iniciales");
		if(sc.hasNextInt()){
			this.initCells = sc.nextInt();
		}
		else{
			this.initCells = game.getInitCells(); // Lo que haya por defecto
		}
		System.out.println("Introduzca el valor de la semilla");
		if(sc.hasNextLong()){ // Podria ser de tipo long
			this.semilla = sc.nextLong();
			rand = new Random(this.semilla);
		}
		else{
			rand = game.getMyRandom();
		}
		
		switch(gameType){
		case FIB:{
			this.game = new Game(new RulesFib(size, initCells, rand), this.size, this.initCells, rand);
		}
		}
	}
	
}
