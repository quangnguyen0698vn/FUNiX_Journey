package com.globalsoftwaresupport;

public class BoggleGame {
	
	private boolean[][] visited;
	private char[][] boggleTable;
	private TernarySearchTree tree;
	
	public BoggleGame(char[][] boggleTable) {
		this.boggleTable = boggleTable;
		this.tree = new TernarySearchTree();
		this.visited = new boolean[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
	}
	
	private boolean isValid(int rowIndex, int colIndex) {
		
		// we are outside the scope of the table horizontally
		if(rowIndex < 0 || rowIndex >= Constants.BOARD_SIZE)
			return false;
		
		// we are outside the scope of the table vertically
		if(colIndex < 0 || colIndex >= Constants.BOARD_SIZE)
			return false;
		
		// whether we have already visited that given cell
		if(visited[rowIndex][colIndex])
			return false;
		
		return true;	
	}

	// BACKTRACKING (depth-first search)
	private void search(int i, int j, String s) {
		
		// base-case (the character sequence is a valid English word)
		if(tree.isWordInTree(s))
			System.out.println(s);
		
		// is it possible to visit this cell?
		if(isValid(i, j)) {
			
			// make the step because the given cell is valid
            visited[i][j] = true; 
            
            // have to check the possible directions recursively
            // going to the left
            if(isValid(i-1, j))
            	search(i-1, j, s+boggleTable[i-1][j]);
            
            // going to the right
            if(isValid(i+1, j))
            	search(i+1, j, s+boggleTable[i+1][j]);
			
            // going down
            if(isValid(i, j+1))
            	search(i, j+1, s+boggleTable[i][j+1]);
            
            // going up
            if(isValid(i, j-1))
            	search(i, j-1, s+boggleTable[i][j-1]);
            
            // WE HAVE TO BACKTRACK
            visited[i][j] = false; 
		}
	}
	
	public void findWords() {
		for(int i=0;i<Constants.BOARD_SIZE;++i)
			for(int j=0;j<Constants.BOARD_SIZE;++j)
				search(i, j, ""+boggleTable[i][j]);
	}
}