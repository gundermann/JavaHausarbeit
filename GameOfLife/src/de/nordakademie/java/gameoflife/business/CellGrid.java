package de.nordakademie.java.gameoflife.business;

import java.util.ArrayList;
import java.util.List;

public class CellGrid {

	private int columns;
	private int rows;
	private Cell[][] cellArray;
	private List<Cell> cellList;

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
		cellList = converCellArrayToList();
	}

	private List<Cell> converCellArrayToList() {
		List<Cell> cells = new ArrayList<Cell>();
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				cells.add(getCellAtPosition(row, column));
			}
		}
		return cells;
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

	public boolean isCellAtPositionAlive(int row, int column) {
		return getCellAtPosition(row, column).isAlive();
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

	public List<Cell> getCellsAsList() {
		return cellList;
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

	public int getColumnOfCell(Cell cell) {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				if (cell == cellArray[row][column]) {
					return column;
				}
			}
		}
		return -1;
	}

	public int getRowOfCell(Cell cell) {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				if (cell == cellArray[row][column]) {
					return row;
				}
			}
		}
		return -1;
	}

	// public boolean[][] convertCellGridToBooleanArray() {
	// boolean[][] integerArray = new boolean[columns][rows];
	// for (int row = 0; row < columns; row++) {
	// for (int column = 0; column < rows; column++) {
	// integerArray[row][column] = isCellAtPositionAlive(row, column);
	// }
	// }
	// return integerArray;
	// }
}
