package tp.pr2;

import tp.pr2.Controller;
import tp.pr2.Game;

public abstract class Command {

	private String helpText;
	private String commandText;
	protected final String commandName;
	Direction dir;
	
	public Command(String commandInfo, String helpInfo){
		this.commandText = commandInfo;
		this.helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}
	
	public abstract void execute(Game game, Controller controller);
	
	public abstract Command parse(String[] commandWords, Controller controller);
	
	public String helpText(){return " " + commandText + ": " + helpText;}
}