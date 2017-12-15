package tp.pr2;

public class MoveCommand extends Command{

	private int n;
	private String cad;
	private String helpText;
	private String commandText;
	Direction dir;
	
	// Se le pasa commandInfo, helpInfo para pasarselo al constructor de la superclase(super())
	public MoveCommand(Direction d){
		super("move", " <direction>: execute a move in one of the directions: up, down, left, right");// Constructora de la clase de la que hereda
		this.dir = d;
	}
	
	public void execute(Game g){
		g.move(dir); // ???
	}
	
	public Command parse(String[] s){
		String c;
		
		if(s.length != 2) return null;
		if(!s[0].equalsIgnoreCase("MOVE")) return null;
		
		c = s[1];
		
		if(!c.equalsIgnoreCase("UP") || !c.equalsIgnoreCase("DOWN") || !c.equalsIgnoreCase("LEFT") || !c.equalsIgnoreCase("RIGHT")) return null;
		else return new MoveCommand(dir); // ???
	}
	
	public String helpText(){
		return commandText + " " + helpText;
	}
}
