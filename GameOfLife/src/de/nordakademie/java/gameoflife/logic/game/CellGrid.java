package de.nordakademie.java.gameoflife.logic.game;

import java.util.ArrayList;
import java.util.List;

/*  Diese Klasse bildet das Spielfeld innerhalb des Programms ab.
  	Die Zellen werden hier geboren bzw. getötet. 
  	Weiterhin wird über diese Klasse nach Nachbarn gesucht.
  
  	@author niels.gundermann */
public class CellGrid {
	
	private int rows;
	private int columns;
	private Cell[][] cellArray;
	
	public CellGrid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		initCellGrid();
	}

	private void initCellGrid() {
		cellArray = new Cell[rows][columns];
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				cellArray[row][column] = new Cell();
			}
		}
	}

	public void killCell(Cell cell){
		cell.killYourself();
	}
	
	public void bearCell(Cell cell){
		cell.bear();
	}
	
	public boolean isCellAtPositionAlive(int row, int column){
		return getCellAtPosition(row, column).isAlive();
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
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
					cells.add(getCellAtPosition(row, column));
				}
			}
		return cells;
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
		int columnOfCell = -1;
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				if(cell == cellArray[row][column]){
						columnOfCell = column;
						break;
					}
				}
			}
		return columnOfCell;
	}

	public int getRowOfCell(Cell cell) {
		int rowOfCell = -1;
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
					if(cell == cellArray[row][column]){
						rowOfCell = row;
						break;
					}
				}
			}
		return rowOfCell;
	}
	
	public Integer[][] convertCellGridToIntegerArray() {
		Integer[][] integerArray = new Integer[rows][columns];
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				integerArray[row][column] = getNumericFormBoolean(getCellAtPosition(row, column).isAlive());
			}
		}
		return integerArray;
	}

	private Integer getNumericFormBoolean(boolean isAlive) {
		Integer value = 0;
		if(isAlive){
			value = 1;
		}
		return value;
	}
}
