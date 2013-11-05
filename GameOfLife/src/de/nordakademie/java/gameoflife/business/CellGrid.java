package de.nordakademie.java.gameoflife.business;

/*
 * Abbildung des Universums.
 * 
 * @autor Niels Gundermann
 */
public class CellGrid {

	private int columns;
	private int rows;
	private Cell[][] cellArray;

	public CellGrid(int[][] initinalArray) {
		rows = initinalArray.length;
		columns = initinalArray[0].length;
		cellArray = new Cell[rows][columns];
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				int currentSetup = initinalArray[row][column];
				checkSetupAndBearCell(currentSetup, row, column);
			}
		}
	}

	private void checkSetupAndBearCell(int currentSetup, int row, int column) {
		Cell cell = new Cell();
		if (currentSetup == 1) {
			bearCell(cell);
		}
		cellArray[row][column] = cell;
	}

	public void killCell(Cell cell) {
		cell.killYourself();
	}

	public void bearCell(Cell cell) {
		cell.bear();
	}

	public int getColumnCount() {
		return columns;
	}

	public int getRowCount() {
		return rows;
	}

	public Cell[][] getCellArray() {
		return cellArray;
	}

	public boolean isCellOnPositionAlive(int row, int column) {
		return getCellAtPosition(row, column).isAlive();
	}

	public Cell getCellAtPosition(int row, int column) {
		Cell cellAtPosition;
		try {
			cellAtPosition = cellArray[row][column];
		} catch (ArrayIndexOutOfBoundsException borderOverflow) {
			cellAtPosition = null;
		}
		return cellAtPosition;
	}

	public boolean[][] getCellGridAsBooleanArray() {
		boolean[][] boolArray = new boolean[rows][columns];
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				boolArray[row][column] = isCellOnPositionAlive(row, column);
			}
		}
		return boolArray;
	}
}
