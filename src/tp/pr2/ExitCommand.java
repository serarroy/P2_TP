package tp.pr2;

public class ExitCommand extends NoParamsCommand{

	private String commandText;
	private String helpText;
	
	public ExitCommand(){
		super("exit", "terminate the program");
	}

	public Command parse(String[] s){
		if(s.length == 1 && s[0].equalsIgnoreCase("EXIT")){
			return new ExitCommand();
		}
		else return null;
	}
	// SI SE QUITAN DA ERROR
	@Override
	public void execute(Game game, Controller controller) {
		game.exit();
	}
	
	public String helpText(){
		return " " + commandText + " " + helpText; 
	}
}
