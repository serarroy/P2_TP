package tp.pr2;

public class HelpCommand extends NoParamsCommand {

	private String commandText, helpText;
	
	public HelpCommand(){
		super("help", "help: print this help message.");
	}
	
	public void execute(Game g, Controller controller){
		g.help();
	}
	
	public Command parse(String[] s){
		if(s.length == 1 && s[0].equalsIgnoreCase("HELP")){
			return new HelpCommand();
		}
		else return null;
	}
	
	public String helpText(){
		return " " + commandText + " " + helpText; 
	}
}
