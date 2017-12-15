package tp.pr2;

public class UndoCommand extends NoParamsCommand{
	
	private String commandText;
	private String helpText;
	
	/*
	public UndoCommand(String commandInfo, String helpInfo, String commandText, String helpText){
		super(commandInfo, helpInfo); // Constructor de la superclase
		this.commandText = commandText;
		this.helpText = helpText;
	}	
	*/
	
	public UndoCommand(){
		super("undo: ", "undo the last command");
	}
	
	public Command parse(String[] commandWords, Controller controller){
		if(commandWords.length == 1 && commandWords[0].equalsIgnoreCase("UNDO")) return new UndoCommand();
		return null;
	}
	
	public String helpText(){
		return " " + commandText + " " + helpText;
	}

	//SI SE QUITA DA ERROR
	@Override
	public void execute(Game game, Controller controller) {
		game.undo();
	}
}