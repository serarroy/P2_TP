package tp.pr2;

public abstract class NoParamsCommand extends Command{

	public NoParamsCommand(String commandInfo, String helpInfo){
		super(commandInfo, helpInfo);
	}
	
	public Command parse(String[] input, Controller controller){
		if(input.length == 1){
			if(input[0].equals(commandName)) return this;
		}
		return null;
	}
	
	
	public void execute(Game game, Controller controller){
		
	}
}
