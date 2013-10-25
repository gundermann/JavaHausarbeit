package de.nordakademie.java.gameoflife.logic.game;

import java.util.ArrayList;
import java.util.List;

/*  Diese Klasse bildet das Spielfeld innerhalb des Programms ab.
 Die Zellen werden hier geboren bzw. get�tet. 
 Weiterhin wird �ber diese Klasse nach Nachbarn gesucht.

 @author niels.gundermann */
public class CellGrid {

	private int rows;
	private int columns;
	private Cell[][] cellArray;

	public CellGrid(Integer[][] initinalArray) {
		rows = initinalArray.length;
		columns = initinalArray[0].length;
		initCellGrid(initinalArray);
	}

	private void initCellGrid(Integer[][] initinalArray) {
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

	public boolean isCellAtPositionAlive(int row, int column) {
		return getCellAtCoordinates(row, column).isAlive();
	}

	public int getColumnCount() {
		return columns;
	}

	public int getRowCount() {
		return rows;
	}

	public Cell[][] getCellGrid() {
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

	public Boolean[][] convertCellGridToBooleanArray() {
		Boolean[][] integerArray = new Boolean[rows][columns];
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				integerArray[row][column] = isCellAtPositionAlive(row, column);
			}
		}
		return integerArray;
	}
}
