package de.nordakademie.java.gameoflife.logic.game;

public class GamePad {
	
	private CellGrid grid;

	public GamePad(Integer[][] initinalArray) {
		int rows = initinalArray.length;
		int columns = initinalArray[0].length;
		grid = new CellGrid(rows, columns);
	}

	public CellGrid getCellGrid() {
		return grid;
	}


	

}
