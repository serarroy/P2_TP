package tp.pr2;

public class CommandParser{

	private static Command[] availableCommands = {new HelpCommand(), new ResetCommand(), 
		new ExitCommand(), new MoveCommand(), new RedoCommand(), new UndoCommand(), new PlayCommand()};
	
	
	public static Command parseCommand(String[] commandsWords, Controller controller){
		int cont = 0;
		
		//String[] tokens = null; // Evitar el warning
		/*
		if(commandsWords.length > 0 && commandsWords.length < 2){
			tokens[0] = commandsWords[0];
			tokens[1] = commandsWords[1];
		}
		else{
			return null;
		}
		*/
		/*
		for(Command c:availableCommands){
			cm = c.parse(commandsWords, controller);
			if(cm != null) return cm;
		}
		return cm; //??
		*/
		
		while(cont < availableCommands.length){
			Command aux = availableCommands[cont].parse(commandsWords, controller);
			if(aux != null) return aux;
			++cont;
		}
		return null;
	}
	
	
	public static String commandHelp(){
		String s = "";
		for(Command c:availableCommands) s += c.helpText();
		return s;
	}
}