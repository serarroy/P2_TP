/* Práctica 2: Mejora y extensión de la aplicación 2048
 * Autores: Sergio Arroyo Galán
 * 			Alejandro Casado Benito
 * Tecnología de la Programación
 * Facultad de Informática
 * Universidad Complutense de Madrid
*/
package tp.pr2;
import java.util.Random;

public class Board {

	private int boardSize;
	private Cell[][] board;
	private MoveResults mr;
	private int score3;
	private static final int CAPACITY = 20;///////////////////////
	
	
	public int getBoardSize() {
		return boardSize;
	}

	public Cell[][] getBoard(){ //???????????????????????????????????????
		return board;
	}
	
	/*
	 * MAL
	public void setBoard(int[][] c){ ////?????????????????????????
		this.board.setValor(c);
	}
	*/
	public MoveResults getMr(){
		return this.mr;
	}
	
	public int getScore3(){
		return this.score3;
	}
	public void setScore3(int valor){
		this.score3 = valor;
	}
	// Constructora
	public Board(int size){
		this.boardSize = size;
		this.mr  = new MoveResults(0, 0, false);
		this.board = new Cell[boardSize][boardSize];
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				// Inicializamos el tablero a vacio
				board[i][j] = new Cell(0);
			}
		}
	}

	// METODOS DE LA CLASE

	public void setCell(Position pos, int value){
		
		int fila = pos.getFila();
		int columna = pos.getColumna();
		
		board[fila][columna] = new Cell(value);
	}
	
	public void setCell(int f, int c, int v){ // v es el valor de la celda
		this.board[f][c].setValor(v);
	}
	
	
	public Cell getCell(int f, int c){
		return this.board[f][c];
	}
	
	
	 
	public int generarValorCelda() {
		
		Random random = new Random();
		
		// Genero un numero entre 1 y 100 para las probabilidades
		// de fichas con valor 2 o 4 (0-89: '2'; 90-100: '4')
		int prob = random.nextInt(100);
		
		if(prob < 90) {
			// Genero un dos (0 + 2)
			return random.nextInt(1) + 2;
		}
		else {
			// Genero un cuatro (0 + 4)
			return random.nextInt(1) + 4;
		}
	}
	
	public boolean generarNuevaCelda(int fila, int columna) {
		if((fila < 0 || fila > this.boardSize) || (columna < 0 || columna > this.boardSize)){
			return false;
		}
		// La celda esta llena, no podemos generar un valor en ella
		if(board[fila][columna].getValor() != 0) {
			return false;
		}
		else{ // La celda esta vacia, podemos generar un valor en ella
			Random random = new Random();
			board[fila][columna].setValor(generarValorCelda());
			return true;
		}
	}
	
	// Método encargado de desplazar las celdas
	public Cell[] desplazaCeldas(Cell[] array){
		int j = this.boardSize - 1;
		for(int i = this.boardSize - 1; i >= 0; i--){
			if(array[i].getValor() != 0){
				array[j].setValor(array[i].getValor());
				//array[i].setValor(0);
				j--;
			}
		}
		for(int i = 0; i <= j; i++){
			array[i].setValor(0);
		}
		return array;
	}
	
	// Calcula highest
	public int highest() {
		int highestResult  = 0;
		for(int f = 0; f < this.boardSize;f++) {
			for(int c = 0; c < this.boardSize;c++) {
				if(this.board[f][c].getValor() > highestResult) {
					highestResult = this.board[f][c].getValor();
				}
			}
		}
		return highestResult;
	}
	
	public int lowest(){
		int lowestResult = this.board[0][0].getValor();
		
		for(int f = 0; f < this.boardSize; f++){
			for(int c = 0; c < this.boardSize; c++){
				if(this.board[f][c].getValor() < lowestResult){
					lowestResult = this.board[f][c].getValor();
				}
			}
		}
		return lowestResult;
	}
	
	// Algoritmo para fusionar celdas
	public Cell[] fusionaCeldas(Cell[] array){
		int j = this.boardSize - 2;
		int score = 0, score2 = 0, highest = 0;
		
		for(int i = this.boardSize - 1; i > 0; i--){ //>=
			score = 0;
			if(array[i].getValor() == array[j].getValor()){
				score = score+ array[i].getValor() + array[j].getValor();
				score2 = score2+ score;
				
				array[i].setValor(array[i].getValor() + array[j].getValor());
				array[j].setValor(0);
				j--;
			}
			else{
				j--; // Desplazamos la j
			}
		}
		highest = this.highest();
		this.mr.setHighest(highest);
		this.setScore3(this.getScore3() + score2);
		
		return array; // Devolvemos el array con todas las posibles fusiones realizadas
	}
	
	// Algoritmo para desplazar celdas
	public Cell[] desplazaFusiona(Cell[] array){
		
		//return this.desplazaCeldas(this.fusionaCeldas(this.desplazaCeldas(array)));
		//return this.desplazaCeldas(this.fusionaCeldasV2(this.desplazaCeldas(array))); // QUITAR: PRUEBAS VERSION 2 (2048 INVERSO)
		return this.desplazaCeldas(this.fusionaCeldasFIB(this.desplazaCeldas(array))); // QUITAR: PRUEBAS VERSION 2 (FIBONACCI)
		
		 // Devolvemos la fila o columna fusionada	
	}
	 
	// Realiza el movimiento hacia abajo
	private void moveDown(){
		this.mr.setMoved(true);
		Cell[] ar = new Cell[this.boardSize];
		int k;
		for(int c = 0; c < this.boardSize; c++) {
			k = 0;
			for(int f = 0; f < this.boardSize; f++) {
				ar[k] = this.board[f][c];
				k++;
			}
			k = 0;
			Cell[] resp = this.desplazaFusiona(ar);
			for(int i = 0; i < this.boardSize; i++) {
				this.board[i][c].setValor(resp[i].getValor());
			}
		}
	}
	
	// Realiza el movimiento hacia arriba
	private void moveUP(){
		this.mr.setMoved(true);
		Cell[] ar = new Cell[this.boardSize];
		int k;
		
		// Antes de realizar el movimiento guardar el estado del tablero en un array
		int[][] previo = new int[this.boardSize][this.boardSize];
		for(int i = 0; i < this.boardSize; i++){
			for(int j = 0; j < this.boardSize; j++){
				previo[i][j] = this.board[i][j].getValor();
			}
		}
		
		for(int c = 0; c < this.boardSize; c++){
			k = 0;
			for(int f = this.boardSize - 1; f >= 0; f--){
				ar[k] = this.board[f][c];
				k++;
			}
			k = 0;
			Cell[] resp = this.desplazaFusiona(ar);
			for(int i = 0; i < this.boardSize; i++){
				this.board[i][c].setValor(resp[resp.length - 1 - i].getValor());
				k++;
			}
		}
	}
	
	// Realiza el movimiento hacia la derecha
	private void moveRight() {
		this.mr.setMoved(true);
		Cell[] ar = new Cell[this.boardSize];
		int k = 0;
		
		// Antes de realizar el movimiento guardar el estado del tablero en un array
		int[][] previo = new int[this.boardSize][this.boardSize];
		for(int i = 0; i < this.boardSize; i++){
			for(int j = 0; j < this.boardSize; j++){
				previo[i][j] = this.board[i][j].getValor();
			}
		}
		
		for(int f = 0; f < this.boardSize; f++) {
			k = 0;
			for(int c = 0; c < this.boardSize; c++) {
				ar[k] = this.board[f][c];
				k++;
			}
			k = 0;
			Cell[] resp = this.desplazaFusiona(ar);
			for(int i = 0; i < this.boardSize; i++) {
				this.board[f][i].setValor(resp[i].getValor());
			}
		}
	}
	// Realiza el movimiento hacia la izquierda
	private void moveLeft() {
		this.mr.setMoved(true);
		Cell[] ar = new Cell[this.boardSize];
		int k = 0;
		
		// Almacenamos el estado del tablero antes de realizar el movimiento
		int[][] previo = new int[this.boardSize][this.boardSize];
		for(int i = 0; i < this.boardSize; i++){
			for(int j = 0; j < this.boardSize; j++){
				previo[i][j] = this.board[i][j].getValor();
			}
		}
		
		for(int f = 0; f < this.boardSize; f++) {
			k = 0;
			for(int c = this.boardSize - 1; c >= 0; c--) {
				ar[k] = this.board[f][c];
				k++;
			}
			k = 0;
			Cell[] resp = this.desplazaFusiona(ar);
			for(int i = 0; i < this.boardSize; i++) {
				this.board[f][i].setValor(resp[resp.length - 1 - i].getValor());
			}
		}
	}
	
	public MoveResults executeMove(Direction dir){
		
		switch(dir){
			case UP:{
				moveUP();
			}
			break;
			case DOWN:{
				moveDown();
			}
			break;
			case RIGHT:{
				moveRight();
			}
			break;
			case LEFT:{
				moveLeft();
			}
			break;
		}
		this.mr.setScore(this.getScore3());
		return this.mr;
	}
	
	// Indica si el tablero esta lleno o no
	public boolean fullBoard(){
		int llenas = 0;
		
		for(int i = 0; i < this.boardSize; i++){
			for(int j = 0; j < this.boardSize; j++){
				if(this.board[i][j].getValor() != 0){
					llenas++; // Contamos el numero de celdas llenas para compararlo con el total
				}
			}
		}
		if(llenas == this.boardSize * this.boardSize){ // Si el numero de llenas es igual al total, lleno
			return true;
		}
		else{ // Si no es igual al total, quiere decir que hay celdas vacias
			return false;
		}
	}
	
	public Position[] freeCells(){
		
		Position[] pos = new Position[this.boardSize * this.boardSize]; // Array de boardSize positions
	
		int k = 0;
		for(int i = 0; i < this.boardSize; i++){
			for(int j = 0; j < this.boardSize; j++){
				if(this.board[i][j].getValor() == 0){
					pos[k] = new Position(i, j);
					k++;
				}
			}
		}
		Position[] posResult = new Position[k];
		for(int i = 0; i < k; i++){
					posResult[i] = pos[i];
		}
		return posResult;
	}
	
	
	// Averigua si hay dos celdas contiguas fusionables (en fila y columna)
	public boolean canMerge(){
		
		boolean lleno = true;
		boolean movPosibles = false;
		int fila = 0, col = 0;

		while(lleno && (fila < this.boardSize) && !movPosibles)
		{
			while((col < this.boardSize-1) && lleno && !movPosibles)
			{
				if (this.board[fila][col].getValor() == 0)//
					lleno = false;
				else if(this.board[fila][col].getValor() == this.board[fila][col+1].getValor())
					movPosibles = true;
				else
					col++;
			}
			if (lleno && !movPosibles)
			{
				fila++;
				col = 0;
			}
		}
		if (lleno) // Comprueba las columnas 
		{
			fila = 0;
			while((col < this.boardSize) && !movPosibles)
			{
				while((fila < this.boardSize-1) && !movPosibles)
				{
					if(this.board[fila][col].getValor() == this.board[fila+1][col].getValor())
						movPosibles = true;
					else
						fila++;
				}
				if(!movPosibles)
				{
					col++;
					fila = 0;
				}
			}
		}
		else
			movPosibles = true;

		return movPosibles;
	}
	
	 // Dibuja la linea discontinua del tablero
	 public String DrawFirstAndLastLine(){
		 int cellSize = 7;
		 String cad = "";
		 String hDelimiter = "-";
		 String space = " ";

		cad += space;
		for(int j = 0; j < this.boardSize * (cellSize + 1) - 1/* * this.boardSize + this.boardSize*/; j++) {
			cad += hDelimiter;
		}
		 return cad;
	 }
	 
	 // Dibuja la linea con rayas verticales y el valor de las celdas
	 public String DrawDataLine(){
		int cellSize = 7;
		String space = " ";
		String vDelimiter ="|";
		String cad = "";
		
		for(int i = 0; i < this.boardSize; i++){
			for(int j = 0; j < this.boardSize; j++){
				cad += vDelimiter;
				for(int k = 0; k < cellSize / 2; k++){
					cad += space;
				}
				if(board[i][j].getValor() == 0){
					cad += space;
				}
				else{
					cad += board[i][j].getValor();
				}
				
				for(int k = 0; k < cellSize / 2; k++){
					cad += space;
				}
			}
			cad += vDelimiter;
			cad += "\n";
			cad += DrawFirstAndLastLine();
			cad += "\n";
		}

		return cad;
	 }
	// Dibuja el tablero
	public String toString() {
		String cad = "";
		
		cad += this.DrawFirstAndLastLine();
		cad += "\n";
		cad += this.DrawDataLine();
		cad += "\n";
		
		return cad;
	}
	
	//*************************
	// METODOS DE LA PRÁCTICA 2
	//*************************
	
	public int generarValorCeldaFIB(){ // Versión Fibonacci
		Random random = new Random();
		
		// Genero un numero entre 1 y 100 para las probabilidades
		// de fichas con valor 2048 o 1024 (0-89: '1'; 90-100: '2')
		int prob = random.nextInt(100);
		
		if(prob < 90) {
			// Genero 2048 (0 + 1)
			return random.nextInt(1) + 1;
		}
		else {
			// Genero 1024 (0 + 1024)
			return random.nextInt(1) + 2;
		}
	}
	/*
	 // Dado un numero devuelve la posicion de la sucesion de Fibonacci de ese valor
	public int fibonacci(int n){
		int i = 0, j = 1;

		for (int k = 1; k < n; k++){
			int t;
			t = i + j;
			i = j;
			j = t;
		}
		
		System.out.println("j que devuelve fibonacci(int n): " + j);
		return j;
	}
	*/
	
	/*
	// Devuelve la posicion en la sucesion de Fibonacci del valor que se le pasa
	public int buscarFibonacci(int n){
		int i = 0;
		
		while(i < fibonacci(n)){
			i++;
		}
		
		System.out.println("Posicion i que devuelve buscarFibonacci(int n): " + i);
		return i;
	}
	*/
	// Metodo enunciado de la practica
	public int nextFib(int previous){
		double phi = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(phi * previous);
	}
	
	public boolean generarNuevaCeldaFIB(int fila, int columna){
		if((fila < 0 || fila > this.boardSize) || (columna < 0 || columna > this.boardSize)){
			return false;
		}
		if(board[fila][columna].getValor() != 0){
			return false;
		}
		else{
			Random random = new Random();
			board[fila][columna].setValor(this.generarValorCeldaFIB());
			return true;
		}
	}
	
	public int generarValorCeldaINV(){ // Versión Inversa
		Random random = new Random();
		
		// Genero un numero entre 1 y 100 para las probabilidades
		// de fichas con valor 2048 o 1024 (0-89: '2048'; 90-100: '1024')
		int prob = random.nextInt(100);
		
		if(prob < 90) {
			// Genero 2048 (0 + 2048)
			return random.nextInt(1) + 2048;
		}
		else {
			// Genero 1024 (0 + 1024)
			return random.nextInt(1) + 1024;
		}
	}
	
	public boolean generarNuevaCeldaINV(int fila, int columna){
		if((fila < 0 || fila > this.boardSize) || (columna < 0 || columna > this.boardSize)){
			return false;
		}
		// La celda esta llena, no podemos generar un valor en ella
		if(board[fila][columna].getValor() != 0) {
			return false;
		}
		else{ // La celda esta vacia, podemos generar un valor en ella
			Random random = new Random();
			board[fila][columna].setValor(this.generarValorCeldaINV());
			return true;
		}
	}
	
	 // Metodo que fusiona celdas segun las reglas de Fibonacci
	// NOTA: NO FUSIONA CORRECTAMENTE
	public Cell[] fusionaCeldasFIB(Cell[] array){
		int j = this.boardSize - 2;
		int score = 0, score2 = 0, highest = 0;
		int fib, fibPos;
		
		for(int i = this.boardSize - 1; i > 0; i--){ //>=
			score = 0;
			fib = this.nextFib(array[i].getValor()); // Calculamos el valor de la serie de Fibonacci para el siguiente elemento
			System.out.println("array[ " + i + "].getValor(): " + array[i].getValor());
			System.out.println("fib: " + fib);
			if(array[i].getValor() == fib){
				//score = score + array[i].getValor() + array[j].getValor();
				score2 = score2+ score;
				array[i].setValor(array[i].getValor() + fib);
				array[j].setValor(0);
				j--;
			}
			else{
				j--; // Desplazamos la j
			}
		}
		highest = this.highest();
		this.mr.setHighest(highest);
		this.setScore3(this.getScore3() + score2);
		
		return array; // Devolvemos el array con todas las posibles fusiones realizadas
	}
	
	// Fusión de las celdas segun 2048 inverso
	public Cell[] fusionaCeldasV2(Cell[] array){
		int j = this.boardSize - 2;
		int score = 0, score2 = 0, highest = 0;
		
		for(int i = this.boardSize - 1; i > 0; i--){ //>=
			score = 0;
			if(array[i].getValor() == array[j].getValor()){
				if(array[i].getValor() == 2048){ // Fusión de celdas con valor 2048 --> 2 ptos
					score = score + 2;
				}
				else if(array[i].getValor() == 1024){ // Fusión de celdas con valor 2048 --> 4 ptos
					score = score + 4;
				}
				else if(array[i].getValor() == 512){ // Fusión de celdas con valor 512 --> 8 ptos
					score = score + 8;
				}
				else if(array[i].getValor() == 256){ // Fusión de celdas con valor 256 --> 16 ptos
					score = score + 16;
				}
				else if(array[i].getValor() == 128){ // Fusión de celdas con valor 128 --> 32 ptos
					score = score + 32;
				}
				else if(array[i].getValor() == 64){ // Fusión de celdas con valor 64 --> 64 ptos
					score = score + 64;
				}
				else if(array[i].getValor() == 32){ // Fusión de celdas con valor 32 --> 128 ptos
					score = score + 128;
				}
				else if(array[i].getValor() == 16){ // Fusión de celdas con valor 16 --> 256 ptos
					score = score + 256;
				}
				else if(array[i].getValor() == 8){ // Fusión de celdas con valor 8 --> 512 ptos
					score = score + 512;
				}
				else if(array[i].getValor() == 4){ // Fusión de celdas con valor 4 --> 1024 ptos
					score = score + 1024;
				}
				else if(array[i].getValor() == 2){ // Fusión de celdas con valor 2 --> 2048 ptos
					score = score + 2048;
				}
				else if(array[i].getValor() == 1){ // Fusión de celdas con valor 1 --> 4096 ptos
					score = score + 4096;
				}
				//score = score + array[i].getValor() + array[j].getValor();
				score2 = score2+ score;
				
				array[i].setValor(array[i].getValor() / 2); // Cambiado respecto a la practica 1: Al fusionar celdas el nuevo valor es la mitad
				array[j].setValor(0);
				j--;
			}
			else{
				j--; // Desplazamos la j
			}
		}
		highest = this.highest();
		this.mr.setHighest(highest);
		this.setScore3(this.getScore3() + score2);
		
		return array; // Devolvemos el array con todas las posibles fusiones realizadas
	}
	
	// Produce la representacion compacta a partir del estado del tablero actual
	public int[][] getState(){ // ??????
		int[][] m = null; // Salía warning
		
		for(int i = 0; i < this.boardSize - 1; i++){
			for(int j = 0; j < this.boardSize - 1; j++){
				m[i][j] = this.board[i][j].getValor();
			}
		}
		return m;
	}
	
	
	// Establece el estado del tablero actual a partir de la representacion compacta
	//pasada como argumento 
	/*
	public void setState(int [][] aState){
		this.board.setBoard(aState);
	}
	*/
	// Devuelve el ultimo estado almacenado
	//public GameState pop();
	
	// Almacena nuevo estado
	//public void push(GameState state);
	
	// Devuelve si la estructura de datos esta vacia
	//public boolean isEmpty();
	
}