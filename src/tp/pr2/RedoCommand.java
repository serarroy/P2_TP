package tp.pr2;

public class RedoCommand extends NoParamsCommand{

	private String commandText;
	private String helpText;
	
	/*
	public RedoCommand(String commandInfo, String helpInfo, String commandText, String helpText){
		super(commandInfo, helpInfo);
		this.commandText = commandText;
		this.helpText = helpText;
	}
	*/
	public RedoCommand(){
		super("redo: ", "redo the last undone command");
	}
	
	public Command parse(String[] commandWords, Controller controller){
		
		if(commandWords.length == 1 && commandWords[0].equalsIgnoreCase("REDO")) return new RedoCommand();
		else return null;
	}

	@Override
	// SI SE QUITA DA ERROR
	public void execute(Game game, Controller controller) {
		// TODO Auto-generated method stub
		game.redo();
	}
	
	
	public String helpText(){
		return " " + commandText + " " + helpText;
	}
	
}
