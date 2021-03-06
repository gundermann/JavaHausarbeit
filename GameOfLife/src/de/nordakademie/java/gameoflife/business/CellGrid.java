package de.nordakademie.java.gameoflife.business;

public class CellGrid {

	private final int columns;
	private final int rows;
	private final Cell[][] cellArray;

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

	public boolean isCellAlive(Cell cell) {
		return cell.isAlive();
	}

	public Cell getCellAtPosition(int row, int column) {
		Cell cellAtPosition;
		if (row <= rows - 1 && column <= columns - 1 && row >= 0 && column >= 0) {
			cellAtPosition = cellArray[row][column];
		} else {
			cellAtPosition = null;
		}
		return cellAtPosition;
	}

	public boolean[][] getCellGridAsBooleanArray() {
		boolean[][] boolArray = new boolean[rows][columns];
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				Cell cell = getCellAtPosition(row, column);
				boolArray[row][column] = isCellAlive(cell);
			}
		}
		return boolArray;
	}

}
