
package tp.pr2;

public class ResetCommand extends NoParamsCommand{
	
	private String commandText, helpText;
	
	public ResetCommand(){
		super("reset", "reset: start a new game.");
	}
	// Ejecuta el metodo reset de la clase Game
	public void execute(Game g, Controller controller){
		g.reset();
	}
	
	public Command parse(String[] s){
		if(s.length == 1 && s[0].equalsIgnoreCase("RESET")){
			return new ResetCommand();
		}
		else return null;
	}
	
	public String helpText(){
		return " " + commandText + " " + helpText; 
	}
}
