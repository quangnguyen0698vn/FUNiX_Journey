package com.globalsoftwaresupport;

public class App {

	public static void main(String[] args) {
		
		char[][] boggleTable = { { 'a', 'n', 'i' }, 
				 				 { 'u', 'a', 'm' }, 
				 				 { 'b', 'l', 'r' }};
		
		BoggleGame game = new BoggleGame(boggleTable);
		game.findWords();

	}

}
