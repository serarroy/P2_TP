package tp.pr2;

public enum GameType {
	ORIG, FIB, INV;
	
	String name;
	GameRules rules;
	
	//getGameRules(); // Se invoca en el execute de play()
}