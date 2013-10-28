package de.nordakademie.java.gameoflife.business;

import java.util.ArrayList;
import java.util.List;

public class CellGrid {

	private int columns;
	private int rows;
	private Cell[][] cellArray;

	public CellGrid(int[][] initinalArray) {
		columns = initinalArray.length;
		rows = initinalArray[0].length;
		cellArray = new Cell[rows][columns];
		for (int row = 0; row < columns; row++) {
			for (int column = 0; column < rows; column++) {
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
		cellArray[column][row] = cell;
	}

	public void killCell(Cell cell) {
		cell.killYourself();
	}

	public void bearCell(Cell cell) {
		cell.bear();
	}

	public boolean isCellAtPositionAlive(int row, int column) {
		return getCellAtCoordinates(row, column).isAlive();
	}

	public int getColumnCount() {
		return rows;
	}

	public int getRowCount() {
		return columns;
	}

	public Cell[][] getCellArray() {
		return cellArray;
	}

	public List<Cell> getCellsAsList() {
		List<Cell> cells = new ArrayList<Cell>();
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				cells.add(getCellAtCoordinates(row, column));
			}
		}
		return cells;
	}

	// TODO Koordinaten umdrehen
	public Cell getCellAtCoordinates(int y, int x) {
		Cell cellAtPosition;
		try {
			cellAtPosition = cellArray[y][x];
		} catch (ArrayIndexOutOfBoundsException borderOverflow) {
			cellAtPosition = null;
		}
		return cellAtPosition;
	}

	public int getColumnOfCell(Cell cell) {
		for (int column = 0; column < columns; column++) {
			for (int row = 0; row < rows; row++) {
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

	public boolean[][] convertCellGridToBooleanArray() {
		boolean[][] integerArray = new boolean[columns][rows];
		for (int row = 0; row < columns; row++) {
			for (int column = 0; column < rows; column++) {
				integerArray[row][column] = isCellAtPositionAlive(row, column);
			}
		}
		return integerArray;
	}
}
